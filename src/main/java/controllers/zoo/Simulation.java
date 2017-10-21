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
import views.Lang;
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
            // Run our Jobs on several threads to reduce the time between 2 turns
            this.handleAsyncJobs();

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
     * Allows to run all our systematics jobs simultaneously before 2 turns
     */
    private void handleAsyncJobs() {
        try {
            if (this.getTurnNb() % 50 == 0) {
                MakeAnimalsAgeJob ageJob = new MakeAnimalsAgeJob(this.getZoo(), this.getTurnNb());
                Thread threadAge = new Thread(ageJob);
                threadAge.start();
                threadAge.join();
            }

            // Allows to check for hatching/pregnancy ending at each turn
            CheckNewBirthJob checkNewBirthJob = new CheckNewBirthJob(this.getZoo().getEnclosureList(), this.getTurnNb());
            Thread threadBirths = new Thread(checkNewBirthJob);
            threadBirths.start();

            // Allows to check at each turn if a wolf has 50 or less domination, and must therefore be retrograded to a lower rank
            // Will only retrograde if the wolf is not the only one of his sex for his rank
            CheckDominationFactorJob dominationFactorJob = new CheckDominationFactorJob(this.getZoo().getEnclosureList());
            Thread dominationFactorThread = new Thread(dominationFactorJob);
            dominationFactorThread.start();

            // Join allows to wait for the Job to end before continuing (we need some jobs to execute simultaneously, but we want them to end before starting a new turn)
            threadBirths.join();
            dominationFactorThread.join();

            // We need to handle what the job has returned
            this.handleNewBirths(checkNewBirthJob.getNewBirths());

        } catch (Exception e) {
            View.displayErrorMessage(e.getMessage());
        }
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
     * Allows to handle all the newly born animals, returned from CheckNewBirthJob
     * Will be executed every turn
     */
    private void handleNewBirths(ArrayList<Animal> newBirths) {
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
                    View.displayWarningMessage(Lang.WRONG_ENCLOSURE_NUMBER_USER_INPUT);
                    continue;
                }
                Enclosure<Animal> chosenEnclosure = this.getZoo().getEnclosureList().get(action - 1);
                if (chosenEnclosure != null && chosenEnclosure.getNbAnimals() < chosenEnclosure.getMaxAnimals()) {
                    chosenEnclosure.add(animal);
                    if (chosenEnclosure.getAnimals().contains(animal)) {
                        View.displayInformationMessage("Adding the new " + animal.getSpecieName() + " to the " + chosenEnclosure.getName() + " enclosure.\n");
                        correctAnswer = true;
                    }
                } else {
                    View.displayWarningMessage(Lang.NOT_AVAILABLE_ENCLOSURE);
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
        View.displayWarningMessage(Lang.ENCLOSURES_DETERIORATED);
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
                View.displayWarningMessage(Lang.WRONG_USER_ACTION);
                this.handleTurn();
                break;
        }

        this.nextTurn();
    }

    /**
     * Allows to display the current number of animals in the zoo
     */
    private void handleDisplayCurrentNbAnimalsInZoo() {
        View.displayInformationMessage(Lang.CURRENT_NUMBER_ANIMALS_IN_ZOO + this.getZoo().getNbAnimalsInZoo());
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
        if (!this.getZoo().getEnclosureList().isEmpty()) {
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
            View.displayInformationMessage(Lang.NO_ENCLOSURES_IN_ZOO);
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
        if (!this.getZoo().getEnclosureList().isEmpty()) {
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
            View.displayInformationMessage(Lang.NO_ENCLOSURES_IN_ZOO);
            this.handleTurn();
            return;
        }
    }

    /**
     * Allows the user to pick an enclosure to clean
     * @param <A> Generic type to be used within the method itself
     */
    private <A extends AnimalInterface> void handlePickEnclosureToClean() {
        if (!this.getZoo().getEnclosureList().isEmpty()) {
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
            View.displayConsoleMessage(Lang.NO_ENCLOSURES_IN_ZOO);
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
        if (!this.getZoo().getEnclosureList().isEmpty()) {
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
                this.handleDecreaseHungerAction(animal, enclosure);
                break;

            case "DECREASE_LIFE":
                this.handleDecreaseLifeAction(animal, enclosure);
                break;

            case "SLEEP":
                this.handleSleepAction(animal, enclosure);
                break;

            case "STOLE":
                this.handleSteelAction(animal, enclosure);
                break;

            case "ESCAPE":
                this.handleEscapeAction(animal, enclosure);
                break;

            case "FIGHT":
                this.handleFightAction(animal, enclosure);
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

                    if (animal instanceof Oviparous) {
                        this.handleOviparousCopulation(animal, secondAnimal, enclosure);
                    } else if (animal instanceof Mammal) {
                        this.handleMammalCopulation(animal, secondAnimal, enclosure);
                    }
                } else {
                    View.displayInformationMessage("No copulation possible in the " + enclosure.getName() + " enclosure, as there is not enough male/female\n");
                }
                break;

            case "WOLF_ATTACK":
                this.handleWolfAttack(animal, enclosure);
                break;

            default:
                break;
        }
    }

    /**
     * Allows to handle the randomly picked WOLF_ATTACK action, which will basically make the given wolf attempt a domination on another one
     * @param animal The first wolf involved
     * @param enclosure The enclosure containing the give wolf
     */
    private void handleWolfAttack(Animal animal, Enclosure enclosure) {
        Animal secondAnimal = this.pickRandomAnimal(enclosure);
        while (animal.equals(secondAnimal)) {
            secondAnimal = this.pickRandomAnimal(enclosure);
        }
        if (secondAnimal instanceof Wolf && ((Wolf)animal).getImpetuosity() >= ((Wolf)secondAnimal).getImpetuosity() && ((Wolf) secondAnimal).getRank().getId() != 1 && !secondAnimal.getSex()) {
            ((Wolf)animal).attemptDomination((Wolf)secondAnimal);
        }
    }

    /**
     * Allows to handle the copulation process for Mammals
     * @param animal The first animal involved
     * @param secondAnimal The second animal involved (must have a different sex that the first one)
     * @param enclosure The enclosure containing the 2 animals
     */
    private void handleMammalCopulation(Animal animal, Animal secondAnimal, Enclosure enclosure) {
        View.displayAnimalActionMessage("Some " + animal.getSpecieName() + " made some adult things in the " + enclosure.getName() + " enclosure.\n");
        if (animal.getSex()) {
            ((Mammal)secondAnimal).copulate((Mammal)animal, this.getTurnNb());
        } else {
            ((Mammal)animal).copulate((Mammal)secondAnimal, this.getTurnNb());
        }
    }

    /**
     * Allows to handle the Oviparous copulation process
     * @param animal The first animal involved
     * @param secondAnimal The second animal involved (must have a different sex that the first one)
     * @param enclosure The enclosure containing these 2 animals
     */
    private void handleOviparousCopulation(Animal animal, Animal secondAnimal, Enclosure enclosure) {
        View.displayAnimalActionMessage("Some " + animal.getSpecieName() + " made some adult things in the " + enclosure.getName() + " enclosure.\n");

        Animal newAnimal;
        // If animal is a male, we must call the copulate method on the second animal (the female)
        if (animal.getSex()) {
            newAnimal = secondAnimal.copulate(animal, this.getTurnNb());
        } else {
            newAnimal = animal.copulate(secondAnimal, this.getTurnNb());
        }

        if (newAnimal != null) {
            // If there is enough space in the parent enclosure, put the new animal in it. Else, ask the employee
            if (enclosure.getNbAnimals() < enclosure.getMaxAnimals()) {
                enclosure.add(newAnimal);
            } else {
                this.displayEnclosureListForNewBirth(newAnimal);
                this.chooseEnclosureForNewBirth(newAnimal);
            }
        }
    }

    /**
     * Allows to handle the randomly picked FIGHT action, which will basically make the given animal fight with another animal of its enclosure
     * @param animal The randomly picked animal which will fight
     * @param enclosure The enclosure of the animal, in which its opponent will be picked
     */
    private void handleFightAction(Animal animal, Enclosure enclosure) {
        if (enclosure.getNbAnimals() > 2) {
            Animal secondAnimal = this.pickRandomAnimal(enclosure);
            while (animal.equals(secondAnimal)) {
                secondAnimal = this.pickRandomAnimal(enclosure);
            }
            if (secondAnimal != null) {
                animal.setHealth(animal.getHealth() - this.getRandom().nextInt(0 , animal.getHealth() + 1));
                secondAnimal.setHealth(animal.getHealth() - this.getRandom().nextInt(0 , animal.getHealth() + 1));

                View.displayInformationMessage(Lang.FIGHT_TWO_ANIMALS_FOUGHT + enclosure.getName() + " enclosure.\n");
                if (animal.getHealth() <= 0) {
                    View.displayWarningMessage(Lang.FIGHT_FIRST_ANIMAL_DIED);
                    enclosure.remove(animal);
                }
                if (secondAnimal.getHealth() <= 0) {
                    View.displayWarningMessage(Lang.FIGHT_SECOND_ANIMAL_DIED);
                    enclosure.remove(secondAnimal);
                }
            }
        }
    }

    /**
     * Allows to handle the randomly picked ESCAPE action, which will basically make an animal escape from its enclosure
     * Animals implementing MarineAnimal can't escape
     * Animals implementing FlyingAnimal must have their aviary in a medium-bad cleaning state to be able to escape
     * @param animal The target animal, randomly picked by the simulation
     * @param enclosure The enclosure containing the target animal
     */
    private void handleEscapeAction(Animal animal, Enclosure enclosure) {
        if (animal instanceof MarineAnimal) {
            // Unless a whales or fishes have wings, they won't escape until chickens have golden teeth
            return;
        } else if (animal instanceof FlyingAnimal) {
            if (enclosure.getCleanliness() < 1) {
                enclosure.remove(animal);
                View.displayWarningMessage("OMG, a " + animal.getSpecieName() + " escaped in the " + enclosure.getName() + " enclosure.\n");
            }
            return;
        }
        enclosure.remove(animal);
        View.displayWarningMessage("OMG, a " + animal.getSpecieName() + " escaped in the " + enclosure.getName() + " enclosure.\n");
    }

    /**
     * Allows to handle the randomly picked SLEEP action, which basically will remove an animal from an enclosure, saying that he was stolen
     * @param animal The target animal, randomly picked by the simulation
     * @param enclosure The enclosure containing the target animal
     */
    private void handleSteelAction(Animal animal, Enclosure enclosure) {
        View.displayWarningMessage("OMG, a " + animal.getSpecieName() + " was stolen in the " + enclosure.getName() + " enclosure.\n");
        enclosure.remove(animal);
    }

    /**
     * Allows to handle the randomly picked SLEEP action, which basically will make sleep or wake up the targeted animal
     * @param animal The target animal, randomly picked by the simulation
     * @param enclosure The enclosure containing the target animal
     */
    private void handleSleepAction(Animal animal, Enclosure enclosure) {
        if (animal.isSleeping()) {
            View.displayInformationMessage("A " + animal.getSpecieName() + " woke up in the " + enclosure.getName() + " enclosure.\n");
            animal.wake();
        } else {
            View.displayInformationMessage("A " + animal.getSpecieName() + " fell asleep in the " + enclosure.getName() + " enclosure.\n");
            animal.sleep();
        }
    }

    /**
     * Allows to handle the randomly picked DECREASE_HUNGER action, which basically will make the selected animal hungry
     * Can eventually make him die in case hunger level reaches 0 (thus removing it from its enclosure)
     * @param animal The target animal for the action DECREASE_HUNGER, which has been randomly picked
     * @param enclosure The enclosure which contains the animal
     */
    private void handleDecreaseHungerAction(Animal animal, Enclosure enclosure) {
        animal.setHunger(animal.getHunger() - 80);
        if (animal.getHunger() <= 0) {
            enclosure.remove(animal);
            View.displayWarningMessage("A " + animal.getSpecieName() + " starved to death in the " + enclosure.getName() + " enclosure.\n");
        } else {
            View.displayWarningMessage("A " + animal.getSpecieName() + " is hungry in the " + enclosure.getName() + " enclosure.\n");
        }
    }

    /**
     * Allows to handle the randomly picked DECREASE_LIFE action, which will basically hurt the animal
     * Can eventually make him die in case life level reaches 0 (thus removing it from its enclosure)
     * @param animal The target animal for the action DECREASE_LIFE, which has been randomly picked
     * @param enclosure The enclosure which contains the animal
     */
    private void handleDecreaseLifeAction(Animal animal, Enclosure enclosure) {
        animal.setHealth(animal.getHealth() - 80);
        if (animal.getHealth() <= 0) {
            enclosure.remove(animal);
            View.displayWarningMessage("A " + animal.getSpecieName() + " died in the " + enclosure.getName() + " enclosure.\n ");
        } else {
            View.displayWarningMessage("A " + animal.getSpecieName() + " is hurt/sick in the " + enclosure.getName() + " enclosure.\n");
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
