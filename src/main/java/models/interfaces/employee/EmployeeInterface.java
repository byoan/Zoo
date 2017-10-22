package models.interfaces.employee;

import models.enclosures.Enclosure;
import models.interfaces.animal.AnimalInterface;

public interface EmployeeInterface {

    /**
     * Makes the employee perform an inspection of the given enclosure
     * @param enclosure The enclosure to inspect
     * @return The enclosure properties (represents the enclosure inspection process)
     */
    String inspectEnclosure(Enclosure enclosure);

    /**
     * Makes the employee perform a cleaning of the given enclosure
     * @param enclosure The enclosure to clean
     */
    void cleanEnclosure(Enclosure enclosure);

    /**
     * Makes the employee feed all the models.animals of the given enclosure
     * @param enclosure The enclosure to feed
     */
    void feedAnimals(Enclosure enclosure);

    /**
     * Makes the employee perform a transfer of the given animal between 2 enclosures
     * @param originEnclosure The original enclosure of the animal
     * @param targetEnclosure The target enclosure
     * @param animalToTransfer The animal to transfer
     */
    void transferAnimal(Enclosure originEnclosure, Enclosure targetEnclosure, AnimalInterface animalToTransfer);
}
