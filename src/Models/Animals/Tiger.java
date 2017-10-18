package Models.Animals;

import Models.Exceptions.Animals.AnimalAlreadyPregnantException;
import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.Mammal;
import Models.Interfaces.Animal.WanderAnimal;
import Views.View;

public class Tiger extends Animal implements Mammal, WanderAnimal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    public Tiger() {
        this.specieName = "Tiger";
        this.sex = this.getRandomBoolean();
        this.weight = 180;
        this.size = 100;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 109;
    }

    public Tiger(boolean sex, float weight, float size, int age) {
        this.specieName = "Tiger";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        // Default ones
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 109;
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayMessage("I'm screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param tiger The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public <A extends Mammal> void copulate(A tiger, int turnNb) {
        // Same sex can't copulate
        if (tiger.getSex() != this.getSex()) {
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
    public Tiger checkBirth(int turnNb) {
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
        View.displayMessage("I'm wandering...");
    }

    /**
     * Represents the birth of the children
     * @return A new Tiger instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Tiger birth() {
        return AnimalFactory.getInstance().createTiger();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
