package Zoo;

import Employees.Employee;
import Enclosures.Enclosure;
import Enclosures.EnclosureInterface;
import animals.AnimalInterface;

import java.util.ArrayList;

public class Zoo {

    private String name;
    private Employee employee;
    private int maxNbEnclosure;
    private ArrayList<Enclosure> enclosureList;

    public Zoo(String name, Employee employee, int maxNbEnclosure) {
        this.name = name;
        this.employee = employee;
        this.maxNbEnclosure = maxNbEnclosure;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getMaxNbEnclosure() {
        return this.maxNbEnclosure;
    }

    public void setMaxNbEnclosure(int maxNbEnclosure) {
        this.maxNbEnclosure = maxNbEnclosure;
    }

    public ArrayList<Enclosure> getEnclosureList() {
        return this.enclosureList;
    }

    public void setEnclosureList(ArrayList<Enclosure> enclosureList) {
        this.enclosureList = enclosureList;
    }

    public void addEnclosure(Enclosure<AnimalInterface> enclosure) {
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

    public void removeEnclosure(Enclosure<AnimalInterface> enclosure) {
        try {
            this.getEnclosureList().remove(enclosure);
        } catch(Exception e) {
            // TODO : throw exception
            System.out.println("An error occurred while trying to remove this enclosure from the zoo : " + e.getMessage());
        }
    }

    public int getNbAnimalsInZoo() {
        int nbAnimals = 0;
        for (Enclosure enclosure : this.getEnclosureList()) {
            nbAnimals += enclosure.getNbAnimals();
        }
        return nbAnimals;
    }

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
