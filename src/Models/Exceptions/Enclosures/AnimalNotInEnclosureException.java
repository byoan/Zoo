package Models.Exceptions.Enclosures;

import Models.Animals.Animal;
import Models.Enclosures.Enclosure;
import Models.Interfaces.Animal.AnimalInterface;

public class AnimalNotInEnclosureException extends Exception {

    /**
     * Represents the animal instance involved in the Exception
     */
    private AnimalInterface animal;

    /**
     * Represents the enclosure instance involved in the Exception
     */
    private Enclosure enclosure;

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
     * @return The enclosure attribute of the Exception
     */
    public <A extends AnimalInterface> Enclosure<A> getEnclosure() {
        return this.enclosure;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message indicating which animal can't be found in which enclosure
     */
    public String getMessage() {
        return "This " + this.getAnimal().getSpecieName() + " is not present in the " + this.getEnclosure().getName() + " enclosure.";
    }
}
