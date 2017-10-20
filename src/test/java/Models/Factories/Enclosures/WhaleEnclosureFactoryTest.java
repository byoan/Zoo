package Models.Factories.Enclosures;

import Models.Animals.Whale;
import Models.Enclosures.Aquarium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhaleEnclosureFactoryTest {

    private WhaleEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = WhaleEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Aquarium<Whale> aquarium = new Aquarium<Whale>("Whale Aquarium n°1", 10, 50, 2, 100, 2);
        Aquarium<Whale> aquariumFromFactory = this.factory.createEnclosure(false);

        assertEquals(aquarium.getName(), aquariumFromFactory.getName());
        assertEquals(aquarium.getMaxAnimals(), aquariumFromFactory.getMaxAnimals());
        assertEquals(aquarium.getDepth(), aquariumFromFactory.getDepth());
        assertEquals(aquarium.getCurrentWaterLevel(), aquariumFromFactory.getCurrentWaterLevel());
        assertEquals(aquarium.getSalinity(), aquariumFromFactory.getSalinity());
    }

    @Test
    void getInstance() {
        WhaleEnclosureFactory whaleEnclosureFactory = WhaleEnclosureFactory.getInstance();
        // Check that the singleton returns the same instance
        assertEquals(whaleEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Aquarium<Whale> aquarium = this.factory.createEnclosure(true);
        assertNotEquals(0, aquarium.getNbAnimals());
    }

}