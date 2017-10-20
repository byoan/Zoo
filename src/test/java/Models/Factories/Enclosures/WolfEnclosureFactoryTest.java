package Models.Factories.Enclosures;

import Models.Animals.Wolf;
import Models.Enclosures.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WolfEnclosureFactoryTest {

    private WolfEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = WolfEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Enclosure<Wolf> enclosure = new Enclosure<Wolf>("Wolf Enclosure n°1", 10, 50);
        Enclosure<Wolf> enclosureFromFactory = this.factory.createEnclosure(false);
        assertEquals(enclosure.getName(), enclosureFromFactory.getName());
        assertEquals(enclosure.getMaxAnimals(), enclosureFromFactory.getMaxAnimals());
    }

    @Test
    void getInstance() {
        WolfEnclosureFactory wolfEnclosureFactory = WolfEnclosureFactory.getInstance();
        // Check that the singleton returns the same instance
        assertEquals(wolfEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Enclosure<Wolf> enclosure = this.factory.createEnclosure(true);
        assertNotEquals(0, enclosure.getNbAnimals());
    }

}