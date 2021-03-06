package models.enclosures;

import models.interfaces.animal.FlyingAnimal;
import views.Color;

/**
 * Represents an aviary
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
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
        this.setHeight(height);
        this.setRoofState(roofState);
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
        if (roofState >= 0 && roofState <= 2) {
            this.roofState = roofState;
        }
    }

    /**
     * Allows to deteriorate the cleanliness and the roofState of the aviary without having to specify any value in the Simulation
     */
    @Override
    public void deteriorate() {
        this.setCleanliness(this.getCleanliness() - 1);
        this.setRoofState(this.getRoofState() - 1);
    }

    /**
     * Allows to clean the aviary
     * Will first call the Enclosure's cleaning method
     */
    @Override
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
        return Color.BLUE + Color.UNDERLINE + "Aviary information:\n\n" + Color.DEFAULT +
                "Name: " + Color.BLUE + this.getName() + Color.DEFAULT + "  |  " +
                "Maximum number of animals that the enclosure can contain: " + Color.BLUE + this.getMaxAnimals() + Color.DEFAULT + "\n" +
                "Surface: " + Color.BLUE + this.getSurface() + "m²" + Color.DEFAULT + "  |  " + "Cleanliness status: " + Color.BLUE + ((this.getCleanliness() == 0) ? "Really bad" : (this.getCleanliness() == 1) ? "Bad" : "Good") + Color.DEFAULT + "\n" +

                "Height: " + Color.BLUE + this.getHeight() + "m" + Color.DEFAULT + "  |  " +
                "Roof State: " + Color.BLUE + ((this.getRoofState() == 0) ? "Really bad" : (this.getRoofState() == 1) ? "Bad" : "Good") + Color.DEFAULT + "\n" +
                "\n" + Color.BLUE + Color.UNDERLINE + "Animals currently in the aviary:\n" + Color.DEFAULT + this.getAnimals() + "\n"
                ;
    }
}
