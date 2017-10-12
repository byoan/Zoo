package Employees;

import Enclosures.Enclosure;
import animals.AnimalInterface;

import java.util.ArrayList;
import java.util.Random;

public class Employee implements EmployeeInterface {

    private static Employee instance = null;

    protected String name;
    protected boolean sex;  //True if man
    protected int age;

    private Employee() {
        super();
    }

    public Employee getInstance() {
        if (Employee.instance == null) {
            // synchronized allows use to keep the singleton even when using multiple threads
            synchronized(Employee.class) {
                if (Employee.instance == null) {
                    Employee.instance = new Employee();
                }
            }
        }
        return Employee.instance;
    }

    public boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public void defineAttributes(String name, boolean sex, int age) {
        this.setName(name);
        this.setSex(sex);
        this.setAge(age);
    }

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
            try {
                originEnclosure.getAnimals().remove(animalToTransfer);
                targetEnclosure.getAnimals().add(animalToTransfer);
            } catch(Exception e) {
                System.out.println("The following error was thrown while trying to transfer an animal : " + e.getMessage());
            }
        }
    }
}

