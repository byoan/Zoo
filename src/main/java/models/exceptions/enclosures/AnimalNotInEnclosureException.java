package models.exceptions.enclosures;

import models.enclosures.Enclosure;
import models.interfaces.animal.AnimalInterface;

/**
 * Represents an exception thrown when trying to delete an animal when which is not contained by the given enclosure
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class AnimalNotInEnclosureException extends Exception {

    /**
     * Represents the animal instance involved in the Exception
     */
    private AnimalInterface animal;

    /**
     * Represents the enclosure instance involved in the Exception
     */
    private Enclosure enclosure;

    /**
     * Constructor for the AnimalNotInEnclosureException
     * @param animal The animal involved in the Exception
     * @param enclosure The enclosure involved with the animal in the Exception
     * @param <A> Generic type of AnimalInterface used for the received animal arg
     */
    public <A extends AnimalInterface> AnimalNotInEnclosureException(AnimalInterface animal, Enclosure<A> enclosure) {
        this.animal = animal;
        this.enclosure = enclosure;
    }

    /**
     * Getter for the animal involved in the Exception
     * @return The animal attribute of the Exception
     */
    public AnimalInterface getAnimal() {
        return animal;
    }

    /**
     * Getter for the enclosure involved in the Exception
     * @param <A> Generic type of AnimalInterface used for the return type
     * @return The enclosure attribute of the Exception
     */
    public <A extends AnimalInterface> Enclosure<A> getEnclosure() {
        return this.enclosure;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message indicating which animal can't be found in which enclosure
     */
    @Override
    public String getMessage() {
        return "This " + this.getAnimal().getSpecieName() + " is not present in the " + this.getEnclosure().getName() + " enclosure.";
    }
}
