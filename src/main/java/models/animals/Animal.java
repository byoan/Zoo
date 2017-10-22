package models.animals;

import models.interfaces.animal.AnimalInterface;
import models.interfaces.animal.Mammal;
import views.Color;
import views.View;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Animal implements AnimalInterface {

    /**
     * Represents the age of an animal
     * 1 = young, 2 = adult, 3 = old
     */
    protected int age = 1;

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    protected int copulationTurn;

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    protected String specieName;

    /**
     * Represents the sex of the Animal, where true equals Male, and false equals Female
     */
    protected boolean sex;

    /**
     * Represents the weight of the Animal, in grammes
     */
    protected float weight;

    /**
     * Represents the size of the Animal, in centimeters
     */
    protected float size;

    /**
     * Represents the current hunger level of an animal, as a percentage, where 1 is very hungry, and 100 is not hungry
     */
    protected int hungerIndicator;

    /**
     * Represents the current sleep state of the Animal, having true for currently sleeping, and false for awake
     */
    protected boolean sleepIndicator;

    /**
     * Represents the current health of the animal, as a percentage, where 1 is low health and 100 is full health
     */
    protected int healthIndicator;

    /**
     * Represents the time required for a child to birth/hatch, as a number of turns
     */
    protected int childrenCreationTime;

    /**
     * Represents a boolean indicating whether or not the animal is currently in an enclosure
     */
    protected boolean isInEnclosure = false;

    /**
     * Weight generator between min and max for each animals
     * @return A randomly generated number
     */
    public int randomWeight(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Size generator between min and max for each animals
     * @return A randomly generated number
     */
    public int randomSize(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Getter for the animal size attribute
     * @return The animal size in cm
     */
    public float getSize() {
        return this.size;
    }

    /**
     * Getter the weight of an animal
     * @return The weight of an animal in kg
     */
    public float getWeight() {
        return this.weight;
    }

    /**
     * Returns the age of the animal ( 1 = young, 2 = adult, 3 = old)
     * @return The age of the animal
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Setter for the animal age
     * @param age The new animal age. Can be 1, 2 or 3 for Young, Adult and Old. If given a greater value, the animal will die at the next turn
     */
    public void setAge(int age) {
        if (age >= 1) {
            this.age = age;
        }
    }

    /**
     * Allows to make the animal get older
     * Will increment to the next step, if not at the maximum value, which is 3
     */
    public void getOlder() {
        this.setAge(this.getAge() + 1);
    }

    /**
     * Allows to randomly generate a boolean, used for the sex definition in case of a 'natural' birth
     * @return the randomly generated sex
     */
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Returns the gestation time for the current animal instance
     * @return the gestation time
     */
    public int getChildrenCreationTime() {
        return this.childrenCreationTime;
    }

    /**
     * Returns the sex of the current animal
     * @return the current sex
     */
    public boolean getSex() {
        return this.sex;
    }

    /**
     * Sets the sex of the current animal
     * @param sex The new sex of the animal (true for male, false for female)
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    /**
     * Returns the hunger level of the current animal
     * @return the hunger level
     */
    public int getHunger() {
        return this.hungerIndicator;
    }

    /**
     * Allows to set the hunger level of the current animal
     * @param newHungerLevel The desired hunger level to apply
     */
    public void setHunger(int newHungerLevel) {
        this.hungerIndicator = newHungerLevel;
    }

    /**
     * Returns the current health level
     * @return the health level
     */
    public int getHealth() {
        return this.healthIndicator;
    }

    /**
     * Allows to update the health level
     * @param newHealthLevel the new health level
     */
    public void setHealth(int newHealthLevel) {
        this.healthIndicator = newHealthLevel;
    }

    /**
     * Sets the turn number at which the pregnancy started
     */
    public void setCopulationTurn(int turnNb) {
        this.copulationTurn = turnNb;
    }

    /**
     * Returns the turn number at which the copulation was done, allowing to compare with the current turn number
     * in order to mesure the gestation
     * @return the turn number
     */
    public int getCopulationTurn() {
        return this.copulationTurn;
    }

    /**
     * Gets the current sleep state
     * @return whether the animal sleeps or not
     */
    public boolean isSleeping() {
        return this.sleepIndicator;
    }

    /**
     * Allows to set the sleeping status to the one given in param
     * @param status the new sleep state
     */
    public void setSleeping(boolean status) {
        this.sleepIndicator = status;
    }

    /**
     * Getter for the specie name of the animal
     * @return The specie name
     */
    public String getSpecieName() {
        return this.specieName;
    }

    /**
     * Setter for the specie name of the animal
     * @param specieName The new specie name
     */
    public void setSpecieName(String specieName) {
        this.specieName = specieName;
    }

    /**
     * animal generic method to feed the current instance
     */
    @Override
    public void eat() {
        if (!this.isSleeping()) {
            this.setHunger(100);
        } else {
            View.displayInformationMessage("A " +  this.getSpecieName() + " can't eat while sleeping");
        }
    }

    /**
     * animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayAnimalActionMessage("A bear is screaming");
    }

    /**
     * animal generic method to heal the current animal instance
     */
    @Override
    public void heal() {
        this.setHealth(100);
        View.displaySuccessMessage("This " + this.specieName + " was healed");
    }

    /**
     * animal generic method to put the current animal into sleep
     */
    @Override
    public void sleep() {
        this.setSleeping(true);
    }

    /**
     * animal generic method to put the current animal instance awake
     */
    @Override
    public void wake() {
        this.setSleeping(false);
    }

    /**
     * Getter for the current enclosure belonging
     * @return Whether the animal is currently in an Enclosure or not
     */
    @Override
    public boolean isInEnclosure() {
        return this.isInEnclosure;
    }

    /**
     * Setter which allows us to mark the current animal instance as in an enclosure or not
     * @param isInEnclosure Whether or not it is now in an enclosure
     */
    @Override
    public void setInEnclosure(boolean isInEnclosure) {
        this.isInEnclosure = isInEnclosure;
    }

    /**
     * Allows a copulation between 2 animals
     * Will be redefined by all children classes
     * @param animal The animal which will perform the copulation with the animal that calls the method
     * @param turnNb The current turn number
     * @param <A> Generic type used for the animals parameter, which is here a type extending AnimalInterface
     */
    @Override
    public <A extends AnimalInterface> Animal copulate(A animal, int turnNb) {
        return null;
    }

    /**
     * Allows a copulation between Mammals specifically (the behaviour is the same, we're just using it for the typing)
     * @param animal The animal which will perform the copulation with the animal that calls the method
     * @param turnNb The current turn number
     * @param <A> Generic type used for the animals parameter, which is here a type extending Mammal
     */
    public <A extends Mammal> void copulate(A animal, int turnNb) {
        // This method will be redefined by all the Mammals of the simulation, as they have a Mammal's specific behaviour
    }

    @Override
    public String toString() {
        return  "\n" + "   Animal {"+ "\n" +"       Specie: " + Color.BLUE + this.getSpecieName() + Color.DEFAULT + "   |  " + "Is in an enclosure : " + Color.BLUE + ((this.isInEnclosure()) ? "Yes" : "No") + Color.DEFAULT + " \n" +
                "       Hunger: " + Color.BLUE + this.getHunger() + "%" + Color.DEFAULT + "    |  " + "Currently sleeping: " + Color.BLUE + ((this.isSleeping()) ? "Yes" : "No") + Color.DEFAULT + "  |  " +
                "Health: " + Color.BLUE + this.getHealth() + "%" + Color.DEFAULT + " \n" +
                "       Sex: " + Color.BLUE + ((this.getSex()) ? "Male" : "Female") + Color.DEFAULT + "     |  " + "Weight: " + Color.BLUE + this.getWeight() + "g" + Color.DEFAULT + "  |  " +
                "Size: " + Color.BLUE + this.getSize() + "cm" + Color.DEFAULT + "  |  " + "Age: " + Color.BLUE + ((this.getAge() == 1) ? "Young" : (this.getAge() == 2) ? "Adult" : "Old") + Color.DEFAULT + " \n"  +
                "       Started pregnancy at turn: " + Color.BLUE + ((this.getCopulationTurn() != 0) ? this.getCopulationTurn() : "-") + Color.DEFAULT + "  |  " + "Pregnancy duration: " + Color.BLUE + this.getChildrenCreationTime() + " turns" + Color.DEFAULT + " \n"
                ;
    }
}
