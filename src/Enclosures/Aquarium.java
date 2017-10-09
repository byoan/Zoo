package Enclosures;

import animals.AnimalInterface;
import animals.MarineAnimal;

import java.util.List;

public class Aquarium<A extends MarineAnimal> extends Enclosure {

    /**
     * Deepth of the aquarium
     */
    private int deepth;

    /**
     * Percentage of salinity
     */
    private int salitiny;

    public int getDeepth() {
        return this.deepth;
    }

    public void setDeepth(int deepth) {
        this.deepth = deepth;
    }

    public int getSalitiny() {
        return this.salitiny;
    }

    public void setSalitiny(int salitiny) {
        this.salitiny = salitiny;
    }

    public void cleanEnclosure() {
        System.out.println("La profondeur est de " + this.getDeepth());
    }

}
