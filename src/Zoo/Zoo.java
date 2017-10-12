package Zoo;

import Employees.Employee;
import Enclosures.Enclosure;
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

}
