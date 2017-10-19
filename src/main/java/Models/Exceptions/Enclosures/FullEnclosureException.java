package Models.Exceptions.Enclosures;

import Models.Enclosures.Enclosure;
import Models.Interfaces.Animal.AnimalInterface;

public class FullEnclosureException extends Exception {

    /**
     * Represents the enclosure involved in the exception
     */
    private Enclosure enclosure;

    /**
     * Constructor for the Exception
     * @param enclosure The enclosure which caused the exception
     */
    public <A extends AnimalInterface> FullEnclosureException(Enclosure<A> enclosure) {
        this.enclosure = enclosure;
    }

    /**
     * Getter for the enclosure involved in the exception
     * @return The enclosure instance
     */
    public <A extends AnimalInterface> Enclosure<A> getEnclosure() {
        return this.enclosure;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message, with an indication regarding the maximum space of the enclosure
     */
    public String getMessage() {
        return "The " + this.getEnclosure().getName() + " enclosure is already full (" + this.getEnclosure().getNbAnimals() + "/" + this.getEnclosure().getMaxAnimals() + ").";
    }
}
