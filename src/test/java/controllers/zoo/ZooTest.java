package controllers.zoo;

import models.animals.Wolf;
import models.employees.Employee;
import models.enclosures.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    private Zoo zoo;

    @BeforeEach
    void setUp() {
        this.zoo = new Zoo("name", Employee.getInstance(), 10);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getName() {
        assertEquals("name", this.zoo.getName());
    }

    @Test
    void setName() {
        this.zoo.setName("newName");
        assertEquals("newName", this.zoo.getName());
    }

    @Test
    void getEmployee() {
        Employee employee = Employee.getInstance();
        assertEquals(employee, this.zoo.getEmployee());
    }

    @Test
    void getMaxNbEnclosure() {
        // Set a new value just to make sure that it does not return the construction's value
        this.zoo.setMaxNbEnclosure(11);
        assertEquals(11, this.zoo.getMaxNbEnclosure());
    }

    @Test
    void setMaxNbEnclosure() {
        this.zoo.setMaxNbEnclosure(31);
        assertEquals(31, this.zoo.getMaxNbEnclosure());
    }

    @Test
    void setEnclosureList() {
        Enclosure<Wolf> enclosure = new Enclosure<Wolf>("name", 10, 10);
        ArrayList<Enclosure> enclosureList = new ArrayList<Enclosure>();

        enclosureList.add(enclosure);
        this.zoo.setEnclosureList(enclosureList);

        assertEquals(enclosureList, this.zoo.getEnclosureList());
    }

    @Test
    void addEnclosure() {
        Enclosure<Wolf> enclosure = new Enclosure<Wolf>("name", 10, 10);
        ArrayList<Enclosure> currentEnclosureList = this.zoo.getEnclosureList();

        this.zoo.addEnclosure(enclosure);
        currentEnclosureList.add(enclosure);

        assertEquals(currentEnclosureList, this.zoo.getEnclosureList());
    }

    @Test
    void removeEnclosure() {
        Enclosure enclosure = new Enclosure("name", 10, 10);
        this.zoo.addEnclosure(enclosure);

        ArrayList<Enclosure> currentEnclosureList = this.zoo.getEnclosureList();

        this.zoo.removeEnclosure(enclosure);
        currentEnclosureList.remove(enclosure);

        assertEquals(currentEnclosureList, this.zoo.getEnclosureList());
    }

    @Test
    void getNbAnimalsInZoo() {
        int countedNbAnimals = 0;
        for (Enclosure enclosure : this.zoo.getEnclosureList()) {
            countedNbAnimals += enclosure.getNbAnimals();
        }
        int nbAnimals = this.zoo.getNbAnimalsInZoo();

        assertEquals(countedNbAnimals, nbAnimals);
    }

}