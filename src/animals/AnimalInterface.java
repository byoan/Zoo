package animals;

public interface AnimalInterface {

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
     * @return
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
}
