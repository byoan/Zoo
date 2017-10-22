package models.animals;

import models.exceptions.animals.AnimalAlreadyPregnantException;
import models.factories.AnimalFactory;
import models.interfaces.animal.Mammal;
import views.View;

/**
 * Represents a bear animal
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class Bear extends Animal implements Mammal {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Bear";

    /**
     * Constructor for the Bear
     * Will randomly generate sex, weight and size
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Bear() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(80000, 600000);
        this.size = this.randomSize(70, 150);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 180;
    }

    /**
     * Constructor for the Bear
     * @param sex The age of the bear
     * @param weight The weight of the bear
     * @param size The size of the bear
     * @param age The age of the bear
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Bear(boolean sex, float weight, float size, int age) {
        this.specieName = SPECIE_NAME;
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
     * animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayInformationMessage("A bear is screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param bear The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    @Override
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
                View.displayErrorMessage(e.getMessage());
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
        } else if (turnNb - this.getCopulationTurn() == this.getChildrenCreationTime()) {
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

    /**
     * Represents the characteristics of th bear
     * @return Bear characteristics information
     */
    @Override
    public String toString() {
        return super.toString() + "   } \n";
    }
}
