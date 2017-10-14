package Enclosures;

import Core.Exceptions.AnimalAlreadyInEnclosureException;
import animals.AnimalInterface;

import java.util.ArrayList;

public class Enclosure<A extends AnimalInterface> {

    /**
     * Represents the name of the enclosure
     */
    private String name;

    /**
     * Represents the surface of the enclosure
     */
    private int surface;

    /**
     * Represents the maximum number of animals that the enclosure can contain
     */
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

    /**
     * Getter for the name of the enclosure
     * @return The name of the enclosure
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the enclosure
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the surface of the enclosure
     * @return The surface attribute of the enclosure
     */
    public int getSurface() {
        return this.surface;
    }

    /**
     * Setter for the surface of the enclosure
     * @param surface The new surface of the enclosure
     */
    public void setSurface(int surface) {
        this.surface = surface;
    }

    /**
     * Getter for the maximum number of animals that this enclosure can contain
     * @return The maximum number of animals attribute of the enclosure
     */
    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    /**
     * Setter for the maximum number of animals of the enclosure attribute
     * @param maxAnimals The new maximum number of animals
     */
    public void setMaxAnimals(int maxAnimals) {
        this.maxAnimals = maxAnimals;
    }

    /**
     * Getter for the level of cleanliness of the enclosure
     * @return The current level of cleanliness of the enclosure
     */
    public int getCleanliness() {
        return this.cleanliness;
    }

    /**
     * Setter of the level of cleanliness of the enclosure
     * @param cleanliness The new level of cleanliness
     */
    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    /**
     * Getter for the list of animals contained in the enclosure
     * @return An ArrayList<A extends AnimalInterface> containing all the animals of the enclosure
     */
    public ArrayList<A> getAnimals() {
        return this.animals;
    }

    /**
     * Setter for the list of animals contained in the enclosure
     * @param animals The new list of animals of the enclosure
     */
    public void setAnimals(ArrayList<A> animals) {
        this.animals = animals;
    }

    /**
     * Allows to add an animal to the enclosure
     * @param animal An Animal class implementing the same AnimalInterface that the enclosure
     */
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

    /**
     * Allows to remove an animal from the Enclosure
     * @param animal The animal to be removed, implementing the same Animal class that the Enclosure
     */
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

    /**
     * Getter for the current number of animals in the enclosure
     * @return The current number of animals in the enclosure
     */
    public int getNbAnimals() {
        return this.getAnimals().size();
    }

    /**
     * Allows to feed all the animals of the enclosure
     */
    public void feedAnimals() {
        for(A animal : this.getAnimals()) {
            animal.eat();
        }
    }

    /**
     * Allows to create a temporary enclosure which has the same properties that the current instance
     * Used for the cleaning process, as an enclosure must be emptied before cleaned
     * @return Enclosure<A extends AnimalInterface> The temporary enclosure to use during cleaning
     */
    public Enclosure<A> createTemporaryEnclosure() {
        return new Enclosure<A>("Temporary Enclosure for " + this.getName() + " cleaning", this.getSurface(), this.getMaxAnimals());
    }

    /**
     * Allows to transfer a given animal from the current enclosure to the given one
     * @param animal The animal to transfer
     * @param targetEnclosure The target enclosure
     * @param silent Whether or not the success message should be displayed
     * @return true if successful, or false if not
     */
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

    /**
     * Allows to clean the enclosure
     * Will transfer all the animals to a temporary enclosure to proceed
     */
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

    /**
     * Allows to display all the enclosure's properties
     * @return A concatenated string containing the enclosure properties
     */
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
