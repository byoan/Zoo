package models.exceptions.enclosures;

import models.enclosures.Enclosure;
import models.interfaces.animal.AnimalInterface;

public class FullEnclosureException extends Exception {

    /**
     * Represents the enclosure involved in the exception
     */
    private Enclosure enclosure;

    /**
     * Constructor for the Exception
     * @param <A> Generic type for AnimalInterface, used for the return type
     * @param enclosure The enclosure which caused the exception
     */
    public <A extends AnimalInterface> FullEnclosureException(Enclosure<A> enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * Getter for the enclosure involved in the exception
     * @param <A> Generic type of AnimalInterface used for the return type
     * @return The enclosure instance
     */
    public <A extends AnimalInterface> Enclosure<A> getEnclosure() {
        return this.enclosure;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message, with an indication regarding the maximum space of the enclosure
     */
    @Override
    public String getMessage() {
        return "The " + this.getEnclosure().getName() + " enclosure is already full (" + this.getEnclosure().getNbAnimals() + "/" + this.getEnclosure().getMaxAnimals() + ").";
    }
}
