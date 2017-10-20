package models.interfaces.animal;

import models.animals.Animal;

public interface Oviparous {

    /**
     * Makes the animal lay
     * @return The new animal
     */
    public Animal lay();

    public boolean checkIfHatched(int turnNb);
}
