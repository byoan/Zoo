package models.exceptions.animals;

import models.interfaces.animal.AnimalInterface;

/**
 * Represents an exception thrown when the given animal is being added to an enclosure while already being in an enclosure
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class AnimalAlreadyInEnclosureException extends Exception {

    /**
     * Represents the animal instance that was involved in the Exception
     */
    private AnimalInterface animal;

    /**
     * Constructor for the Exception
     * @param animal The animal instance which caused the error
     */
    public AnimalAlreadyInEnclosureException(AnimalInterface animal) {
        this.animal = animal;
    }

    /**
     * Getter for the animal instance of the Exception
     * @return The animal instance
     */
    private AnimalInterface getAnimal() {
        return this.animal;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message
     */
    @Override
    public String getMessage() {
        return "This " + this.getAnimal() + " is already in an enclosure";
    }
}
