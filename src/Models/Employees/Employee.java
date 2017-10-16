package Employees;

import Enclosures.Enclosure;
import animals.AnimalInterface;

import java.util.ArrayList;
import java.util.Random;

public class Employee implements EmployeeInterface {

    /**
     * Represents the employee instance, used by the Singleton's getInstance
     */
    private static Employee instance = null;

    /**
     * Represents the name of the employee
     */
    protected String name;

    /**
     * Represents the sex of the Employee
     * Randomly generated if not redefined in the defineAttributes method
     */
    protected boolean sex = this.getRandomBoolean();

    /**
     * Represents the age of the employee
     */
    protected int age;

    /**
     * Constructor of the Employee
     * Private visibility as we want this class to be a Singleton
     */
    private Employee() {
        super();
    }

    /**
     * Allows to retrieve the Employee instance (Singleton)
     * Will create a new one before the return on first call
     * @return Employee The employee instance
     */
    public static Employee getInstance() {
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

    /**
     * Allows to generate a random boolean (used for sex)
     * @return A random boolean (true or false)
     */
    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    /**
     * Allows to define all attributes of our unique instance of Employee (which is not accessible anyway)
     * @param name The name of the employee
     * @param sex The sex of the employee
     * @param age The age of the employee
     */
    public void defineAttributes(String name, boolean sex, int age) {
        this.setName(name);
        this.setSex(sex);
        this.setAge(age);
    }

    /**
     * Getter for the name of the employee
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for the name of the employee
     * @param name The new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the sex of the employee
     * @return The sex of the employee
     */
    public boolean getSex() {
        return this.sex;
    }

    /**
     * Setter for the sex attribute of the employee
     * @param sex The new sex of the employee
     */
    public void setSex(boolean sex) {
        this.sex = sex;
    }

    /**
     * Getter for the age attribute of the employee
     * @return The age of the employee
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Setter for the age attribute of the employee
     * @param age The new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Allows to make the employee perform the inspection of the given enclosure
     * @param enclosure The enclosure to inspect
     * @return The enclosure inspection (as its properties)
     */
    public String inspectEnclosure(Enclosure enclosure) {
        return enclosure.toString();
    }

    /**
     * Allows to make the employee perform a clean of the given enclosure
     * @param enclosure The enclosure to clean
     */
    public void cleanEnclosure(Enclosure enclosure) {
        enclosure.cleanEnclosure();
    }

    /**
     * Allows to make the employee feed all the animals of the given enclosure
     * @param enclosure The enclosure to feed
     */
    public void feedAnimals(Enclosure enclosure) {
        ArrayList<AnimalInterface> animals = enclosure.getAnimals();
        for (AnimalInterface animal : animals) {
            animal.eat();
        }
    }

    public <A extends AnimalInterface> void feedAnimal(A animal) {
        animal.eat();
    }

    public <A extends AnimalInterface> void healAnimal(A animal) {
        animal.heal();
    }

    /**
     * Allows to make the employee perform an animal transfer between 2 enclosures
     * @param originEnclosure The origin enclosure of the animal
     * @param targetEnclosure The target enclosure for the animal
     * @param animalToTransfer The animal to transfer
     */
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

