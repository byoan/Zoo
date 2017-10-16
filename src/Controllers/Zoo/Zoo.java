package Controllers.Zoo;

import Models.Employees.Employee;
import Models.Enclosures.Enclosure;
import Models.Interfaces.Animal.AnimalInterface;

import java.util.ArrayList;

public class Zoo {

    /**
     * Represents the name of the Controllers.Zoo
     */
    private String name;

    /**
     * Represents the Employee instance of the Controllers.Zoo
     */
    private Employee employee;

    /**
     * Represents the maximum number of enclosure that this Controllers.Zoo can contain
     */
    private int maxNbEnclosure;

    /**
     * Represents all the enclosures contained by this Controllers.Zoo
     */
    private ArrayList<Enclosure> enclosureList;

    /**
     * Constructor for the Controllers.Zoo class
     * @param name The name of our Controllers.Zoo
     * @param employee An employee instance which represents our unique employee of the Controllers.Zoo
     * @param maxNbEnclosure The max number of enclosure that the Controllers.Zoo can contain
     */
    public Zoo(String name, Employee employee, int maxNbEnclosure) {
        this.name = name;
        this.employee = employee;
        this.maxNbEnclosure = maxNbEnclosure;
        this.setEnclosureList(new ArrayList<Enclosure>());
    }

    /**
     * Getter for our Controllers.Zoo name
     * @return The Controllers.Zoo name attribute
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the Controllers.Zoo name
     * @param name The new name to give to the Controllers.Zoo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for our Employee of the Controllers.Zoo
     * @return Employee The Employee attribute of the Controllers.Zoo class
     */
    public static Employee getEmployee() {
        return Employee.getInstance();
    }

    /**
     * Getter for the maximum number of enclosure that the Controllers.Zoo can contain
     * @return int The attribute representing maximum number of enclosure that can be contained
     */
    public int getMaxNbEnclosure() {
        return this.maxNbEnclosure;
    }

    /**
     * Setter for the maximum number of enclosure that the Controllers.Zoo can contain
     * @param maxNbEnclosure The new maximum number of enclosures
     */
    public void setMaxNbEnclosure(int maxNbEnclosure) {
        this.maxNbEnclosure = maxNbEnclosure;
    }

    /**
     * Getter for the enclosures contained by the Controllers.Zoo
     * @return An ArrayList of Models.Enclosures
     */
    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    /**
     * Setter for the enclosure list of the Controllers.Zoo
     * @param enclosureList The new enclosures list
     */
    public void setEnclosureList(ArrayList<Enclosure> enclosureList) {
        this.enclosureList = enclosureList;
    }

    /**
     * Allows to add a new Enclosure to the Controllers.Zoo
     * Will check if there is enough size in the Controllers.Zoo before proceeding
     * @param enclosure The Enclosure to add
     * @param <A> Generic type that can be used in the method
     */
    public <A extends AnimalInterface> void addEnclosure(Enclosure<A> enclosure) {
        try {
            if (this.getEnclosureList().size() < this.getMaxNbEnclosure()) {
                this.getEnclosureList().add(enclosure);
            } else {
                // TODO : throw exception
                System.out.println("Can't add this enclosure to the Controllers.Zoo, as it is full");
            }
        } catch(Exception e) {
            System.out.println("An error occurred while trying to add this enclosure to the zoo : " + e.getMessage());
        }
    }

    /**
     * Allows to remove an Enclosure from the Controllers.Zoo
     * Will check if the Enclosure is in the Controllers.Zoo before proceeding to the removal
     * @param enclosure The Enclosure to remove
     */
    public void removeEnclosure(Enclosure<AnimalInterface> enclosure) {
        try {
            if (this.getEnclosureList().contains(enclosure)) {
                this.getEnclosureList().remove(enclosure);
            } else {
                // TODO : throw custom exception
                System.out.println("The given enclosure is currently not in this Controllers.Zoo");
            }
        } catch(Exception e) {
            // TODO : throw exception
            System.out.println("An error occurred while trying to remove this enclosure from the zoo : " + e.getMessage());
        }
    }

    /**
     * Allows to retrieve the first Enclosure instance from our Controllers.Zoo's enclosures list that matches the given name
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
            System.out.println("No enclosure in the Controllers.Zoo yet");
        }
        return null;
    }

    /**
     * Getter for the current number of Models.Animals that are in the Controllers.Zoo
     * Will iterate over the enclosures list to retrieve each enclosure's number of Models.Animals
     * @return The total number of Models.Animals in the Controllers.Zoo
     */
    public int getNbAnimalsInZoo() {
        int nbAnimals = 0;
        for (Enclosure enclosure : this.getEnclosureList()) {
            nbAnimals += enclosure.getNbAnimals();
        }
        return nbAnimals;
    }

    /**
     * Allows to display all the characteristics of each animal in the Controllers.Zoo
     */
    public void displayAnimalsFromAllEnclosures() {
        for (Enclosure enclosure : this.getEnclosureList()) {
            ArrayList<AnimalInterface> animalList = enclosure.getAnimals();
            for (AnimalInterface animal : animalList) {
                System.out.println(animal.toString());
            }
        }
    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.init();
    }

}
