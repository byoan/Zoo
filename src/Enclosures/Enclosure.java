package Enclosures;

import Core.Exceptions.AnimalAlreadyInEnclosureException;
import animals.AnimalInterface;

import java.util.ArrayList;

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
    private ArrayList<A> animals;

    public Enclosure(String name, int surface, int maxAnimals) {
        this.name = name;
        this.surface = surface;
        this.maxAnimals = maxAnimals;
        this.cleanliness = 2;
        this.animals = new ArrayList<A>();
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

    public ArrayList<A> getAnimals() {
        return this.animals;
    }

    public void setAnimals(ArrayList<A> animals) {
        this.animals = animals;
    }

    public void add(A animal) {
        try {
            if (!this.getAnimals().contains(animal)) {
                this.getAnimals().add(animal);
            } else {
                //throw new AnimalAlreadyInEnclosureException(animal, this);
                System.out.println("Can't add this animal as it is already in");
            }
        } catch(Exception e) {
            System.out.println("An error occured while trying to add the Animal to the Enclosure : " + e.getMessage());
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
        return new Enclosure<A>("Temporary Enclosure for " + this.getName() + " cleaning", this.getSurface(), this.getMaxAnimals());
    }

    public void cleanEnclosure() {
        if (this.getAnimals().size() > 0) {
            Enclosure<A> temporaryEnclosure = this.createTemporaryEnclosure();
            for (A animal : this.getAnimals()) {
                try {
                    temporaryEnclosure.add(animal);
                    this.getAnimals().remove(animal);
                } catch (Exception e) {
                    e.getMessage();
                }
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

    @Override
    public String toString() {
        return "Enclosure{" +
                "name='" + name + '\'' +
                ", surface=" + surface +
                ", maxAnimals=" + maxAnimals +
                ", cleanliness=" + cleanliness +
                ", animals=" + animals +
                '}';
    }
}
