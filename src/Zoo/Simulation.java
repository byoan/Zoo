package Zoo;

import Core.Factories.AnimalFactory;
import Employees.Employee;
import Enclosures.Enclosure;
import animals.AnimalInterface;
import animals.Tiger;
import animals.Wolf;

import java.util.Scanner;

public class Simulation {

    private Zoo zoo;
    private int turnNb;
    Scanner scanner;

    private Employee getEmployee() {
        return Zoo.getEmployee();
    }

    private int getTurnNb() {
        return this.turnNb;
    }

    private void setTurnNb(int turnNb) {
        // Add this test to prevent going back to a previous turn
        if (turnNb >= this.getTurnNb()) {
            this.turnNb = turnNb;
        } else {
            System.out.println("Error while trying to set the turn number to " + turnNb + ", can't go back to a previous turn");
        }
    }

    private void nextTurn() {
        if (this.getTurnNb() != 0) {
            System.out.println("\nTurn n°" + this.getTurnNb() + " ended.");
        }
        this.setTurnNb(this.getTurnNb() + 1);
        System.out.println("Turn n°" + this.getTurnNb() + " started.\n");
        // Execute events randomization here
        this.handleTurn();
    }

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
                this.handleAnimalTransfer();
                break;
            case 4:
                this.doNothing();
                break;
            default:
                System.out.println("Did not understood your action, please try again");
                this.handleTurn();
                break;
        }

        this.nextTurn();
    }

    private void displayPickAction() {
        System.out.println("Choose an action by entering it's number:");
        System.out.println("1. Inspect an enclosure");
        System.out.println("2. Clean an enclosure");
        System.out.println("3. Transfer an animal");
        System.out.println("4. Do nothing");
    }

    private void doNothing() {
        System.out.println("Not doing anything this turn ...");
    }

    private <A extends AnimalInterface> void handleAnimalTransfer() {
        if (this.getZoo().getEnclosureList().size() > 0) {
            Enclosure<A> originEnclosure = this.pickEnclosure("Select the enclosure containing the animal you want to transfer:");
            A animal = this.pickAnimal("Select the animal you want to transfer:", originEnclosure);
            Enclosure<A> targetEnclosure = this.pickEnclosure("Select the enclosure in which you would like to send this animal:");

            while (originEnclosure.equals(targetEnclosure)) {
                System.out.println("Can't transfer this animal in its current enclosure. Please choose another one.");
                targetEnclosure = this.pickEnclosure("Select the enclosure in which you would like to send this animal:");
            }

            originEnclosure.transferAnimal(animal, targetEnclosure, false);
        } else {
            System.out.println("No enclosures are currently in the Zoo.\n");
            this.handleTurn();
            return;
        }
    }

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

    private Zoo getZoo() {
        return this.zoo;
    }

    private void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

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
