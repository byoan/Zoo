package animals;

public class Bear extends Animal implements Mammal {

    public Bear() {
        this.specieName = "Bear";
        this.sex = true;
        this.weight = 80;
        this.size = 2;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 180;
    }

    public Bear(boolean sex, float weight, float size, int age) {
        this.specieName = "Bear";
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
    @Override
    public void eat() {

    /**
     * Allows to randomly generate a boolean, used for the sex definition in case of a 'natural' birth
     * @return the randomly generated sex
     */
    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
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
     * Animal generic method to feed the current instance
     */
    @Override
    public void eat() {
        this.setHunger(100);
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

    }

    @Override
    public void birth() {
        if (this.sex == true) {
            return;
        }
    }
}
