package Employees;

import Enclosures.Enclosure;
import animals.AnimalInterface;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class Employee implements EmployeeInterface {

    protected String name;
    protected boolean sex;  //True if man
    protected int age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void inspectEnclosure(Enclosure enclosure) {
        enclosure.toString();
    }

    public void cleanEnclosure(Enclosure enclosure) {
        enclosure.cleanEnclosure();
    }

    public void feedAnimals(Enclosure enclosure) {
        ArrayList<AnimalInterface> animals = enclosure.getAnimals();
        for (AnimalInterface animal : animals) {
            animal.eat();
        }
    }

    public void transferAnimal(Enclosure originEnclosure, Enclosure targetEnclosure, AnimalInterface animalToTransfer) {
        if (targetEnclosure.getNbAnimals() >= targetEnclosure.getAnimals().size() + 1) {
            originEnclosure.getAnimals().remove(animalToTransfer);
            targetEnclosure.getAnimals().add(animalToTransfer);
        }
    }
}

