package models.animals;

import models.exceptions.animals.AnimalAlreadyPregnantException;
import models.factories.AnimalFactory;
import models.interfaces.animal.Mammal;
import models.interfaces.animal.WanderAnimal;
import views.View;

/**
 * Represents a tiger animal
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class Tiger extends Animal implements Mammal, WanderAnimal {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Tiger";

    /**
     * Constructor for Tiger
     * Will randomly generate sex, weight and size
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Tiger() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(65000, 310000);
        this.size = this.randomSize(70, 120);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 109;
    }

    /**
     * Constructor for the Tiger
     * @param sex The sex of the tiger
     * @param weight The weight of the tiger
     * @param size The size of the tiger
     * @param age The age of the tiger
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Tiger(boolean sex, float weight, float size, int age) {
        this.specieName = SPECIE_NAME;
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
        View.displayAnimalActionMessage("I'm screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param tiger The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    @Override
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
                View.displayErrorMessage(e.getMessage());
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
        } else if (turnNb - this.getCopulationTurn() == this.getChildrenCreationTime()) {
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
     * @return A new Tiger instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Tiger birth() {
        return AnimalFactory.getInstance().createTiger();
    }

    /**
     * Represents the characteristics of the tiger
     * @return Tiger characteristics information
     */
    @Override
    public String toString() {
        return super.toString() + "   } \n";
    }
}
