package models.interfaces.animal;

import models.animals.Animal;

/**
 * Represents the oviparous animal interface
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public interface Oviparous {

    /**
     * Makes the animal lay
     * @return The new animal
     */
    Animal lay();

    boolean checkIfHatched(int turnNb);
}
