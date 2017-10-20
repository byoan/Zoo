package models.factories.enclosures;

import models.animals.Shark;
import models.enclosures.Aquarium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SharkEnclosureFactoryTest {

    private SharkEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = SharkEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Aquarium<Shark> aquarium = new Aquarium<Shark>("Shark Aquarium n°1", 10, 50, 2, 100, 2);
        Aquarium<Shark> aquariumFromFactory = this.factory.createEnclosure(false);

        assertEquals(aquarium.getName(), aquariumFromFactory.getName());
        assertEquals(aquarium.getMaxAnimals(), aquariumFromFactory.getMaxAnimals());
        assertEquals(aquarium.getDepth(), aquariumFromFactory.getDepth());
        assertEquals(aquarium.getCurrentWaterLevel(), aquariumFromFactory.getCurrentWaterLevel());
        assertEquals(aquarium.getSalinity(), aquariumFromFactory.getSalinity());
    }

    @Test
    void getInstance() {
        SharkEnclosureFactory sharkEnclosureFactory = SharkEnclosureFactory.getInstance();
        // Check that the singleton returns the same instance
        assertEquals(sharkEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Aquarium<Shark> aquarium = this.factory.createEnclosure(true);
        assertNotEquals(0, aquarium.getNbAnimals());
    }

}