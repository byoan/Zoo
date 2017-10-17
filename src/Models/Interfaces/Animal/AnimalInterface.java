package Models.Interfaces.Animal;

import Models.Animals.Animal;

public interface AnimalInterface {

    /**
     * Allows to make the animal older at some specific times of the simulation
     */
    public void getOlder();

    /**
     * Getter for the age of the Animal (1 Young, 2 Adult, 3 Old)
     * @return The age of the Animal
     */
    public int getAge();

    /**
     * Makes the animal eat
     */
    public void eat();

    /**
     * Makes the animal scream
     */
    public void scream();

    /**
     * Heals the animal
     */
    public void heal();

    /**
     * Makes the animal sleep
     */
    public void sleep();

    /**
     * Awakes the animal
     */
    public void wake();

    /**
     * Getter for the isInEnclosure attributes, which indicates us if the animal is currently in an enclosure
     * @return Whether or not the animal is in an enclosure
     */
    public boolean isInEnclosure();

    /**
     * Setter for the isInEnclosure attribute
     * Called when added or removed from an Enclosure
     * @param isInEnclosure Whether or not it is now in an enclosure
     */
    public void setInEnclosure(boolean isInEnclosure);

    public <A extends AnimalInterface> Animal copulate(A animal, int turnNb);

    public boolean getSex();

    public String getSpecieName();
}
