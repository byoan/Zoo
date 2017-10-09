package Enclosures;

import animals.AnimalInterface;

import java.util.List;

public class Enclosure {
    private String name;
    private int surface;
    private int maxAnimals;

    /**
     * Level of cleanliness
     * 0 = bad, 1 = correct, 2 = good
     */
    private int cleanliness;

    /**
     * Type of animal that can be contained in the enclosure
     */
    private AnimalInterface type;

    /**
     * The collection of animals that are contained in the enclosure
     */
    private List<AnimalInterface> animals;

    public Enclosure() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getMaxAnimals() {
        return maxAnimals;
    }

    public void setMaxAnimals(int maxAnimals) {
        this.maxAnimals = maxAnimals;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public AnimalInterface getType() {
        return type;
    }

    public void setType(AnimalInterface type) {
        this.type = type;
    }

    public List<AnimalInterface> getAnimals() {
        return animals;
    }

    public void setAnimals(List<AnimalInterface> animals) {
        this.animals = animals;
    }
}
