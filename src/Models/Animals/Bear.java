package Models.Animals;

import Models.Exceptions.Animals.AnimalAlreadyPregnantException;
import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.Mammal;
import Views.View;

public class Bear extends Animal implements Mammal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    public Bear() {
        this.specieName = "Bear";
        this.sex = this.getRandomBoolean();
        this.weight = 80;
        this.size = 2;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 180;
    }

    public Bear(boolean sex, float weight, float size, int age) {
        this.specieName = "Bear";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        // Default ones
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 180;
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayMessage("A bear is screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param bear The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public <A extends Mammal> void copulate(A bear, int turnNb) {
        // Same sex can't copulate
        if (bear.getSex() != this.getSex()) {
            try {
                if (this.getCopulationTurn() == 0) {
                    this.setCopulationTurn(turnNb);
                } else {
                    throw new AnimalAlreadyPregnantException(this);
                }
            } catch (AnimalAlreadyPregnantException e) {
                View.displayMessage(e.getMessage());
            }
        }
    }

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public Bear checkBirth(int turnNb) {
        if (this.getCopulationTurn() == 0) {
            return null;
        } else if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime()) {
            this.setCopulationTurn(0);
            return this.birth();
        }
        return null;
    }

    /**
     * Represents the birth of the children
     * @return A new Bear instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Bear birth() {
        return AnimalFactory.getInstance().createBear();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
