package models.exceptions.animals;

import models.animals.Animal;

/**
 * Represents an exception thrown when the given animal is getting pregnant while already being pregnant
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class AnimalAlreadyPregnantException extends Exception {

    /**
     * Represents the animal involved in the Exception
     */
    private Animal animal;


    /**
     * Constructor for the Exception
     * @param animal The animal involved in the Exception
     */
    public AnimalAlreadyPregnantException(Animal animal) {
        this.animal = animal;
    }

    /**
     * Getter for the animal instance involved in the Exception
     * @return The animal attribute of the Exception
     */
    public Animal getAnimal() {
        return this.animal;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message
     */
    @Override
    public String getMessage() {
        return "A " + this.getAnimal().getSpecieName() + " copulated, but is already pregnant.";
    }

}