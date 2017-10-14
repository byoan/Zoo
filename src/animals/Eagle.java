package animals;

import java.util.Random;

public class Eagle extends Animal implements FlyingAnimal, Oviparous {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Eagle() {
        this.specieName = "Eagle";
        this.sex = this.getRandomBoolean();
        this.weight = 30;
        this.size = 1;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = 0;
        this.isHatched = false;
    }

    public Eagle(int copulationTurn) {
        this.specieName = "Eagle";
        this.sex = this.getRandomBoolean();
        this.weight = 30;
        this.size = 1;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Eagle(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = "Eagle";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

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
        if (this.sleepIndicator == false) {
            this.setHunger(100);
            System.out.println("This " + this.specieName + " was fed");
        } else {
            System.out.println("Can't eat while sleeping");
        }
    }

    @Override
    public void scream() {
        System.out.println("An eagle is screaming");
    }

    @Override
    public void heal() {
        this.setHealth(100);
        System.out.println("This " + this.specieName + " was healed");
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
    public boolean isInEnclosure() {
        return this.isInEnclosure;
    }

    @Override
    public void setInEnclosure(boolean isInEnclosure) {
        this.isInEnclosure = isInEnclosure;
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param eagle The animal instance that represents the male
     */
    public void copulate(Eagle eagle, int turnNb) {
        if (eagle.getSex() == this.getSex()) {
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

    public Eagle lay(int turnNb) {
        return new Eagle(turnNb);
    }

    @Override
    public Eagle lay() {
        return new Eagle();
    }

    @Override
    public void fly() {
        System.out.println("An eagle is flying");
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "specieName='" + specieName + '\'' +
                ", sex=" + sex +
                ", weight=" + weight +
                ", size=" + size +
                ", age=" + age +
                ", hungerIndicator=" + hungerIndicator +
                ", sleepIndicator=" + sleepIndicator +
                ", healthIndicator=" + healthIndicator +
                ", copulationTurn=" + copulationTurn +
                ", isHatched=" + isHatched +
                ", childrenCreationTime=" + childrenCreationTime +
                ", isInEnclosure=" + isInEnclosure +
                '}';
    }
}
