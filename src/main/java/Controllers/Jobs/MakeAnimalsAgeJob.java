package Controllers.Jobs;

import Controllers.Zoo.Zoo;
import Models.Animals.Animal;
import Models.Animals.Wolf;
import Models.Enclosures.Enclosure;
import Models.Interfaces.Animal.AnimalInterface;
import Views.View;


public class MakeAnimalsAgeJob {

    /**
     * The Zoo instance to manipulate in the Job
     */
    private Zoo zoo;

    /**
     * The current turn number
     */
    private int turnNb;

    /**
     * Constructor for the MakeAnimalsAgeJob job
     * @param zoo The zoo instance that will be manipulated
     * @param turnNb The current turn number
     */
    public MakeAnimalsAgeJob(Zoo zoo, int turnNb) {
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
     * If an animal reaches the age above "Old", which is basically the oldest available, he dies
     */
    public void exec() {
        for (Enclosure<Animal> enclosure : this.getZoo().getEnclosureList()) {
            // We must proceed this way, as using a foreach on the collection, and removing an item from this collection within the foreach will create an Exception
            for (int i = 0; i < enclosure.getNbAnimals(); i++) {
                Animal animal = enclosure.getAnimals().get(i);
                animal.getOlder();

                if (animal.getAge() == 4) {
                    if (animal.getSpecieName() == "Wolf") {
                        ((Wolf)animal).getPack().remove((Wolf)animal);
                        enclosure.remove(animal);
                        View.displayMessage("A " + animal.getSpecieName() + " died of old age in the " + enclosure.getName() + " enclosure.\n");
                        animal = null;
                        // Decrease i to always pick the first of the list
                        i--;
                    }
                }
            }
        }
    }

}
