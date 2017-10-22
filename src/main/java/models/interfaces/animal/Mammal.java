package models.interfaces.animal;

import models.animals.Animal;

/**
 * Represents the mammal animal interface
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public interface Mammal {

    /**
     * Makes the animal create a new one trough birth
     * @return The new animal
     */
    Animal birth();

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     * @return The newly created animal if any, or null if no birth happened
     */
    Animal checkBirth(int turnNb);

    /**
     * Allows an animal to perform a copulation with the one given in arg
     * @param animal The animal that will perform the copulation with our current Animal instance
     * @param turnNb The current turn number, used in case the copulation succeed, to calculate how long to pregnancy will last
     * @param <A> Generic type of Mammal to force having a Mammal as first arg
     */
    <A extends Mammal> void copulate(A animal, int turnNb);

    /**
     * Getter for the sex of the animal
     * @return The sex of the animal, where true is male, and false is female
     */
    boolean getSex();
}
