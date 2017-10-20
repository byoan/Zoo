package models.factories.enclosures;

import models.animals.Penguin;
import models.enclosures.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PenguinEnclosureFactoryTest {

    private PenguinEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = PenguinEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Enclosure<Penguin> enclosure = new Enclosure<Penguin>("Penguin Enclosure n°1", 10, 50);
        Enclosure<Penguin> enclosureFromFactory = this.factory.createEnclosure(false);
        assertEquals(enclosure.getName(), enclosureFromFactory.getName());
        assertEquals(enclosure.getMaxAnimals(), enclosureFromFactory.getMaxAnimals());
    }

    @Test
    void getInstance() {
        PenguinEnclosureFactory penguinEnclosureFactory = PenguinEnclosureFactory.getInstance().getInstance();
        // Check that the singleton returns the same instance
        assertEquals(penguinEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Enclosure<Penguin> enclosure = this.factory.createEnclosure(true);
        assertNotEquals(0, enclosure.getNbAnimals());
    }

}