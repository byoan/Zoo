package Zoo;

import Core.Factories.AnimalFactory;
import Employees.Employee;
import Enclosures.Enclosure;
import animals.AnimalInterface;
import animals.Tiger;
import animals.Wolf;

import java.util.Scanner;

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
            System.out.println("\nTurn n°" + this.getTurnNb() + " ended.");
        }
        this.setTurnNb(this.getTurnNb() + 1);
        System.out.println("Turn n°" + this.getTurnNb() + " started.\n");
        // Execute events randomization here
        this.handleTurn();
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

    /**
     * Simulation init method, which basically runs the whole simulation
     */
    public void init() {
        this.setTurnNb(0);
        this.scanner = new Scanner(System.in);
        this.setZoo(new Zoo("My Zoo", this.getEmployee(), 2));

        Enclosure<Tiger> tigerEnclosure = new Enclosure<Tiger>("Tiger Enclosure", 10, 2);
        Enclosure<Wolf> wolfEnclosure = new Enclosure<Wolf>("Wolf Enclosure", 10, 10);
        Tiger tiger = AnimalFactory.getInstance().createTiger();
        Wolf wolf = AnimalFactory.getInstance().createWolf();

        zoo = this.getZoo();
        zoo.addEnclosure(tigerEnclosure);
        zoo.addEnclosure(wolfEnclosure);
        zoo.getEnclosureByName("Tiger Enclosure").add(tiger);
        zoo.getEnclosureByName("Wolf Enclosure").add(wolf);

        // Beginning of the simulation
        System.out.println("\n\n====== WELCOME TO ZOO SIMULATOR 3000 ======\n\n");
        this.nextTurn();
    }
}
