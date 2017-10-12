package Core.Exceptions;

import Enclosures.Enclosure;
import animals.AnimalInterface;

import java.util.ArrayList;

public class AnimalAlreadyInEnclosureException extends Exception {
    private AnimalInterface animal;
    private Enclosure enclosure;

    public AnimalAlreadyInEnclosureException(AnimalInterface animal, Enclosure enclosure) {
        this.animal = animal;
        this.enclosure = enclosure;
    }

    private AnimalInterface getAnimal() {
        return animal;
    }

    private Enclosure getEnclosure() {
        return this.enclosure;
    }

    public String getMessage() {
        return "This animal is already in the " + this.getEnclosure().getName() + " enclosure";
    }
}
