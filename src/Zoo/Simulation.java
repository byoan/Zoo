package Zoo;

import Core.Factories.AnimalFactory;
import Employees.Employee;
import Enclosures.Enclosure;
import animals.AnimalInterface;
import animals.Tiger;
import animals.Whale;

public class Simulation {

    private Employee employee;
    private Zoo zoo;
    private int turnNb;

    public Employee getEmployee() {
        return employee;
    }

    public int getTurnNb() {
        return this.turnNb;
    }

    public void setTurnNb(int turnNb) {
        // Add this test to prevent going back to a previous turn
        if (turnNb >= this.getTurnNb()) {
            this.turnNb = turnNb;
        } else {
            System.out.println("Error while trying to set the turn number to " + turnNb + ", can't go back to a previous turn");
        }
    }

    public void nextTurn() {
        this.setTurnNb(this.getTurnNb() + 1);
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Zoo getZoo() {
        return this.zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public void init() {
        this.setTurnNb(0);
        this.setEmployee(Employee.getInstance());
        this.setZoo(new Zoo("My Zoo", this.getEmployee(), 1));

        Enclosure<Tiger> tigerEnclosure = new Enclosure<Tiger>("Tiger Enclosure", 10, 2);
        Tiger tiger = AnimalFactory.getInstance().createTiger();
        Whale whale = AnimalFactory.getInstance().createWhale();

        employee = this.getEmployee();
        zoo = this.getZoo();
        System.out.println(tigerEnclosure.getClass());
        System.out.println();
        zoo.addEnclosure(tigerEnclosure);
        zoo.getEnclosureByName("Tiger Enclosure").add(tiger);
        zoo.getEnclosureByName("Tiger Enclosure").add(whale);
        System.out.println(zoo.getEnclosureByName("Tiger Enclosure").toString());
        System.out.println(zoo.getEnclosureList());
    }
}
