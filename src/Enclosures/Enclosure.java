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
            if (!animal.isInEnclosure() && this.getAnimals().size() < this.getMaxAnimals()) {
                this.getAnimals().add(animal);
                animal.setInEnclosure(true);
            } else {
                //throw new AnimalAlreadyInEnclosureException(animal, this);
                System.out.println("Can't add this animal as it is already in an enclosure, or the enclosure is full");
            }
        } catch(Exception e) {
            System.out.println("An error occurred while trying to add the Animal to the Enclosure : " + e.getMessage());
        }
    }

    public void remove(A animal) {
        try {
            if (animal.isInEnclosure() && this.getAnimals().contains(animal)) {
                this.getAnimals().remove(animal);
                animal.setInEnclosure(false);
            } else {
                // TODO : custom exception
                System.out.println("This animal is not in this enclosure");
            }
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

    public boolean transferAnimal(A animal, Enclosure<A> targetEnclosure, boolean silent) {
        if (this.getAnimals().contains(animal)) {
            try {
                this.remove(animal);
                targetEnclosure.add(animal);
            } catch (Exception e) {
                System.out.println("An error occurred while transferring the animal : " + e.getMessage());
                return false;
            }
            // Check that the transfer executed as expected
            if (!this.getAnimals().contains(animal) && targetEnclosure.getAnimals().contains(animal)) {
                if (!silent) {
                    System.out.println("The animal was successfully transferred");
                }
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("This animal is not in this enclosure");
            return false;
        }
    }

    public void cleanEnclosure() {
        if (this.getAnimals().size() > 0) {
            Enclosure<A> temporaryEnclosure = this.createTemporaryEnclosure();
            int originalSize = this.getNbAnimals();

            // We must proceed this way, as using a foreach on the collection, and removing an item from this collection within the foreach will create an Exception
            for (int i = 0; i < originalSize; i++) {
                A animal = this.getAnimals().get(i);
                this.transferAnimal(animal, temporaryEnclosure, true);
            }
            this.setCleanliness(2);

            // We must proceed this way, as using a foreach on the collection, and removing an item from this collection within the foreach will create an Exception
            for (int i = 0; i < originalSize; i++) {
                A animal = temporaryEnclosure.getAnimals().get(i);
                temporaryEnclosure.transferAnimal(animal, this, true);
            }
        } else {
            this.setCleanliness(2);
        }
        System.out.println("The " + this.getName() + " enclosure has been cleaned.");
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
