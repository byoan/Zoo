package models.interfaces.animal;

import models.animals.Animal;

/**
 * Represents the animal interface
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public interface AnimalInterface {

    /**
     * Allows to make the animal older at some specific times of the simulation
     */
    void getOlder();

    /**
     * Getter for the age of the animal (1 Young, 2 Adult, 3 Old)
     * @return The age of the animal
     */
    int getAge();

    /**
     * Makes the animal eat
     */
    void eat();

    /**
     * Makes the animal scream
     */
    void scream();

    /**
     * Heals the animal
     */
    void heal();

    /**
     * Makes the animal sleep
     */
    void sleep();

    /**
     * Awakes the animal
     */
    void wake();

    /**
     * Getter for the isInEnclosure attributes, which indicates us if the animal is currently in an enclosure
     * @return Whether or not the animal is in an enclosure
     */
    boolean isInEnclosure();

    /**
     * Setter for the isInEnclosure attribute
     * Called when added or removed from an Enclosure
     * @param isInEnclosure Whether or not it is now in an enclosure
     */
    void setInEnclosure(boolean isInEnclosure);

    <A extends AnimalInterface> Animal copulate(A animal, int turnNb);

    boolean getSex();

    String getSpecieName();
}
