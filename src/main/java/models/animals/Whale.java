package models.animals;

import models.exceptions.animals.AnimalAlreadyPregnantException;
import models.factories.AnimalFactory;
import models.interfaces.animal.Mammal;
import models.interfaces.animal.MarineAnimal;
import views.View;

/**
 * Represents a bear animal
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class Whale extends Animal implements MarineAnimal, Mammal {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Whale";

    /**
     * Constructor for the Whale
     * Will randomly generate sex, weight and size
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Whale() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = randomWeight(20000000, 30000000);
        this.size = randomSize(1300, 1600);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }

    /**
     * Constructor for Whale
     * @param sex The sex of the whale
     * @param weight The weight of the whale
     * @param size The size of the whale
     * @param age The age of the whale
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Whale(boolean sex, float weight, float size, int age) {
        this.specieName = SPECIE_NAME;
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        // Default ones
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
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param whale The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    @Override
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
     * @return A new Whale instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Whale birth() {
        return AnimalFactory.getInstance().createWhale();
    }

    /**
     * Represents the characteristics of th whale
     * @return Whale characteristics information
     */
    @Override
    public String toString() {
        return super.toString() + "   } \n";
    }
}
