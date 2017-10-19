package Controllers.Jobs;

import Models.Enclosures.Enclosure;
import Models.Animals.Animal;
import Models.Interfaces.Animal.Mammal;
import Models.Interfaces.Animal.Oviparous;

import java.util.ArrayList;

public class CheckNewBirthJob {

    ArrayList<Enclosure> enclosureList;
    int turnNb;
    ArrayList<Animal> newBirths = new ArrayList<Animal>();

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
     * Will most likely represent the list of enclosures of the Zoo
     * @return ArrayList<Enclosure> The enclosure list that the job received
     */
    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    /**
     * Getter for the newly born animals collections (born this turn)
     * @return ArrayList<Animal> that were born this turn
     */
    public ArrayList<Animal> getNewBirths() {
        return this.newBirths;
    }

    /**
     * Allows to browse the previously given enclosure list, looking for animals that were born during the turn.
     * These animals will be added to a collection which will later be retrieved through a getter
     * @param <A> Generic type to use within the method
     */
    public <A extends Animal> void exec() {
        for (Enclosure<A> enclosure : this.getEnclosureList()) {
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
