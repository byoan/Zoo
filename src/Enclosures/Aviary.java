package Enclosures;

import animals.FlyingAnimal;

import java.util.List;

public class Aviary<A extends FlyingAnimal> extends Enclosure {

    /**
     * The collection of animals that are contained in the enclosure
     */
    private List<A> animals;


}
