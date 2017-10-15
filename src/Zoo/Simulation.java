package Zoo;

import Core.Enums.RandomActions;
import Core.Factories.AnimalFactory;
import Core.Jobs.CheckNewBirthJob;
import Employees.Employee;
import Enclosures.Aviary;
import Enclosures.Enclosure;
import animals.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Simulation {

    /**
     * Represents the Zoo instance
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
     * Getter for the ThreadLocalRandom instance of the Simulation
     * @return The ThreadLocalRandom instance of the simulation
     */
    public ThreadLocalRandom getRandom() {
        return this.random;
    }

    /**
     * Returns the employee instance
     * @return Employee The employee instance of the Zoo
     */
    private Employee getEmployee() {
        return Zoo.getEmployee();
    }

    /**
     * Returns the current turn number
     * @return int The turn number
     */
    private int getTurnNb() {
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
            System.out.println("Error while trying to set the turn number to " + turnNb + ", can't go back to a previous turn");
        }
    }

    /**
     * Ends the current turn and launches the next one
     */
    private void nextTurn() {
        if (this.getTurnNb() != 0) {
            System.out.println("\nTurn n°" + this.getTurnNb() + " ended.\n");
            this.handleRandomEventGeneration();
            if (this.getTurnNb() % 10 == 0) {
                this.deteriorateEnclosures();
            }
            this.handleNewBirths();
        }

        this.setTurnNb(this.getTurnNb() + 1);

        System.out.println("Turn n°" + this.getTurnNb() + " started.\n");

        this.handleTurn();
    }

    /**
     * Allows to display a list of enclosures available for the new birth
     * Will only display enclosure that have enough space
     */
    private void displayEnclosureListForNewBirth(Animal animal) {
        System.out.println("A new " + animal.getSpecieName() + " is born. Please choose an enclosure to put it in:");
        int i = 0;
        for (Enclosure<Animal> enclosure : this.getZoo().getEnclosureList()) {
            if (enclosure.getNbAnimals() < enclosure.getMaxAnimals()) {
                System.out.println((i + 1) + ". " + enclosure.getName());
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
     */
    private void handleNewBirths() {
        CheckNewBirthJob checkNewBirthJob = new CheckNewBirthJob(this.getZoo().getEnclosureList(), this.getTurnNb());
        checkNewBirthJob.exec();
        ArrayList<Animal> newBirths =  checkNewBirthJob.getNewBirths();

        if (newBirths.size() > 0) {
            for (Animal animal : newBirths) {
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
                    System.out.println("Can't enter a value greater than the maximum enclosure. Please try again:\n");
                    continue;
                }
                Enclosure<Animal> chosenEnclosure = this.getZoo().getEnclosureList().get(action - 1);
                if (chosenEnclosure != null && chosenEnclosure.getNbAnimals() < chosenEnclosure.getMaxAnimals()) {
                    chosenEnclosure.add(animal);
                    System.out.println("Adding the new " + animal.getSpecieName() + " to the " + chosenEnclosure.getName() + " enclosure.\n");
                    correctAnswer = true;
                    break;
                } else {
                    System.out.println("This enclosure is not available. Please chose another one:\n");
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.toString() + ". Please try again:\n");
            }
        }
    }

    /**
     * Allows to randomly generate an event for the current turn
     */
    private void handleRandomEventGeneration() {
        // Random events generation
        Enclosure randomEnclosure = this.pickRandomEnclosure();
        Animal animal = this.pickRandomAnimal(randomEnclosure);
        String randomAction = this.pickRandomAction();

        if (randomEnclosure != null && animal != null) {
            this.handleRandomAction(randomAction, animal, randomEnclosure);
        }
    }

    /**
     * Allows to deteriorate the cleanliness (and specific children values) of the enclosure without having to specify any value
     */
    private void deteriorateEnclosures() {
        for (Enclosure<Animal> enclosure : this.getZoo().getEnclosureList()) {
            enclosure.deteriorate();
        }
        System.out.println("Enclosures were deteriorated. You should look at them.\n");
    }

    /**
     * This method allows to handle all the action that take place during this turn (from the Employee side)
     */
    private void handleTurn() {
        this.displayPickAction();
        int action = 0;

        while (true) {
            String userInput = scanner.next();
            try {
                action = Integer.parseInt(userInput);
                break;
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage() + ". Please try again");
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
                this.doNothing();
                break;
            default:
                System.out.println("Did not understood your action, please try again");
                this.handleTurn();
                break;
        }

        this.nextTurn();
    }

    /**
     * Allows to display to the user all the available action to manipulate its employee
     */
    private void displayPickAction() {
        System.out.println("Choose an action by entering it's number:");
        System.out.println("1. Inspect an enclosure");
        System.out.println("2. Clean an enclosure");
        System.out.println("3. Feed an animal");
        System.out.println("4. Heal an animal");
        System.out.println("5. Transfer an animal");
        System.out.println("6. Do nothing");
    }

    /**
     * Displays the message which matches the "Do Nothing this turn" action
     */
    private void doNothing() {
        System.out.println("Not doing anything this turn ...");
    }

    private <A extends AnimalInterface> void handleAnimalFeeding() {
        Enclosure<A> enclosure = this.pickEnclosure("Select the enclosure containing the animal you want to feed:");
        A animal = this.pickAnimal("Select the animal you want to feed:", enclosure);
        this.getEmployee().feedAnimal(animal);
    }

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
                System.out.println("Can't transfer this animal in its current enclosure. Please choose another one.");
                targetEnclosure = this.pickEnclosure("Select the enclosure in which you would like to send this animal:");
            }

            // Proceed to the transfer
            originEnclosure.transferAnimal(animal, targetEnclosure, false);
        } else {
            System.out.println("No enclosures are currently in the Zoo.\n");
            // Return to the main menu
            this.handleTurn();
            return;
        }
    }

    /**
     * Offers the user to pick an enclosure from the Zoo's enclosure list
     * @param messageToDisplay The message to display before offering options to the user
     * @param <A> Generic type to be used within the method itself
     * @return Enclosure<A> An Enclosure of AnimalInterface
     */
    private <A extends AnimalInterface> Enclosure<A> pickEnclosure(String messageToDisplay) {
        System.out.println(messageToDisplay);
        System.out.println("0. Go back to menu\n");
        int i = 1;
        for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
            System.out.println(i + ". " + enclosure.getName());
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
                    System.out.println("Please enter a valid value");
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage() + ". Please try again");
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
        System.out.println(messageToDisplay);
        System.out.println("0. Go back to menu\n");
        int i = 1;
        for (A animal : enclosure.getAnimals()) {
            System.out.println(i + ". " + animal.toString());
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
                    System.out.println("Please enter a valid value");
                }
            } catch (Exception e) {
                System.out.println("Error : " + e.getMessage() + ". Please try again");
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
            System.out.println("Which enclosure would you like to inspect?");
            System.out.println("0. Go back to menu\n");
            int i = 1;
            for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
                System.out.println(i + ". " + enclosure.getName());
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
                        System.out.println("Please enter a valid value");
                    }
                } catch (Exception e) {
                    System.out.println("Error : " + e.getMessage() + ". Please try again");
                }
            }
            System.out.println("Inspecting the enclosure n°" + action + ":");
            System.out.println(this.getEmployee().inspectEnclosure(this.getZoo().getEnclosureList().get(action - 1)));
        } else {
            System.out.println("No enclosures are currently in the Zoo.\n");
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
            System.out.println("Which enclosure would you like to clean?");
            System.out.println("0. Go back to menu\n");
            int i = 1;
            for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
                System.out.println(i + ". " + enclosure.getName());
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
                        System.out.println("Please enter a valid value");
                    }
                } catch (Exception e) {
                    System.out.println("Error : " + e.getMessage() + ". Please try again");
                }
            }
            System.out.println("Cleaning the enclosure n°" + action + " ...");
            this.getEmployee().cleanEnclosure(this.getZoo().getEnclosureList().get(action - 1));
        } else {
            System.out.println("No enclosures are currently in the Zoo.\n");
            this.handleTurn();
            return;
        }
    }

    /**
     * Returns our Zoo instance
     * @return A Zoo instance
     */
    private Zoo getZoo() {
        return this.zoo;
    }

    /**
     * Setter for our Zoo attribute
     * @param zoo A Zoo instance
     */
    private void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    private <A extends AnimalInterface> Enclosure<A> pickRandomEnclosure() {
        if (this.getZoo().getEnclosureList().size() > 0) {
            int randomEnclosureId = this.getRandom().nextInt(this.getZoo().getEnclosureList().size());
            return this.getZoo().getEnclosureList().get(randomEnclosureId);
        }
        return null;
    }

    private Animal pickRandomAnimal(Enclosure<Animal> enclosure) {
        if (enclosure.getNbAnimals() > 0) {
            int randomAnimalId = this.getRandom().nextInt(enclosure.getNbAnimals());
            return enclosure.getAnimals().get(randomAnimalId);
        }
        return null;
    }

    private String pickRandomAction() {
        // Retrieve in our own array, as each call to values() creates a new array (performance hungry)
        RandomActions[] values = RandomActions.values();
        int actionId = this.getRandom().nextInt(values.length);
        return values[actionId].toString();
    }

    private void handleRandomAction(String action, Animal animal, Enclosure enclosure) {
        switch (action) {
            case "DECREASE_HUNGER":
                animal.setHunger(animal.getHunger() - 80);
                if (animal.getHunger() <= 0) {
                    enclosure.remove(animal);
                    System.out.println("A " + animal.getSpecieName() + " starved to death in the " + enclosure.getName() + " enclosure.\n");
                    animal = null;
                } else {
                    System.out.println("A " + animal.getSpecieName() + " is hungry in the " + enclosure.getName() + " enclosure.\n");
                }
                break;
            case "DECREASE_LIFE":
                animal.setHealth(animal.getHealth() - 80);
                if (animal.getHealth() <= 0) {
                    enclosure.remove(animal);
                    System.out.println("A " + animal.getSpecieName() + " died in the " + enclosure.getName() + " enclosure.\n ");
                    animal = null;
                } else {
                    System.out.println("A " + animal.getSpecieName() + " is hurt/sick in the " + enclosure.getName() + " enclosure.\n");
                }
                break;
            case "SLEEP":
                if (animal.isSleeping()) {
                    animal.setSleeping(false);
                } else {
                    animal.wake();
                }
                break;
            case "STOLE":
                enclosure.remove(animal);
                System.out.println("OMG, a " + animal.getSpecieName() + " was stolen in the " + enclosure.getName() + " enclosure.\n");
                animal = null;
                break;
            case "ESCAPE":
                if (animal instanceof MarineAnimal) {
                    // Unless a whale has wings, it won't escape until chickens have golden teeth
                    break;
                } else if (animal instanceof FlyingAnimal) {
                    if (enclosure.getCleanliness() < 1) {
                        enclosure.remove(animal);
                        System.out.println("OMG, a " + animal.getSpecieName() + " escaped in the " + enclosure.getName() + " enclosure.\n");
                        animal = null;
                    }
                    break;
                }
                enclosure.remove(animal);
                System.out.println("OMG, a " + animal.getSpecieName() + " escaped in the " + enclosure.getName() + " enclosure.\n");
                animal = null;
                break;
            case "FIGHT":
                if (enclosure.getNbAnimals() > 2) {
                    Animal secondAnimal = this.pickRandomAnimal(enclosure);
                    while (animal.equals(secondAnimal)) {
                        secondAnimal = this.pickRandomAnimal(enclosure);
                    }
                    animal.setHealth(animal.getHealth() - this.getRandom().nextInt(1 , animal.getHealth()));
                    secondAnimal.setHealth(animal.getHealth() - this.getRandom().nextInt(1 , animal.getHealth()));
                    System.out.println("2 animals fought in the " + enclosure.getName() + " enclosure.\n");
                    if (animal.getHealth() <= 0) {
                        System.out.println("The first one died.\n");
                        enclosure.remove(animal);
                        animal = null;
                    }
                    if (secondAnimal.getHealth() <= 0) {
                        System.out.println("The second one died.\n");
                        enclosure.remove(secondAnimal);
                        secondAnimal = null;
                    }
                }
                break;
            case "COPULATE":
                if (enclosure.getNbAnimals() >= 2 && this.hasAtLeastMaleAndFemale(enclosure)) {
                    Animal secondAnimal = this.pickRandomAnimal(enclosure);

                    while (secondAnimal.equals(animal) || (animal.getSex() == secondAnimal.getSex())) {
                        secondAnimal = this.pickRandomAnimal(enclosure);
                        if (secondAnimal.getSex() == animal.getSex()) {
                            secondAnimal = animal;
                        }
                    }

                    System.out.println("Some animals made some adult things in the " + enclosure.getName() + " enclosure.\n");

                    if (animal instanceof Oviparous) {
                        Animal newAnimal;
                        if (animal.getSex()) {
                            newAnimal = secondAnimal.copulate(animal, this.getTurnNb());
                            while (newAnimal == null) {
                                newAnimal = secondAnimal.copulate(animal, this.getTurnNb());
                                if (newAnimal != null) {
                                    break;
                                }
                            }
                        } else {
                            newAnimal = animal.copulate(secondAnimal, this.getTurnNb());
                            while (newAnimal == null) {
                                newAnimal = animal.copulate(secondAnimal, this.getTurnNb());
                                if (newAnimal != null) {
                                    break;
                                }
                            }
                        }

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
                    System.out.println("No copulation possible in the " + enclosure.getName() + " enclosure, as there is not enough male/female\n");
                }
                break;
        }
    }

    /**
     * Simulation init method, which basically runs the whole simulation
     */
    public void init() {
        this.setTurnNb(0);
        this.scanner = new Scanner(System.in);
        this.setZoo(new Zoo("My Zoo", this.getEmployee(), 10));

        Enclosure<Tiger> tigerEnclosure = new Enclosure<Tiger>("Tiger Enclosure", 10, 10);
        Aviary<Eagle> eagleEnclosure = new Aviary<Eagle>("Eagle Enclosure", 10, 4, 4, 2);
        Enclosure<Wolf> wolfEnclosure = new Enclosure<Wolf>("Wolf Enclosure", 10, 10);
        Tiger tiger = AnimalFactory.getInstance().createTiger();
        Tiger tiger2 = AnimalFactory.getInstance().createTiger();
        Eagle eagle1 = AnimalFactory.getInstance().createEagle();
        Wolf wolf = AnimalFactory.getInstance().createWolf();

        zoo = this.getZoo();
        zoo.addEnclosure(tigerEnclosure);
        zoo.addEnclosure(eagleEnclosure);
        zoo.addEnclosure(wolfEnclosure);
        zoo.getEnclosureByName("Tiger Enclosure").add(tiger);
        zoo.getEnclosureByName("Tiger Enclosure").add(tiger2);
        zoo.getEnclosureByName("Eagle Enclosure").add(eagle1);
        zoo.getEnclosureByName("Wolf Enclosure").add(wolf);

        // Beginning of the simulation
        System.out.println("\n\n====== WELCOME TO ZOO SIMULATOR 3000 ======\n\n");
        this.nextTurn();
    }
}
