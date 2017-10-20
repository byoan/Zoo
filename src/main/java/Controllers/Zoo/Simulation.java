package controllers.zoo;

import controllers.jobs.CheckDominationFactorJob;
import controllers.jobs.MakeAnimalsAgeJob;
import models.enums.AnimalTypes;
import models.enums.RandomActions;
import controllers.jobs.CheckNewBirthJob;
import models.employees.Employee;
import models.enclosures.Enclosure;
import models.animals.*;
import models.factories.EnclosureFactory;
import models.interfaces.animal.*;
import views.View;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    /**
     * Represents the controllers.zoo instance
     */
    private Zoo zoo;

    /**
     * Represents the current turn number
     */
    private int turnNb;

    /**
     * Represents the Scanner class, used for user input
     */
    Scanner scanner;

    /**
     * Represents the ThreadLocalRandom class, used for event generation between turns
     */
    ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * Constructor for the Simulation
     * Will initiate turn number, the scanner class for user input, get an employee instance, assign a new zoo to the simulation, and generate some population to start with
     */
    public Simulation() {
        this.setTurnNb(0);
        this.scanner = new Scanner(System.in);
        Employee employee = Employee.getInstance();
        this.setZoo(new Zoo("HackerMan", employee, 10));


        for (int i = 0; i < this.getZoo().getMaxNbEnclosure(); i++) {
            Enclosure enclosure = this.generateRandomEnclosures();
            zoo.addEnclosure(enclosure);
        }
    }

    /**
     * Getter for the ThreadLocalRandom instance of the Simulation
     * @return The ThreadLocalRandom instance of the simulation
     */
    public ThreadLocalRandom getRandom() {
        return this.random;
    }

    /**
     * Returns the employee instance of the zoo
     * @return employee The employee instance of the zoo
     */
    private Employee getEmployee() {
        return this.getZoo().getEmployee();
    }

    /**
     * Returns the current turn number
     * @return int The turn number
     */
    protected int getTurnNb() {
        return this.turnNb;
    }

    /**
     * Allows to define the current turn number, but can't be lower that the actual turn number
     * @param turnNb The wanted turn number
     */
    private void setTurnNb(int turnNb) {
        // Add this test to prevent going back to a previous turn
        if (turnNb >= this.getTurnNb()) {
            this.turnNb = turnNb;
        } else {
            View.displayErrorMessage("Error while trying to set the turn number to " + turnNb + ", can't go back to a previous turn");
        }
    }

    /**
     * Ends the current turn and launches the next one
     */
    private void nextTurn() {
        if (this.getTurnNb() != 0) {
            View.displayConsoleMessage("\nTurn n°" + this.getTurnNb() + " ended.\n");
            this.handleAging();
            this.handleNewBirths();
            this.handleDominationRetrograde();
            this.handleRandomEventGeneration();
            if (this.getTurnNb() % 10 == 0) {
                this.deteriorateEnclosures();
            }
        }

        this.setTurnNb(this.getTurnNb() + 1);

        View.displayConsoleMessage("Turn n°" + this.getTurnNb() + " started.\n");

        this.handleTurn();
    }

    /**
     * Allows to display a list of enclosures available for the new birth
     * Will only display enclosure that have enough space
     */
    private void displayEnclosureListForNewBirth(Animal animal) {
        View.displayInformationMessage("A new " + animal.getSpecieName() + " is born. Please choose an enclosure to put it in:");
        int i = 0;
        for (Enclosure<Animal> enclosure : this.getZoo().getEnclosureList()) {
            if (enclosure.getNbAnimals() < enclosure.getMaxAnimals()) {
                View.displayMessage((i + 1) + ". " + enclosure.getName());
            }
            ++i;
        }
    }

    /**
     * Checks if there is at least a male and a female in an Enclosure
     * Used before trying to make them copulate
     * @param enclosure The enclosure to test
     * @return Whether both sexes have been seen
     */
    private boolean hasAtLeastMaleAndFemale(Enclosure<Animal> enclosure) {
        boolean seenMale = false;
        boolean seenFemale = false;
        for (Animal animal : enclosure.getAnimals()) {
            if (animal.getSex()) {
                seenMale = true;
            } else if (!animal.getSex()) {
                seenFemale = true;
            }
        }

        return (seenMale && seenFemale);
    }

    /**
     * Allows to execute a job every 50 turn, which will make the animals get older
     */
    private void handleAging() {
        if (this.getTurnNb() % 50 == 0) {
            MakeAnimalsAgeJob job = new MakeAnimalsAgeJob(this.getZoo(), this.getTurnNb());
            job.exec();
        }
    }

    /**
     * Allows to check at each turn if a wolf has 50 or less domination, and must therefore be retrograded to a lower rank
     * Will only retrograde if the wolf is not the only one of his sex for his rank
     */
    private void handleDominationRetrograde() {
        CheckDominationFactorJob job = new CheckDominationFactorJob(this.getZoo().getEnclosureList());
        job.exec();
    }

    /**
     * Allows to handle all the newly born animals, returned from CheckNewBirthJob
     * Will be executed every turn
     */
    private void handleNewBirths() {
        CheckNewBirthJob checkNewBirthJob = new CheckNewBirthJob(this.getZoo().getEnclosureList(), this.getTurnNb());
        checkNewBirthJob.exec();
        // Retrieve result from exec() method
        ArrayList<Animal> newBirths =  checkNewBirthJob.getNewBirths();

        if (!newBirths.isEmpty()) {
            for (Animal animal : newBirths) {
                // Make the employee choose an enclosure for the newly born animal
                this.displayEnclosureListForNewBirth(animal);
                this.chooseEnclosureForNewBirth(animal);
            }
        }
    }

    /**
     * Asks the employee to choose a new enclosure for a newly born animal
     * Used when the parent's enclosure is full
     * @param animal The new animal
     */
    private void chooseEnclosureForNewBirth(Animal animal) {
        int action = 0;
        boolean correctAnswer = false;
        while (!correctAnswer) {
            String userInput = scanner.next();
            try {
                action = Integer.parseInt(userInput);
                if (action > this.getZoo().getEnclosureList().size()) {
                    View.displayWarningMessage("Can't enter a value greater than the maximum enclosure. Please try again:\n");
                    continue;
                }
                Enclosure<Animal> chosenEnclosure = this.getZoo().getEnclosureList().get(action - 1);
                if (chosenEnclosure != null && chosenEnclosure.getNbAnimals() < chosenEnclosure.getMaxAnimals()) {
                    chosenEnclosure.add(animal);
                    View.displayInformationMessage("Adding the new " + animal.getSpecieName() + " to the " + chosenEnclosure.getName() + " enclosure.\n");
                    correctAnswer = true;
                } else {
                    View.displayWarningMessage("This enclosure is not available. Please chose another one:\n");
                }
            } catch (Exception e) {
                View.displayErrorMessage("Error : " + e.toString() + ". Please try again:\n");
            }
        }
    }

    /**
     * Allows to randomly generate an event for the current turn
     */
    private void handleRandomEventGeneration() {
        // Random events generation
        Enclosure randomEnclosure = this.pickRandomEnclosure();
        if (randomEnclosure != null) {
            Animal animal = this.pickRandomAnimal(randomEnclosure);
            String randomAction = this.pickRandomAction();

            if (animal != null) {
                this.handleRandomAction(randomAction, animal, randomEnclosure);
            }
        }

    }

    /**
     * Allows to deteriorate the cleanliness (and specific children values) of the enclosure without having to specify any value
     */
    private void deteriorateEnclosures() {
        for (Enclosure<Animal> enclosure : this.getZoo().getEnclosureList()) {
            enclosure.deteriorate();
        }
        View.displayWarningMessage("enclosures are deteriorated. You should clean them.\n");
    }

    /**
     * This method allows to handle all the action that take place during this turn (from the employee side)
     */
    private void handleTurn() {
        View.displayPickAction();
        int action = 0;

        while (true) {
            String userInput = scanner.next();
            try {
                action = Integer.parseInt(userInput);
                break;
            } catch (Exception e) {
                View.displayErrorMessage("Error : " + e.getMessage() + ". Please try again");
            }
        }

        switch(action) {
            case 1:
                this.handlePickEnclosureToInspect();
                break;
            case 2:
                this.handlePickEnclosureToClean();
                break;
            case 3:
                this.handleAnimalFeeding();
                break;
            case 4:
                this.handleAnimalHealing();
                break;
            case 5:
                this.handleAnimalTransfer();
                break;
            case 6:
                this.handleDisplayCurrentNbAnimalsInZoo();
                break;
            case 7:
                this.handleDisplayAllAnimalsInZoo();
                break;
            case 8:
                this.doNothing();
                break;
            default:
                View.displayWarningMessage("Did not understood your action, please try again");
                this.handleTurn();
                break;
        }

        this.nextTurn();
    }

    /**
     * Allows to display the current number of animals in the zoo
     */
    private void handleDisplayCurrentNbAnimalsInZoo() {
        View.displayInformationMessage("Current number of animals in the zoo: " + this.getZoo().getNbAnimalsInZoo());
    }

    /**
     * Allows to display all the animals that are currently in the zoo
     */
    private void handleDisplayAllAnimalsInZoo() {
        View.displayInformationMessage("Here are all the animals currently in the zoo:\n");
        this.getZoo().displayAnimalsFromAllEnclosures();
    }

    /**
     * Displays the message which matches the "Do Nothing this turn" action
     */
    private void doNothing() {
        View.displayInformationMessage("Not doing anything this turn ...");
    }

    private <A extends AnimalInterface> void handleAnimalFeeding() {
        Enclosure<A> enclosure = this.pickEnclosure("Select the enclosure that you want to feed:");
        enclosure.feedAnimals();
        View.displaySuccessMessage("This enclosure has been fed");
    }

    /**
     * Allows to handle the healing of the animal chosen by the user
     * @param <A> Generic type which extends the AnimalInterface
     */
    private <A extends AnimalInterface> void handleAnimalHealing() {
        Enclosure<A> enclosure = this.pickEnclosure("Select the enclosure containing the animal you want to heal:");
        A animal = this.pickAnimal("Select the animal you want to heal:", enclosure);
        this.getEmployee().healAnimal(animal);
    }

    /**
     * Allows to handle the animal transfer between 2 enclosures
     * @param <A> Generic type to be used within the method itself
     */
    private <A extends AnimalInterface> void handleAnimalTransfer() {
        if (this.getZoo().getEnclosureList().size() > 0) {
            Enclosure<A> originEnclosure = this.pickEnclosure("Select the enclosure containing the animal you want to transfer:");
            A animal = this.pickAnimal("Select the animal you want to transfer:", originEnclosure);
            Enclosure<A> targetEnclosure = this.pickEnclosure("Select the enclosure in which you would like to send this animal:");

            // While the enclosure is not different, we must keep asking to the user a valid one
            while (originEnclosure.equals(targetEnclosure)) {
                View.displayWarningMessage("Can't transfer this animal in its current enclosure. Please choose another one.");
                targetEnclosure = this.pickEnclosure("Select the enclosure in which you would like to send this animal:");
            }

            // Proceed to the transfer
            originEnclosure.transferAnimal(animal, targetEnclosure, false);
        } else {
            View.displayInformationMessage("No enclosures are currently in the zoo.\n");
            // Return to the main menu
            this.handleTurn();
            return;
        }
    }

    /**
     * Offers the user to pick an enclosure from the zoo's enclosure list
     * @param messageToDisplay The message to display before offering options to the user
     * @param <A> Generic type to be used within the method itself
     * @return Enclosure<A> An Enclosure of AnimalInterface
     */
    private <A extends AnimalInterface> Enclosure<A> pickEnclosure(String messageToDisplay) {
        View.displayMessage(messageToDisplay);
        View.displayBackToMenuMessage();
        int i = 1;
        for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
            View.displayMessage(i + ". " + enclosure.getName());
            ++i;
        }

        int action = 0;
        while (true) {
            String userInput = scanner.next();
            try {
                action = Integer.parseInt(userInput);
                if (action > 0 && action <= this.getZoo().getEnclosureList().size()) {
                    break;
                } else if (action == 0) {
                    this.handleTurn();
                } else {
                    View.displayWarningMessage("Please enter a valid value");
                }
            } catch (Exception e) {
                View.displayErrorMessage("Error : " + e.getMessage() + ". Please try again");
            }
        }
        return this.getZoo().getEnclosureList().get(action - 1);
    }

    /**
     * Offers the user to pick an animal from the given enclosure
     * @param messageToDisplay The message to display before offering options to the user
     * @param enclosure The enclosure in which the user must pick an animal
     * @param <A> Generic type to be used within the method itself
     * @return The chosen animal, which implements the AnimalInterface
     */
    private <A extends AnimalInterface> A pickAnimal(String messageToDisplay, Enclosure<A> enclosure) {
        View.displayMessage(messageToDisplay);
        View.displayBackToMenuMessage();
        int i = 1;
        for (A animal : enclosure.getAnimals()) {
            View.displayMessage(i + ". " + animal.toString());
            ++i;
        }

        int action = 0;
        while (true) {
            String userInput = scanner.next();
            try {
                action = Integer.parseInt(userInput);
                if (action > 0 && action <= enclosure.getAnimals().size()) {
                    break;
                } else if (action == 0) {
                    this.handleTurn();
                } else {
                    View.displayWarningMessage("Please enter a valid value");
                }
            } catch (Exception e) {
                View.displayErrorMessage("Error : " + e.getMessage() + ". Please try again");
            }
        }
        return enclosure.getAnimals().get(action - 1);
    }

    /**
     * Allows the user to pick an enclosure to inspect
     * @param <A> Generic type to be used within the method itself
     */
    private <A extends AnimalInterface> void handlePickEnclosureToInspect() {
        if (this.getZoo().getEnclosureList().size() > 0) {
            View.displayConsoleMessage("\nWhich enclosure would you like to inspect?\n");
            View.displayBackToMenuMessage();
            int i = 1;
            for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
                View.displayMenuMessage(i + ". " + enclosure.getName());
                ++i;
            }

            int action = 0;
            while (true) {
                String userInput = scanner.next();
                try {
                    action = Integer.parseInt(userInput);
                    if (action > 0 && action <= this.getZoo().getEnclosureList().size()) {
                        break;
                    } else if (action == 0) {
                        this.handleTurn();
                    } else {
                        View.displayWarningMessage("Please enter a valid value");
                    }
                } catch (Exception e) {
                    View.displayErrorMessage("Error : " + e.getMessage() + ". Please try again");
                }
            }
            View.displayConsoleMessage("Inspecting the enclosure n°" + action + ":\n");
            View.displayMessage(this.getEmployee().inspectEnclosure(this.getZoo().getEnclosureList().get(action - 1)));
        } else {
            View.displayInformationMessage("No enclosures are currently in the zoo.\n");
            this.handleTurn();
            return;
        }
    }

    /**
     * Allows the user to pick an enclosure to clean
     * @param <A> Generic type to be used within the method itself
     */
    private <A extends AnimalInterface> void handlePickEnclosureToClean() {
        if (this.getZoo().getEnclosureList().size() > 0) {
            View.displayConsoleMessage("\nWhich enclosure would you like to clean?\n");
            View.displayBackToMenuMessage();
            int i = 1;
            for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
                View.displayInformationMessage(i + ". " + enclosure.getName());
                ++i;
            }

            int action = 0;
            while (true) {
                String userInput = scanner.next();
                try {
                    action = Integer.parseInt(userInput);
                    if (action > 0 && action <= this.getZoo().getEnclosureList().size()) {
                        break;
                    } else if (action == 0) {
                        this.handleTurn();
                    } else {
                        View.displayWarningMessage("Please enter a valid value");
                    }
                } catch (Exception e) {
                    View.displayErrorMessage("Error : " + e.getMessage() + ". Please try again");
                }
            }
            View.displayConsoleMessage("Cleaning the enclosure n°" + action + " ...");
            this.getEmployee().cleanEnclosure(this.getZoo().getEnclosureList().get(action - 1));
        } else {
            View.displayConsoleMessage("No enclosures are currently in the zoo.\n");
            this.handleTurn();
            return;
        }
    }

    /**
     * Returns our controllers.zoo instance
     * @return A controllers.zoo instance
     */
    protected Zoo getZoo() {
        return this.zoo;
    }

    /**
     * Setter for our controllers.zoo attribute
     * @param zoo A controllers.zoo instance
     */
    private void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }


    /**
     * Allows to pick a random enclosure from the zoo's enclosure list
     * @param <A> Generic type parameter, to receive an enclosure typed with an animal implementing AnimalInterface
     * @return The randomly picked enclosure
     */
    private <A extends AnimalInterface> Enclosure<A> pickRandomEnclosure() {
        if (this.getZoo().getEnclosureList().size() > 0) {
            int randomEnclosureId = this.getRandom().nextInt(this.getZoo().getEnclosureList().size());
            return this.getZoo().getEnclosureList().get(randomEnclosureId);
        }
        return null;
    }

    /**
     * Allows to pick a random animal in a given enclosure
     * @param enclosure The enclosure in which we must pick an animal
     * @return The randomly picked animal
     */
    private Animal pickRandomAnimal(Enclosure<Animal> enclosure) {
        if (enclosure.getNbAnimals() > 0) {
            int randomAnimalId = this.getRandom().nextInt(enclosure.getNbAnimals());
            return enclosure.getAnimals().get(randomAnimalId);
        }
        return null;
    }

    /**
     * Allows to randomly pick an action for the turn using the RandomActions enum
     * @return The name of the random action
     */
    private String pickRandomAction() {
        // Retrieve in our own array, as each call to values() creates a new array (performance hungry)
        RandomActions[] values = RandomActions.values();
        int actionId = this.getRandom().nextInt(values.length);
        return values[actionId].toString();
    }

    /**
     * Allows to handle the randomly generated action for the current turn
     * @param action The action that was generated
     * @param animal The animal which will sustain the event
     * @param enclosure The enclosure which contains the targeted animal
     */
    private void handleRandomAction(String action, Animal animal, Enclosure enclosure) {
        switch (action) {
            case "DECREASE_HUNGER":
                animal.setHunger(animal.getHunger() - 80);
                if (animal.getHunger() <= 0) {
                    enclosure.remove(animal);
                    View.displayWarningMessage("A " + animal.getSpecieName() + " starved to death in the " + enclosure.getName() + " enclosure.\n");
                } else {
                    View.displayWarningMessage("A " + animal.getSpecieName() + " is hungry in the " + enclosure.getName() + " enclosure.\n");
                }
                break;

            case "DECREASE_LIFE":
                animal.setHealth(animal.getHealth() - 80);
                if (animal.getHealth() <= 0) {
                    enclosure.remove(animal);
                    View.displayWarningMessage("A " + animal.getSpecieName() + " died in the " + enclosure.getName() + " enclosure.\n ");
                } else {
                    View.displayWarningMessage("A " + animal.getSpecieName() + " is hurt/sick in the " + enclosure.getName() + " enclosure.\n");
                }
                break;

            case "SLEEP":
                if (animal.isSleeping()) {
                    View.displayInformationMessage("A " + animal.getSpecieName() + " woke up in the " + enclosure.getName() + " enclosure.\n");
                    animal.wake();
                } else {
                    View.displayInformationMessage("A " + animal.getSpecieName() + " fell asleep in the " + enclosure.getName() + " enclosure.\n");
                    animal.sleep();
                }
                break;

            case "STOLE":
                enclosure.remove(animal);
                View.displayWarningMessage("OMG, a " + animal.getSpecieName() + " was stolen in the " + enclosure.getName() + " enclosure.\n");
                break;

            case "ESCAPE":
                if (animal instanceof MarineAnimal) {
                    // Unless a whale has wings, it won't escape until chickens have golden teeth
                    break;
                } else if (animal instanceof FlyingAnimal) {
                    if (enclosure.getCleanliness() < 1) {
                        enclosure.remove(animal);
                        View.displayWarningMessage("OMG, a " + animal.getSpecieName() + " escaped in the " + enclosure.getName() + " enclosure.\n");
                    }
                    break;
                }
                enclosure.remove(animal);
                View.displayWarningMessage("OMG, a " + animal.getSpecieName() + " escaped in the " + enclosure.getName() + " enclosure.\n");
                break;

            case "FIGHT":
                if (enclosure.getNbAnimals() > 2) {
                    Animal secondAnimal = this.pickRandomAnimal(enclosure);
                    while (animal.equals(secondAnimal)) {
                        secondAnimal = this.pickRandomAnimal(enclosure);
                    }
                    if (secondAnimal != null) {
                        animal.setHealth(animal.getHealth() - this.getRandom().nextInt(1 , animal.getHealth() + 1));
                        secondAnimal.setHealth(animal.getHealth() - this.getRandom().nextInt(1 , animal.getHealth() + 1));

                        View.displayInformationMessage("2 animals fought in the " + enclosure.getName() + " enclosure.\n");
                        if (animal.getHealth() <= 0) {
                            View.displayWarningMessage("The first one died.\n");
                            enclosure.remove(animal);
                        }
                        if (secondAnimal.getHealth() <= 0) {
                            View.displayWarningMessage("The second one died.\n");
                            enclosure.remove(secondAnimal);
                        }
                    }
                }
                break;

            case "COPULATE":
                if (enclosure.getNbAnimals() >= 2 && this.hasAtLeastMaleAndFemale(enclosure)) {
                    Animal secondAnimal = this.pickRandomAnimal(enclosure);

                    // Try to pick another animal until we pick one that is not the same that the first, and that has not the same sex
                    while (animal.equals(secondAnimal) || secondAnimal == null || (animal.getSex() == secondAnimal.getSex())) {
                        secondAnimal = this.pickRandomAnimal(enclosure);
                        // If we do not get the conditions we want, we set them equals, so the checks made in the while condition will make us loop until we find a correct match
                        if (secondAnimal == null || secondAnimal.getSex() == animal.getSex()) {
                            secondAnimal = animal;
                        }
                    }

                    View.displayAnimalActionMessage("Some " + animal.getSpecieName() + " made some adult things in the " + enclosure.getName() + " enclosure.\n");

                    if (animal instanceof Oviparous) {
                        Animal newAnimal;
                        // If its a male, we must call the copulate method on the second animal (the female)
                        if (animal.getSex()) {
                            newAnimal = secondAnimal.copulate(animal, this.getTurnNb());
                            // Try to copulate until we get a valid animal
                            while (newAnimal == null) {
                                newAnimal = secondAnimal.copulate(animal, this.getTurnNb());
                                if (newAnimal != null) {
                                    break;
                                }
                            }
                        } else {
                            newAnimal = animal.copulate(secondAnimal, this.getTurnNb());
                            // Try to copulate until we get a valid animal
                            while (newAnimal == null) {
                                newAnimal = animal.copulate(secondAnimal, this.getTurnNb());
                                if (newAnimal != null) {
                                    break;
                                }
                            }
                        }

                        // If there is enough space in the parent enclosure, put the new animal in it. Else, ask the employee
                        if (enclosure.getNbAnimals() < enclosure.getMaxAnimals()) {
                            enclosure.add(newAnimal);
                        } else {
                            this.displayEnclosureListForNewBirth(newAnimal);
                            this.chooseEnclosureForNewBirth(newAnimal);
                        }
                    } else if (animal instanceof Mammal) {
                        if (animal.getSex()) {
                            ((Mammal)secondAnimal).copulate((Mammal)animal, this.getTurnNb());
                        } else {
                            ((Mammal)animal).copulate((Mammal)secondAnimal, this.getTurnNb());
                        }
                    }
                } else {
                    View.displayInformationMessage("No copulation possible in the " + enclosure.getName() + " enclosure, as there is not enough male/female\n");
                }
                break;

            case "WOLF_ATTACK":
                Animal secondAnimal = this.pickRandomAnimal(enclosure);
                while (animal.equals(secondAnimal)) {
                    secondAnimal = this.pickRandomAnimal(enclosure);
                }
                if (secondAnimal instanceof Wolf && ((Wolf)animal).getImpetuosity() >= ((Wolf)secondAnimal).getImpetuosity() && ((Wolf) secondAnimal).getRank().getId() != 1 && !secondAnimal.getSex()) {
                    ((Wolf)animal).attemptDomination((Wolf)secondAnimal);
                }
                break;

            default:
                break;
        }
    }

    /**
     * Allows to generate a new randomly typed enclosure
     * @return The enclosure type, as an int
     */
    private Enclosure generateRandomEnclosures() {
        // Retrieve in our own array, as each call to values() creates a new array copy (performance hungry)
        AnimalTypes[] values = AnimalTypes.values();
        int animalTypeId = this.getRandom().nextInt(values.length);

        // Retrieve the factory instance that matches the animal type id retrieved
        EnclosureFactory factory = EnclosureFactory.getInstance(values[animalTypeId].getId());
        return factory.createEnclosure(true);
    }

    /**
     * Simulation init method, which basically runs the whole simulation
     */
    public void init() {
        // Beginning of the simulation
        View.displayWelcomeMessage();
        this.nextTurn();
    }
}
