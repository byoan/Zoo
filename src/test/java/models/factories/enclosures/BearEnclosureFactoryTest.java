package models.factories.enclosures;

import models.animals.Bear;
import models.enclosures.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BearEnclosureFactoryTest {

    private BearEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = BearEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Enclosure<Bear> enclosure = new Enclosure<Bear>("Bear Enclosure n°1", 10, 50);
        Enclosure<Bear> enclosureFromFactory = this.factory.createEnclosure(false);
        assertEquals(enclosure.getName(), enclosureFromFactory.getName());
        assertEquals(enclosure.getMaxAnimals(), enclosureFromFactory.getMaxAnimals());
    }

    @Test
    void getInstance() {
        BearEnclosureFactory bearEnclosureFactory = BearEnclosureFactory.getInstance();
        // Check that the singleton returns the same instance
        assertEquals(bearEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Enclosure<Bear> enclosure = this.factory.createEnclosure(true);
        assertNotEquals(0, enclosure.getNbAnimals());
    }

}