package Enclosures;

import animals.AnimalInterface;
import animals.MarineAnimal;

import java.util.List;

public class Aquarium extends Enclosure {

    public Aquarium(AnimalInterface type) {
        this.setType(type);
    }
}
