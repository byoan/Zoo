package Core.Jobs;

import Enclosures.Enclosure;
import animals.Animal;
import animals.Mammal;
import animals.Oviparous;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

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

    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    public ArrayList<Animal> getNewBirths() {
        return this.newBirths;
    }

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
