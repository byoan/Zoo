package Zoo;

import Employees.Employee;
import Enclosures.Enclosure;
import animals.AnimalInterface;

import java.util.ArrayList;

public class Zoo {

    /**
     * Represents the name of the Zoo
     */
    private String name;

    /**
     * Represents the Employee instance of the Zoo
     */
    private Employee employee;

    /**
     * Represents the maximum number of enclosure that this Zoo can contain
     */
    private int maxNbEnclosure;

    /**
     * Represents all the enclosures contained by this Zoo
     */
    private ArrayList<Enclosure> enclosureList;

    /**
     * Constructor for the Zoo class
     * @param name The name of our Zoo
     * @param employee An employee instance which represents our unique employee of the Zoo
     * @param maxNbEnclosure The max number of enclosure that the Zoo can contain
     */
    public Zoo(String name, Employee employee, int maxNbEnclosure) {
        this.name = name;
        this.employee = employee;
        this.maxNbEnclosure = maxNbEnclosure;
        this.setEnclosureList(new ArrayList<Enclosure>());
    }

    /**
     * Getter for our Zoo name
     * @return The Zoo name attribute
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the Zoo name
     * @param name The new name to give to the Zoo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for our Employee of the Zoo
     * @return Employee The Employee attribute of the Zoo class
     */
    public static Employee getEmployee() {
        return Employee.getInstance();
    }

    /**
     * Getter for the maximum number of enclosure that the Zoo can contain
     * @return int The attribute representing maximum number of enclosure that can be contained
     */
    public int getMaxNbEnclosure() {
        return this.maxNbEnclosure;
    }

    /**
     * Setter for the maximum number of enclosure that the Zoo can contain
     * @param maxNbEnclosure The new maximum number of enclosures
     */
    public void setMaxNbEnclosure(int maxNbEnclosure) {
        this.maxNbEnclosure = maxNbEnclosure;
    }

    /**
     * Getter for the enclosures contained by the Zoo
     * @return An ArrayList of Enclosures
     */
    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    /**
     * Setter for the enclosure list of the Zoo
     * @param enclosureList The new enclosures list
     */
    public void setEnclosureList(ArrayList<Enclosure> enclosureList) {
        this.enclosureList = enclosureList;
    }

    /**
     * Allows to add a new Enclosure to the Zoo
     * Will check if there is enough size in the Zoo before proceeding
     * @param enclosure The Enclosure to add
     * @param <A> Generic type that can be used in the method
     */
    public <A extends AnimalInterface> void addEnclosure(Enclosure<A> enclosure) {
        try {
            if (this.getEnclosureList().size() < this.getMaxNbEnclosure()) {
                this.getEnclosureList().add(enclosure);
            } else {
                // TODO : throw exception
                System.out.println("Can't add this enclosure to the Zoo, as it is full");
            }
        } catch(Exception e) {
            System.out.println("An error occurred while trying to add this enclosure to the zoo : " + e.getMessage());
        }
    }

    /**
     * Allows to remove an Enclosure from the Zoo
     * Will check if the Enclosure is in the Zoo before proceeding to the removal
     * @param enclosure The Enclosure to remove
     */
    public void removeEnclosure(Enclosure<AnimalInterface> enclosure) {
        try {
            this.getEnclosureList().remove(enclosure);
        } catch(Exception e) {
            // TODO : throw exception
            System.out.println("An error occurred while trying to remove this enclosure from the zoo : " + e.getMessage());
        }
    }

    /**
     * Allows to retrieve the first Enclosure instance from our Zoo's enclosures list that matches the given name
     * @param enclosureName The name of the searched enclosure
     * @return mixed The first Enclosure instance that matches this name, or null if there isn't any match
     */
    public Enclosure getEnclosureByName(String enclosureName) {
        if (this.getEnclosureList().size() > 0) {
            for (int i = 0; i <= this.getEnclosureList().size(); i++) {
                if (this.getEnclosureList().get(i).getName() == enclosureName) {
                    return this.getEnclosureList().get(i);
                }
            }
            // TODO : throw Exception
            System.out.println("No enclosure found with this name");
        } else {
            // TODO : throw Exception
            System.out.println("No enclosure in the Zoo yet");
        }
        return null;
    }

    /**
     * Getter for the current number of animals that are in the Zoo
     * Will iterate over the enclosures list to retrieve each enclosure's number of animals
     * @return The total number of animals in the Zoo
     */
    public int getNbAnimalsInZoo() {
        int nbAnimals = 0;
        for (Enclosure enclosure : this.getEnclosureList()) {
            nbAnimals += enclosure.getNbAnimals();
        }
        return nbAnimals;
    }

    /**
     * Allows to display all the characteristics of each animal in the Zoo
     */
    public void displayAnimalsFromAllEnclosures() {
        System.out.println("Displaying all animals currently in the Zoo : ");
        for (Enclosure enclosure : this.getEnclosureList()) {
            ArrayList<AnimalInterface> animalList = enclosure.getAnimals();
            for (AnimalInterface animal : animalList) {
                animal.toString();
            }
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.init();
    }

}
