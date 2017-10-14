package Enclosures;

import animals.MarineAnimal;

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

    /**
     * Constructor for the aquarium
     * @param name The name of the aquarium
     * @param surface The surface of the aquarium, in meters
     * @param maxAnimals The maximum number of animals that the aquarium can contain
     * @param depth The depth of the aquarium, in meters
     * @param salinity The percentage of salinity of the aquarium
     * @param waterLevel The water level of the aquarium, in meters
     */
    public Aquarium(String name, int surface, int maxAnimals, int depth, int salinity, int waterLevel) {
        super(name, surface, maxAnimals);
        this.deepth = deepth;
        this.salitiny = salitiny;
        this.waterLevel = waterLevel;
    }

    /**
     * Getter for the water level of the aquarium
     * @return The current water level of the aquarium
     */
    public int getCurrentWaterLevel() {
        return this.waterLevel;
    }

    /**
     * Setter of the water level of the aquarium
     * Must have defined the depth of the aquarium before using
     * @param waterLevel The new water level
     */
    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     * Getter for the aquarium depth
     * @return The aquarium depth
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * Setter for the aquarium max depth
     * @param depth The new max depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Getter for the level of salinity of the aquarium
     * @return The current level of salinity
     */
    public int getSalinity() {
        return this.salinity;
    }

    /**
     * Setter for the level of salinity of the aquarium
     * @param salinity
     */
    public void setSalinity(int salinity) {
        this.salinity = salinity;
    }

    /**
     * Allows to clean the aquarium.
     * Does redefine the cleanEnclosure method from its parent
     * Uses a generic name to ease its call within the Simulation
     */
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

    /**
     * Allows to display all the aquarium properties
     * @return
     */
    @Override
    public String toString() {
        return super.toString() + "Aquarium{" +
                "deepth=" + deepth +
                ", salitiny=" + salitiny +
                ", waterLevel=" + waterLevel +
                '}';
    }
}
