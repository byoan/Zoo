package models.factories.enclosures;

import models.animals.Tiger;
import models.enclosures.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TigerEnclosureFactoryTest {

    private TigerEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = TigerEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Enclosure<Tiger> enclosure = new Enclosure<Tiger>("Tiger Enclosure n°1", 10, 50);
        Enclosure<Tiger> enclosureFromFactory = this.factory.createEnclosure(false);
        assertEquals(enclosure.getName(), enclosureFromFactory.getName());
        assertEquals(enclosure.getMaxAnimals(), enclosureFromFactory.getMaxAnimals());
    }

    @Test
    void getInstance() {
        TigerEnclosureFactory tigerEnclosureFactory = TigerEnclosureFactory.getInstance();
        // Check that the singleton returns the same instance
        assertEquals(tigerEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Enclosure<Tiger> enclosure = this.factory.createEnclosure(true);
        assertNotEquals(0, enclosure.getNbAnimals());
    }

}