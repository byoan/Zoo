package Controllers.Jobs;

import Controllers.Zoo.Zoo;
import Models.Animals.Animal;
import Models.Enclosures.Enclosure;
import Models.Interfaces.Animal.AnimalInterface;
import Views.View;

public class MakeAnimalsAge {

    /**
     * The Zoo instance to manipulate in the Job
     */
    private Zoo zoo;

    /**
     * The current turn number
     */
    private int turnNb;

    /**
     * Constructor for the MakeAnimalsAge job
     * @param zoo The zoo instance that will be manipulated
     * @param turnNb The current turn number
     */
    public MakeAnimalsAge(Zoo zoo, int turnNb) {
        this.zoo = zoo;
        this.turnNb = turnNb;
    }

    /**
     * Getter for the Zoo instance passed during the Job construction
     * @return The Zoo instance
     */
    public Zoo getZoo() {
        return this.zoo;
    }

    /**
     * Getter for the turn number passed during the Job construction
     * @return The current turn number
     */
    public int getTurnNb() {
        return this.turnNb;
    }

    /**
     * Executes the job
     * Will call the getOlder method for each animal of the Zoo
     * @param <A>
     */
    public <A extends AnimalInterface> void exec() {
        for (Enclosure<A> enclosure : this.getZoo().getEnclosureList()) {
            for (AnimalInterface animal : enclosure.getAnimals()) {
                animal.getOlder();
            }
        }
    }

}
