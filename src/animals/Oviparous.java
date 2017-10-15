package animals;

public interface Oviparous {

    /**
     * Makes the animal lay
     * @return The new animal
     */
    public Animal lay();

    public boolean checkIfHatched(int turnNb);
}
