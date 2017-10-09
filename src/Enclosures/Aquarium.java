package Enclosures;

import animals.AnimalInterface;
import animals.MarineAnimal;

import java.util.List;

public class Aquarium<A extends MarineAnimal> extends Enclosure {

    /**
     * The collection of animals that are contained in the enclosure
     */
    private List<A> animals;
}
