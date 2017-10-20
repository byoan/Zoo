package Models.Factories.Enclosures;

import Models.Animals.Eagle;
import Models.Enclosures.Aviary;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EagleEnclosureFactoryTest {

    private EagleEnclosureFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = EagleEnclosureFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
        // Reset the attribute used for the numbering of the enclosures names
        this.factory.enclosureNb = 0;
    }

    @Test
    void createEnclosure() {
        Aviary<Eagle> aviary = new Aviary<Eagle>("Eagle Aviary n°1", 10, 50, 10, 2);
        Aviary<Eagle> aviaryFromFactory = this.factory.createEnclosure(false);
        assertEquals(aviary.getName(), aviaryFromFactory.getName());
        assertEquals(aviary.getMaxAnimals(), aviaryFromFactory.getMaxAnimals());
    }

    @Test
    void getInstance() {
        EagleEnclosureFactory eagleEnclosureFactory = EagleEnclosureFactory.getInstance();
        // Check that the singleton returns the same instance
        assertEquals(eagleEnclosureFactory, this.factory);
    }

    @Test
    void generateRandomPopulation() {
        Aviary<Eagle> aviary = this.factory.createEnclosure(true);
        assertNotEquals(0, aviary.getNbAnimals());
    }

}