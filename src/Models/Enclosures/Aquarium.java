package Models.Enclosures;

import Models.Interfaces.Animal.MarineAnimal;

public class Aquarium<A extends MarineAnimal> extends Enclosure {

    /**
     * Deepth of the aquarium
     */
    private int depth;

    /**
     * Percentage of salinity
     */
    private int salinity;

    /**
     * Represents the current water level
     */
    private int waterLevel;

    /**
     * Constructor for the aquarium
     * @param name The name of the aquarium
     * @param surface The surface of the aquarium, in meters
     * @param maxAnimals The maximum number of Models.Animals that the aquarium can contain
     * @param depth The depth of the aquarium, in meters
     * @param salinity The percentage of salinity of the aquarium
     * @param waterLevel The water level of the aquarium, in meters
     */
    public Aquarium(String name, int surface, int maxAnimals, int depth, int salinity, int waterLevel) {
        super(name, surface, maxAnimals);
        this.depth = depth;
        this.salinity = salinity;
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
        if (waterLevel >= 0 && waterLevel <= this.getDepth()) {
            this.waterLevel = waterLevel;
        } else {
            System.out.println("The water level can't be greater that the aquarium depth");
        }
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
        if (salinity >= 0 && salinity <= 100) {
            this.salinity = salinity;
        }
    }

    public void deteriorate() {
        this.setWaterLevel(this.getCurrentWaterLevel() - 1);
        this.setSalinity(this.getSalinity() - 10);
    }

    /**
     * Allows to clean the aquarium.
     * Does redefine the cleanEnclosure method from its parent
     * Uses a generic name to ease its call within the Simulation
     */
    public void cleanEnclosure() {
        System.out.println("The water level is worth " + this.getCurrentWaterLevel());
        if (this.getCurrentWaterLevel() < this.getDepth()) {
            System.out.println("Adding more water ...");
            this.setWaterLevel(this.getDepth());
            System.out.println("The water level is now worth " + this.getCurrentWaterLevel());
        }
        System.out.println("The salinity level is now worth : " + this.getSalinity() + "/m3");
        if (this.getSalinity() < 100) {
            System.out.println("Recalibrating salinity level ...");
            this.setSalinity(100);
            System.out.println("Salinity has been recalibrated, it is now worth : " + this.getSalinity());
        }
    }

    /**
     * Allows to display all the aquarium properties
     * @return
     */
    @Override
    public String toString() {
        return "Aquarium {\n" +
                " name='" + this.getName() + "', \n" +
                " surface=" + this.getSurface() + "\n" +
                " maxAnimals=" + this.getMaxAnimals() + ", \n" +
                " cleanliness=" + this.getCleanliness() + ", \n" +
                " depth=" + this.getDepth() + ", \n" +
                " salinity=" + this.getSalinity() + ", \n" +
                " waterLevel=" + this.getCurrentWaterLevel() + ", \n" +
                " Models.Animals=" + this.getAnimals() + ", \n" +
                '}';
    }
}
