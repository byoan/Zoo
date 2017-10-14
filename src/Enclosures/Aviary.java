package Enclosures;

import animals.FlyingAnimal;

public class Aviary<A extends FlyingAnimal> extends Enclosure {

    /**
     * Represents the height of the aviary
     */
    private int height;

    /**
     * Represents the roof current cleanliness
     * 0 = bad, 1 = correct, 2 = good
     */
    private int roofState;

    /**
     * Constructor for the Aviary
     * @param name The name of the aviary
     * @param surface The surface of the aviary
     * @param maxAnimals The maximum number of animals that the aviary can contain
     * @param height The height of the aviary
     * @param roofState The roof state cleanliness (0 = bad, 1 = correct, 2 = good)
     */
    public Aviary(String name, int surface, int maxAnimals, int height, int roofState) {
        super(name, surface, maxAnimals);
        this.height = height;
        this.roofState = roofState;
    }

    /**
     * Getter for the aviary height attribute
     * @return The value of the aviary height attribute
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Setter for the aviary height attribute
     * @param height The new aviary height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Getter for the current roof state cleanliness
     * 0 = bad, 1 = correct, 2 = good
     * @return The current roof state value
     */
    public int getRoofState() {
        return this.roofState;
    }

    /**
     * Setter of the current roof state cleanliness
     * @param roofState The new roof state cleanliness value
     */
    public void setRoofState(int roofState) {
        this.roofState = roofState;
    }

    /**
     * Allows to clean the aviary
     * Will first call the Enclosure's cleaning method
     */
    public void cleanEnclosure() {
        super.cleanEnclosure();
        if (this.getRoofState() <= 1) {
            this.setRoofState(2);
        }
    }

    /**
     * Allows to display all the aviary's properties
     * @return A concatenated string containing the aviary properties
     */
    @Override
    public String toString() {
        return super.toString() + "Aviary{" +
                "height=" + height +
                ", roofState=" + roofState +
                '}';
    }
}
