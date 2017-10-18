package Models.Exceptions.Animals;

import Models.Animals.Animal;

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
    public String getMessage() {
        return "This " + this.getAnimal().getSpecieName() + " is already pregnant. It therefore can't copulate.";
    }

}
