package Employees;

import Enclosures.Enclosure;
import animals.AnimalInterface;

public interface EmployeeInterface {

    /**
     * Makes the Employee perform an inspection of the given enclosure
     * @param enclosure The enclosure to inspect
     * @return The enclosure properties (represents the enclosure inspection process)
     */
    public String inspectEnclosure(Enclosure enclosure);

    /**
     * Makes the Employee perform a cleaning of the given enclosure
     * @param enclosure The enclosure to clean
     */
    public void cleanEnclosure(Enclosure enclosure);

    /**
     * Makes the Employee feed all the animals of the given enclosure
     * @param enclosure The enclosure to feed
     */
    public void feedAnimals(Enclosure enclosure);

    /**
     * Makes the Employee perform a transfer of the given animal between 2 enclosures
     * @param originEnclosure The original enclosure of the animal
     * @param targetEnclosure The target enclosure
     * @param animalToTransfer The animal to transfer
     */
    public void transferAnimal(Enclosure originEnclosure, Enclosure targetEnclosure, AnimalInterface animalToTransfer);
}
