package Controllers.Zoo;

import Models.Employees.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    private Zoo zoo;
    private Simulation simulation;

    @BeforeEach
    void setUp() {
        this.simulation = new Simulation();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void init() {
        this.zoo = this.simulation.getZoo();

        // Simple check that our main objects were properly instantiated
        assertNotNull(this.simulation);
        assertNotNull(this.zoo);

        // Check that the turn number successfully initiated
        assertEquals(0, this.simulation.getTurnNb());

        // Check that we have enclosures and animals within them
        assertNotEquals(0, this.zoo.getEnclosureList().size());
        assertNotEquals(0, this.zoo.getNbAnimalsInZoo());
    }

}