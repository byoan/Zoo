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

    /**
     * Represents the current water level
     */
    private int waterLevel;

    public Aquarium(String name, int surface, int maxAnimals, int deepth, int salitiny, int waterLevel) {
        super(name, surface, maxAnimals);
        this.deepth = deepth;
        this.salitiny = salitiny;
        this.waterLevel = waterLevel;
    }

    public int getCurrentWaterLevel() {
        return this.waterLevel;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

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
        System.out.println("Le niveau de l'eau est de " + this.getCurrentWaterLevel());
        if (this.getCurrentWaterLevel() < this.getDeepth()) {
            System.out.println("Remplissage du bassin ...");
            System.out.println("Le niveau de l'eau est maintenant de " + this.getCurrentWaterLevel());
        }
        System.out.println("La salinité vaut actuellement : " + this.getSalitiny() + "/m3");
        if (this.getSalitiny() < 100) {
            System.out.println("Recalibrage du niveau de sel ...");
            this.setSalitiny(100);
            System.out.println("La salinité a été recalibrée, elle vaut maintenant : " + this.getSalitiny());
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Aquarium{" +
                "deepth=" + deepth +
                ", salitiny=" + salitiny +
                ", waterLevel=" + waterLevel +
                '}';
    }
}
