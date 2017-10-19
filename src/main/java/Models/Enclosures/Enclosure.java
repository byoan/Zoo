package Models.Enclosures;

import Models.Enums.WolfRank;
import Models.Exceptions.Animals.AnimalAlreadyInEnclosureException;
import Models.Exceptions.Enclosures.AnimalNotInEnclosureException;
import Models.Exceptions.Enclosures.FullEnclosureException;
import Models.Interfaces.Animal.AnimalInterface;
import Models.Animals.Wolf;
import Models.Animals.Packs.WolfPack;
import Views.View;

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
     * Represents the maximum number of Models.Animals that the enclosure can contain
     */
    private int maxAnimals;

    /**
     * Level of cleanliness
     * 0 = bad, 1 = correct, 2 = good
     */
    private int cleanliness;

    /**
     * The collection of Models.Animals that are contained in the enclosure
     */
    private ArrayList<A> animals;

    /**
     * Represents the WolfPack present in the enclosure, if any
     * Can only have one pack per Enclosure
     */
    private WolfPack wolfPack;

    /**
     * Constructor for the Enclosure
     * @param name The name of the enclosure
     * @param surface The surface of the enclosure
     * @param maxAnimals The maximum number of animals that the enclosure can contain
     */
    public Enclosure(String name, int surface, int maxAnimals) {
        this.name = name;
        this.surface = surface;
        this.maxAnimals = maxAnimals;
        this.cleanliness = 2;
        this.animals = new ArrayList<A>();
        this.wolfPack = null;
    }

    /**
     * Getter for the wolf pack of the enclosure
     * @return The wolf pack of the enclosure, if any
     */
    public WolfPack getWolfPack() {
        return this.wolfPack;
    }

    /**
     * Setter for the wolf pack of the enclosure
     * @param wolfPack The wolfpack to add to this enclosure
     */
    public void setWolfPack(WolfPack wolfPack) {
        this.wolfPack = wolfPack;
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
     * Getter for the maximum number of Models.Animals that this enclosure can contain
     * @return The maximum number of Models.Animals attribute of the enclosure
     */
    public int getMaxAnimals() {
        return this.maxAnimals;
    }

    /**
     * Setter for the maximum number of Models.Animals of the enclosure attribute
     * @param maxAnimals The new maximum number of Models.Animals
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
        if (cleanliness >= 0 && cleanliness <= 2) {
            this.cleanliness = cleanliness;
        }
    }

    /**
     * Allows to deteriorate the cleanliness of the enclosure without having to specify any value in the Simulation
     */
    public void deteriorate() {
        this.setCleanliness(this.getCleanliness() - 1);
    }

    /**
     * Getter for the list of Models.Animals contained in the enclosure
     * @return An ArrayList<A extends AnimalInterface> containing all the Models.Animals of the enclosure
     */
    public ArrayList<A> getAnimals() {
        return this.animals;
    }

    /**
     * Setter for the list of Models.Animals contained in the enclosure
     * @param animals The new list of Models.Animals of the enclosure
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
            if (!animal.isInEnclosure()) {
                if (this.getAnimals().size() < this.getMaxAnimals()) {
                    // Specific case to the Wolf -> Packs
                    if (animal.getSpecieName() == "Wolf") {
                        this.handleWolfAdditionToPack(animal);
                    }
                    if (this.getAnimals().size() == 0 || this.getAnimals().get(0).getSpecieName() == animal.getSpecieName()) {
                        this.getAnimals().add(animal);
                        animal.setInEnclosure(true);
                    } else {
                        View.displayMessage("Can't add the wrong animal type to the enclosure");
                    }
                } else {
                    throw new FullEnclosureException(this);
                }
            } else {
                throw new AnimalAlreadyInEnclosureException(animal);
            }
        } catch (FullEnclosureException e) {
            View.displayMessage(e.getMessage());
        } catch (AnimalAlreadyInEnclosureException e) {
            View.displayMessage(e.getMessage());
        } catch (Exception e) {
            View.displayMessage("An error occurred while trying to add this animal to the " + this.getName() + " enclosure : " + e.toString());
        }
    }

    /**
     * Allows to remove an animal from the Enclosure
     * @param animal The animal to be removed, implementing the same Animal class that the Enclosure
     */
    public void remove(A animal) {
        try {
            if (animal.isInEnclosure() && this.getAnimals().contains(animal)) {
                if (animal.getSpecieName() == "Wolf") {
                    this.handleWolfDeletion((Wolf) animal);
                }
                this.getAnimals().remove(animal);
                animal.setInEnclosure(false);
            } else {
                throw new AnimalNotInEnclosureException(animal, this);
            }
        } catch (AnimalNotInEnclosureException e) {
            View.displayMessage(e.getMessage());
        } catch(Exception e) {
            View.displayMessage(e.getMessage());
        }
    }

    /**
     * Getter for the current number of Models.Animals in the enclosure
     * @return The current number of Models.Animals in the enclosure
     */
    public int getNbAnimals() {
        return this.getAnimals().size();
    }

    /**
     * Allows to feed all the Models.Animals of the enclosure
     */
    public void feedAnimals() {
        for(A animal : this.getAnimals()) {
            animal.eat();
        }
    }

    /**
     * Allows to handle Wolves' specific removal from an Enclosure
     */
    private void handleWolfDeletion(Wolf wolf) {
        if (this.getWolfPack() != null && wolf.getPack() != null) {
            this.getWolfPack().remove(wolf);
        }
    }

    /**
     * Allows to check for packs before adding the wolf to the enclosure
     * @param wolf The wolf which we'll try to the pack, if he matches some conditions
     */
    private void handleWolfAdditionToPack(A wolf) {
        if (wolf instanceof Wolf) {
            if (this.getWolfPack() == null) {
                for (A wolfInEnclosure : this.getAnimals()) {
                    if (wolfInEnclosure.getSex() != wolf.getSex()) {
                        this.setWolfPack(new WolfPack());
                        ((Wolf) wolf).setRank(WolfRank.Alpha);
                        ((Wolf) wolf).setStrength(101);
                        this.getWolfPack().add((Wolf) wolf);
                        ((Wolf) wolfInEnclosure).setRank(WolfRank.Alpha);
                        ((Wolf) wolfInEnclosure).setStrength(101);
                        this.getWolfPack().add((Wolf) wolfInEnclosure);

                        // In case we have animals in the enclosures, but no pack yet, we must add them to the newly created pack
                        if (this.getNbAnimals() >= 2) {
                            this.getWolfPack().addAllWolvesToPack((Enclosure<Wolf>) this);
                            this.getWolfPack().setAtLeastOneOmegaInPack((Enclosure<Wolf>) this);
                            // Sort the list by level after our insertion
                            this.getWolfPack().setWolfList(this.getWolfPack().insertionSort(this.getWolfPack().getWolfList()));
                        }
                        break;
                    }
                }
            } else if (this.getWolfPack() != null && ((Wolf) wolf).getRank() == null) {
                WolfPack pack = this.getWolfPack();
                pack.generateWolfRank((Wolf) wolf);
                pack.add((Wolf) wolf);
                pack.setAtLeastOneOmegaInPack((Enclosure<Wolf>) this);
                // Sort the list by level after our insertion
                pack.setWolfList(pack.insertionSort(pack.getWolfList()));
            }
        } else {
            // Error exception
        }
    }

    /**
     * Allows to create a temporary enclosure which has the same properties that the current instance
     * Used for the cleaning process, as an enclosure must be emptied before cleaned
     * @return Enclosure<AnimalInterface> The temporary enclosure to use during cleaning
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
                View.displayMessage("An error occurred while transferring the animal : " + e.getMessage());
                return false;
            }
            // Check that the transfer executed as expected
            if (!this.getAnimals().contains(animal) && targetEnclosure.getAnimals().contains(animal)) {
                if (!silent) {
                    View.displayMessage("The animal was successfully transferred");
                }
                return true;
            } else {
                return false;
            }
        } else {
            View.displayMessage("This animal is not in this enclosure");
            return false;
        }
    }

    /**
     * Allows to clean the enclosure
     * Will transfer all the Models.Animals to a temporary enclosure to proceed
     */
    public void cleanEnclosure() {
        if (this.getAnimals().size() > 0) {
            Enclosure<A> temporaryEnclosure = this.createTemporaryEnclosure();
            int originalSize = this.getNbAnimals();

            // We must proceed this way, as using a foreach on the collection, and removing an item from this collection within the foreach will create an Exception
            for (int i = 0; i < originalSize; i++) {
                // We have to get the first animal, as this.getAnimals() gets reduced at every transfer, which means that we can't get using i (FIFO)
                A animal = this.getAnimals().get(0);
                this.transferAnimal(animal, temporaryEnclosure, true);
            }
            this.setCleanliness(2);

            // We must proceed this way, as using a foreach on the collection, and removing an item from this collection within the foreach will create an Exception
            for (int i = 0; i < originalSize; i++) {
                // We have to get the first animal, as this.getAnimals() gets reduced at every transfer, which means that we can't get using i (FIFO)
                A animal = temporaryEnclosure.getAnimals().get(0);
                temporaryEnclosure.transferAnimal(animal, this, true);
            }
        } else {
            this.setCleanliness(2);
        }
        View.displayMessage("The " + this.getName() + " enclosure has been cleaned.");
    }

    /**
     * Allows to display all the enclosure's properties
     * @return A concatenated string containing the enclosure properties
     */
    @Override
    public String toString() {
        return "Enclosure information:\n" +
                "Name: '" + this.getName() + "'" + "  |  " +
                "Maximum number of animals that the enclosure can contain: " + this.getMaxAnimals() + "\n" +
                "Surface: " + this.getSurface() + " m²  |  " + "Cleanliness status: " + ((this.getCleanliness() == 0) ? "Really bad" : (this.getCleanliness() == 1) ? "Bad" : "Good") + "\n" +
                "\nAnimals currently in the enclosure: \n" + this.getAnimals() +
                ((this.getWolfPack() != null) ? "Pack: " + this.getWolfPack() + ", \n" : "")
                ;
    }
}