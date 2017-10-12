package animals;

import java.util.Random;

public class Whale extends Animal implements MarineAnimal, Mammal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    public Whale() {
        this.specieName = "Whale";
        this.sex = this.getRandomBoolean();
        this.weight = 140000;
        this.size = 25;
        this.age = 0;
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

    /**
     * Returns the turn number at which the copulation was done, allowing to compare with the current turn number
     * in order to mesure the gestation
     * @return the turn number
     */
    public int getCopulationTurn() {
        return this.copulationTurn;
    }

    /**
     * Sets the turn number at which the pregnancy started
     */
    public void setCopulationTurn(int turnNumber) {
        this.copulationTurn = turnNumber;
    }

    /**
     * Animal generic method to feed the current instance
     */
    @Override
    public void eat() {
        if (this.sleepIndicator != false) {
            this.setHunger(100);
        } else {
            System.out.println("Can't eat while sleeping");
        }
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        System.out.println("I'm screaming");
    }

    /**
     * Animal generic method to heal the current animal instance
     */
    @Override
    public void heal() {
        this.setHealth(100);
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
    public void swim() {

    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param whale The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public void copulate(Whale whale, int turnNb) {
        if (whale.getSex() == this.getSex()) {
            System.out.println("Same sex can't copulate");
        } else {
            if (this.getSex() == false) {
                if (this.getCopulationTurn() <= 0) {
                    this.setCopulationTurn(turnNb);
                } else {
                    // @TODO
                    // Throw exception
                    System.out.println("This animal is already pregnant");
                }
            } else {
                // @TODO
                // Throw exception
                System.out.println("Man can't be pregnant");
            }
        }
    }

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public void checkBirth(int turnNb) {
        if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime()) {
            this.birth();
        }
    }

    /**
     * Generic method who say if animal wandering or not
     */
    public void wander() {
        System.out.println("I'm wandering...");
    }

    /**
     * Represents the birth of the children
     * @return
     */
    @Override
    public Whale birth() {
        this.setCopulationTurn(0);
        return new Whale();
    }

    @Override
    public String toString() {
        return "Whale{" +
                "specieName='" + specieName + '\'' +
                ", sex=" + sex +
                ", weight=" + weight +
                ", size=" + size +
                ", age=" + age +
                ", hungerIndicator=" + hungerIndicator +
                ", sleepIndicator=" + sleepIndicator +
                ", healthIndicator=" + healthIndicator +
                ", copulationTurn=" + copulationTurn +
                ", childrenCreationTime=" + childrenCreationTime +
                '}';
    }
}
