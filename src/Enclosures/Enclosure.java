package Enclosures;

import animals.AnimalInterface;

import java.util.List;

public class Enclosure<A extends AnimalInterface> {
    private String name;
    private int surface;
    private int maxAnimals;

    /**
     * Level of cleanliness
     * 0 = bad, 1 = correct, 2 = good
     */
    private int cleanliness;

    /**
     * The collection of animals that are contained in the enclosure
     */
    private List<A> animals;

    public Enclosure() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSurface() {
        return this.surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    public void setMaxAnimals(int maxAnimals) {
        this.maxAnimals = maxAnimals;
    }

    public int getCleanliness() {
        return this.cleanliness;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public List<A> getAnimals() {
        return this.animals;
    }

    public void setAnimals(List<A> animals) {
        this.animals = animals;
    }

    public void add(A animal) {
        try {
            this.getAnimals().add(animal);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(A animal) {
        try {
            this.getAnimals().remove(animal);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getNbAnimals() {
        return this.getAnimals().size();
    }

    public void feedAnimals() {
        for(A animal : this.getAnimals()) {
            animal.eat();
        }
    }

    public Enclosure<A> createTemporaryEnclosure() {
        return new Enclosure<A>();
    }

    public void cleanEnclosure() {
        if (this.getAnimals().size() > 0) {
            Enclosure<A> temporaryEnclosure = this.createTemporaryEnclosure();
            for (A animal : this.getAnimals()) {
                temporaryEnclosure.add(animal);
                this.getAnimals().remove(animal);
            }
            this.setCleanliness(2);
            for (A animal : temporaryEnclosure.getAnimals()) {
                this.getAnimals().add(animal);
                temporaryEnclosure.remove(animal);
            }
        } else {
            this.setCleanliness(2);
        }
    }
}
