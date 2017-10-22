package controllers.jobs;

import models.enclosures.Enclosure;
import models.animals.Animal;
import models.interfaces.animal.Mammal;
import models.interfaces.animal.Oviparous;

import java.util.ArrayList;

/**
 * Job that will be executed at every turn, which will check if there are new birth animal in enclosures
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class CheckNewBirthJob implements Runnable {

    /**
     * Represents the enclosure list to check
     */
    ArrayList<Enclosure> enclosureList;

    /**
     * Represents the current turn number
     */
    int turnNb;

    /**
     * Represents the list of new birth during this turn
     */
    ArrayList<Animal> newBirths = new ArrayList<Animal>();

    /**
     * Generic method to check a new birth
     * @param enclosureList The enclosure list to check
     * @param turnNb The current turn number
     */
    public CheckNewBirthJob(ArrayList<Enclosure> enclosureList, int turnNb) {
        this.enclosureList = enclosureList;
        this.turnNb = turnNb;
    }

    /**
     * Getter for the current turn number
     * @return the turn number at which the job is executed
     */
    public int getTurnNb() {
        return this.turnNb;
    }

    /**
     * Getter for the list of enclosure to check
     * Will most likely represent the list of enclosures of the zoo
     * @return An ArrayList of enclosures that contains the enclosure list received by the Job during its construction
     */
    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    /**
     * Getter for the newly born animals collections (born this turn)
     * @return An ArrayList of Animals that will contain the animals born this turn
     */
    public ArrayList<Animal> getNewBirths() {
        return this.newBirths;
    }

    /**
     * Allows to browse the previously given enclosure list, looking for animals that were born during the turn.
     * These animals will be added to a collection which will later be retrieved through a getter
     */
    @Override
    public void run() {
        for (Enclosure<Animal> enclosure : this.getEnclosureList()) {
            for (Animal animal : enclosure.getAnimals()) {
                if (animal instanceof Oviparous) {
                    ((Oviparous) animal).checkIfHatched(this.getTurnNb());
                } else if (animal instanceof Mammal) {
                    Animal newAnimal;
                    newAnimal = ((Mammal)animal).checkBirth(this.getTurnNb());
                    if (newAnimal != null) {
                        this.getNewBirths().add(newAnimal);
                    }
                }
            }
        }
    }
}
