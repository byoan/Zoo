package controllers.zoo;

import models.employees.Employee;
import models.enclosures.Enclosure;
import models.exceptions.zoo.CantFindEnclosureByNameException;
import models.exceptions.zoo.EmptyZooException;
import models.exceptions.zoo.EnclosureNotInZooException;
import models.exceptions.zoo.FullZooException;
import models.interfaces.animal.AnimalInterface;
import views.View;

import java.util.ArrayList;

/**
 * Represents the Zoo of the simulation
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class Zoo {

    /**
     * Represents the name of the controllers.zoo
     */
    private String name;

    /**
     * Represents the employee instance of the controllers.zoo
     */
    private Employee employee;

    /**
     * Represents the maximum number of enclosure that this controllers.zoo can contain
     */
    private int maxNbEnclosure;

    /**
     * Represents all the enclosures contained by this controllers.zoo
     */
    private ArrayList<Enclosure> enclosureList;

    /**
     * Constructor for the controllers.zoo class
     * @param name The name of our controllers.zoo
     * @param employee An employee instance which represents our unique employee of the controllers.zoo
     * @param maxNbEnclosure The max number of enclosure that the controllers.zoo can contain
     */
    public Zoo(String name, Employee employee, int maxNbEnclosure) {
        this.name = name;
        this.employee = employee;
        this.maxNbEnclosure = maxNbEnclosure;
        this.setEnclosureList(new ArrayList<Enclosure>());
    }

    /**
     * Getter for our controllers.zoo name
     * @return The controllers.zoo name attribute
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the controllers.zoo name
     * @param name The new name to give to the controllers.zoo
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for our employee of the zoo
     * @return employee The employee attribute of the zoo class
     */
    public Employee getEmployee() {
        return this.employee;
    }

    /**
     * Getter for the maximum number of enclosure that the controllers.zoo can contain
     * @return int The attribute representing maximum number of enclosure that can be contained
     */
    public int getMaxNbEnclosure() {
        return this.maxNbEnclosure;
    }

    /**
     * Setter for the maximum number of enclosure that the controllers.zoo can contain
     * @param maxNbEnclosure The new maximum number of enclosures
     */
    public void setMaxNbEnclosure(int maxNbEnclosure) {
        this.maxNbEnclosure = maxNbEnclosure;
    }

    /**
     * Getter for the enclosures contained by the zoo
     * @return An ArrayList of enclosures
     */
    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    /**
     * Setter for the enclosure list of the controllers.zoo
     * @param enclosureList The new enclosures list
     */
    public void setEnclosureList(ArrayList<Enclosure> enclosureList) {
        this.enclosureList = enclosureList;
    }

    /**
     * Allows to add a new Enclosure to the controllers.zoo
     * Will check if there is enough size in the controllers.zoo before proceeding
     * @param enclosure The Enclosure to add
     * @param <A> Generic type that can be used in the method
     */
    public <A extends AnimalInterface> void addEnclosure(Enclosure<A> enclosure) {
        try {
            if (this.getEnclosureList().size() < this.getMaxNbEnclosure()) {
                this.getEnclosureList().add(enclosure);
            } else {
                throw new FullZooException(enclosure, this);
            }
        } catch (FullZooException e) {
            View.displayErrorMessage(e.getMessage());
        } catch(Exception e) {
            View.displayErrorMessage("An error occurred while trying to add the " + enclosure.getName() + " enclosure to the zoo : " + e.getMessage());
        }
    }

    /**
     * Allows to remove an Enclosure from the controllers.zoo
     * Will check if the Enclosure is in the controllers.zoo before proceeding to the removal
     * @param enclosure The Enclosure to remove
     */
    public void removeEnclosure(Enclosure<AnimalInterface> enclosure) {
        try {
            if (this.getEnclosureList().contains(enclosure)) {
                this.getEnclosureList().remove(enclosure);
            } else {
                throw new EnclosureNotInZooException(enclosure, this);
            }
        } catch (EnclosureNotInZooException e) {
            View.displayErrorMessage(e.getMessage());
        } catch (Exception e) {
            View.displayErrorMessage("An error occurred while trying to remove the " + enclosure.getName() + " from the zoo : " + e.getMessage());
        }
    }

    /**
     * Allows to retrieve the first Enclosure instance from our controllers.zoo's enclosures list that matches the given name
     * @param enclosureName The name of the searched enclosure
     * @return mixed The first Enclosure instance that matches this name, or null if there isn't any match
     */
    public Enclosure getEnclosureByName(String enclosureName) {
        try {
            if (!this.getEnclosureList().isEmpty()) {
                for (int i = 0; i <= this.getEnclosureList().size(); i++) {
                    if (this.getEnclosureList().get(i).getName() == enclosureName) {
                        return this.getEnclosureList().get(i);
                    }
                }
                // In case we did not returned any enclosure that matched the given name
                throw new CantFindEnclosureByNameException(enclosureName);
            } else {
                // If the size is equals to 0, the zoo has no enclosures to search in
                throw new EmptyZooException(this);
            }
        } catch (CantFindEnclosureByNameException e) {
            View.displayErrorMessage(e.getMessage());
        } catch (EmptyZooException e) {
            View.displayErrorMessage(e.getMessage());
        } catch (Exception e) {
            View.displayErrorMessage("An error occurred while trying to search the enclosure named " + enclosureName + " in the zoo");
        }
        return null;
    }

    /**
     * Getter for the current number of animals that are in the zoo
     * Will iterate over the enclosures list to retrieve each enclosure's number of animals
     * @return The total number of animals in the zoo
     */
    public int getNbAnimalsInZoo() {
        int nbAnimals = 0;
        for (Enclosure enclosure : this.getEnclosureList()) {
            nbAnimals += enclosure.getNbAnimals();
        }
        return nbAnimals;
    }

    /**
     * Allows to display all the characteristics of each animal in the zoo
     */
    public void displayAnimalsFromAllEnclosures() {
        for (Enclosure enclosure : this.getEnclosureList()) {
            ArrayList<AnimalInterface> animalList = enclosure.getAnimals();
            for (AnimalInterface animal : animalList) {
                View.displayMessage(animal.toString());
            }
        }
    }

    /**
     * Represents the main Class of the Zoo
     * @param args Of the Zoo
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.init();
    }

}
