package Models.Animals;

import Models.Exceptions.Animals.AnimalAlreadyPregnantException;
import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.Mammal;
import Models.Interfaces.Animal.MarineAnimal;
import Views.View;

public class Whale extends Animal implements MarineAnimal, Mammal {

    public Whale() {
        this.specieName = "Whale";
        this.sex = this.getRandomBoolean();
        this.weight = randomWeight(20000000, 30000000);
        this.size = randomSize(1300, 1600);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }

    public Whale(boolean sex, float weight, float size, int age) {
        this.specieName = "Whale";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        //Default ones
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayAnimalActionMessage("I'm screaming");
    }

    /**
     * Animal generic method to swim
     */
    @Override
    public void swim() {
        View.displayAnimalActionMessage("A whale is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param whale The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public <A extends Mammal> void copulate(A whale, int turnNb) {
        // Same sex can't copulate
        if (whale.getSex() != this.getSex()) {
            try {
                if (this.getCopulationTurn() == 0) {
                    this.setCopulationTurn(turnNb);
                } else {
                    throw new AnimalAlreadyPregnantException(this);
                }
            } catch (AnimalAlreadyPregnantException e) {
                View.displayErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public Whale checkBirth(int turnNb) {
        if (this.getCopulationTurn() == 0) {
            return null;
        } else if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime()) {
            this.setCopulationTurn(0);
            return this.birth();
        }
        return null;
    }

    /**
     * Generic method who say if animal wandering or not
     */
    public void wander() {
        View.displayAnimalActionMessage("I'm wandering...");
    }

    /**
     * Represents the birth of the children
     * @return A new Whale instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Whale birth() {
        return AnimalFactory.getInstance().createWhale();
    }

    @Override
    public String toString() {
        return super.toString() + "   } \n";
    }
}
