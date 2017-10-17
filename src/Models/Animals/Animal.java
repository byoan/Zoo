package Models.Animals;

import Models.Interfaces.Animal.AnimalInterface;
import Models.Interfaces.Animal.Mammal;
import Views.View;

import java.util.Random;

public class Animal implements AnimalInterface {

    /**
     * Represents the age of an animal
     * 1 = young, 2 = adult, 3 = old
     */
    public int ageAnimal = 1;

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    protected String specieName;
    protected boolean sex; // True if male
    protected float weight;
    protected float size;
    protected int age;
    protected int hungerIndicator;
    protected boolean sleepIndicator;
    protected int healthIndicator; // Percentage
    protected int childrenCreationTime;
    protected boolean isInEnclosure = false;

    /**
     * Returns the age of the animal ( 1 = young, 2 = adult, 3 = old)
     * @return The age of the animal
     */
    public int getAge() {
        return this.ageAnimal;
    }

    /**
     * Setter for the age of the animal
     */
    public void setAge(int age) {
        if (age >= 1 && age <= 3) {
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
     * Setter for the animal age
     * @param ageAnimal The new animal age. Can be 1, 2 or 3 for Young, Adult and Old
     */
    public void setAgeAnimal(int ageAnimal) {
        this.ageAnimal = ageAnimal;
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
     * @param sex The new sex of the Animal (true for male, false for female)
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
    public void setCopulationTurn(int turnNumber) {
        this.copulationTurn = turnNumber;
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
        return specieName;
    }

    /**
     * Setter for the specie name of the animal
     * @param specieName The new specie name
     */
    public void setSpecieName(String specieName) {
        this.specieName = specieName;
    }

    /**
     * Animal generic method to feed the current instance
     */
    @Override
    public void eat() {
        if (this.sleepIndicator == false) {
            this.setHunger(100);
        } else {
            View.displayMessage("A " +  this.getSpecieName() + " can't eat while sleeping");
        }
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        View.displayMessage("A bear is screaming");
    }

    /**
     * Animal generic method to heal the current animal instance
     */
    @Override
    public void heal() {
        this.setHealth(100);
        View.displayMessage("This " + this.specieName + " was healed");
    }

    /**
     * Animal generic method to put the current animal into sleep
     */
    @Override
    public void sleep() {
        this.setSleeping(true);
    }

    /**
     * Animal generic method to put the current animal instance awake
     */
    @Override
    public void wake() {
        this.setSleeping(false);
    }

    @Override
    public boolean isInEnclosure() {
        return this.isInEnclosure;
    }

    @Override
    public void setInEnclosure(boolean isInEnclosure) {
        this.isInEnclosure = isInEnclosure;
    }

    @Override
    public <A extends AnimalInterface> Animal copulate(A animal, int turnNb) {
        return null;
    }

    public <A extends Mammal> void copulate(A animal, int turnNb) {

    }
}
