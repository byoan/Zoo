package animals;

public interface Mammal {

    /**
     * Makes the animal create a new one trough birth
     * @return The new animal
     */
    public Animal birth();

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public Animal checkBirth(int turnNb);

    public <A extends Mammal> void copulate(A animal, int turnNb);

    public boolean getSex();
}
