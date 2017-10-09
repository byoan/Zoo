package animals;

import java.util.Random;

public class Pinguin extends Animal implements MarineAnimal, Oviparous, FlyingAnimal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Pinguin() {
        this.specieName = "Pinguin";
        this.sex = true;
        this.weight = 3;
        this.size = 0.05f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = 0;
        this.isHatched = false;
    }

    public Pinguin(int copulationTurn) {
        this.specieName = "Pinguin";
        this.sex = true;
        this.weight = 3;
        this.size = 0.5f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Pinguin(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = "Pinguin";
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
     * Allows to set the current state of hatching of our Animal instance
     */
    public void setHatched(boolean value) {
        this.isHatched = value;
    }

    /**
     * Checks if the current animal is hatched, using its creation turn and the required amount of time to hatch
     * @param turnNb
     */
    public boolean checkIfHatched(int turnNb) {
        if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime() || this.getCopulationTurn() == 0) {
            this.setHatched(true);
        }
        return this.isHatched;
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
     * @param sex
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

    @Override
    public void eat() {
        if (this.sleepIndicator != false) {
            this.setHunger(100);
        } else {
            System.out.println("Can't eat while sleeping");
        }
    }

    @Override
    public void scream() {
        System.out.println("A pinguin is screaming");
    }

    @Override
    public void heal() {
        this.setHealth(100);
    }

    @Override
    public void sleep() {
        this.setSleeping(true);
    }

    @Override
    public void wake() {
        this.setSleeping(false);
    }

    @Override
    public void swim() {
        System.out.println("A pinguin is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param pinguin The animal instance that represents the male
     */
    public void copulate(Pinguin pinguin, int turnNb) {
        if (pinguin.getSex() == this.getSex()) {
            System.out.println("Same sex can't copulate");
        } else {
            if (this.getSex() == false) {
                this.lay(turnNb);
            } else {
                // @TODO
                // Throw exception
                System.out.println("Man can't be pregnant");
            }
        }
    }

    public Pinguin lay(int turnNb) {
        return new Pinguin(turnNb);
    }

    @Override
    public Pinguin lay() {
        return new Pinguin();
    }

    @Override
    public void fly() {
        System.out.println("A pinguin is flying");
    }
}
