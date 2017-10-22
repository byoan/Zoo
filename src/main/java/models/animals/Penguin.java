package models.animals;

import models.factories.AnimalFactory;
import models.interfaces.animal.AnimalInterface;
import models.interfaces.animal.FlyingAnimal;
import models.interfaces.animal.MarineAnimal;
import models.interfaces.animal.Oviparous;
import views.Color;
import views.View;

/**
 * Represents a penguin animal
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class Penguin extends Animal implements MarineAnimal, Oviparous, FlyingAnimal {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Penguin";

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    /**
     * Constructor for the Penguin
     * Will randomly generate sex, weight and size
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Penguin() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(500, 700);
        this.size = this.randomSize(37, 39);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = 0;
        this.childrenCreationTime = 30;
        this.isHatched = true;
    }

    /**
     * Constructor for the Penguin
     * @param copulationTurn The turn number at which the copulation took place
     * Will randomly generate sex, weight and size
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Penguin(int copulationTurn) {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(500, 700);
        this.size = this.randomSize(37, 39);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = copulationTurn;
        this.childrenCreationTime = 30;
        this.isHatched = false;
    }

    /**
     * Constructor for the Penguin
     * @param sex The sex of the penguin
     * @param weight The weight of the penguin
     * @param size The size of the penguin
     * @param age The age of the penguin
     * @param copulationTurn The copulation turn of the penguin
     * Age, hunger, sleep, health and children creation time will always be the same
     */
    public Penguin(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = SPECIE_NAME;
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    /**
     * Getter for hatched status
     * @return Return hatched status
     */
    public boolean getIsHatched() {
        return this.isHatched;
    }

    /**
     * Setter for the isHatched attribute, which represents whether or not the Animal has hatched or is still in an egg
     * @param value The new isHatched value to assign
     */
    public void setHatched(boolean value) {
        this.isHatched = value;
    }

    /**
     * Checks if the current animal is hatched, using its creation turn and the required amount of time to hatch
     * @param turnNb The current turn number of the simulation
     */
    public boolean checkIfHatched(int turnNb) {
        if (this.getIsHatched()) {
            return true;
        } else if (turnNb - this.getCopulationTurn() == this.getChildrenCreationTime()) {
            View.displaySuccessMessage(Color.BOLD + "A penguin hatched !\n" + Color.DEFAULT);
            this.setHatched(true);
        }
        return this.getIsHatched();
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayAnimalActionMessage("A penguin is screaming");
    }

    /**
     * Animal generic method to swim
     */
    @Override
    public void swim() {
        View.displayAnimalActionMessage("A penguin is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param penguin The animal instance that represents the male
     */
    @Override
    public <A extends AnimalInterface> Animal copulate(A penguin, int turnNb) {
        // Same sex can't copulate
        if (penguin.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (!this.getSex()) {
                return AnimalFactory.getInstance().createPenguin(turnNb);
            } else {
                return null;
            }
        }
    }

    /**
     * Animal generic method to lay
     * @return A new penguin instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Penguin lay() {
        return AnimalFactory.getInstance().createPenguin();
    }

    /**
     * Animal generic method to fly
     */
    @Override
    public void fly() {
        View.displayAnimalActionMessage("A penguin is flying");
    }

    /**
     * Represents the characteristics of the penguin
     * @return penguin characteristics information
     */
    @Override
    public String toString() {
        return super.toString()  + "       Already hatched: " + Color.BLUE + ((this.getIsHatched()) ? "Yes" : "No") + Color.DEFAULT + "     }\n";
    }
}
