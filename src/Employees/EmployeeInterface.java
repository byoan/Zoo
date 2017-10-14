package Employees;

import Enclosures.Enclosure;
import animals.AnimalInterface;

public interface EmployeeInterface {

    public String inspectEnclosure(Enclosure enclosure);

    public void cleanEnclosure(Enclosure enclosure);

    public void feedAnimals(Enclosure enclosure);

    public void transferAnimal(Enclosure originEnclosure, Enclosure targetEnclosure, AnimalInterface animalToTransfer);
}
