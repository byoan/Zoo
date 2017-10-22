package models.animals;

import models.interfaces.animal.Mammal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class BearTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void scream() {
    }

    @Test
    void copulate() {
        // Instantiate required values
        Bear bear = new Bear();
        Mammal bear2 = new Bear();
        bear.setSex(false);
        ((Bear)bear2).setSex(true);

        int turnNb = 4;
        bear.childrenCreationTime = 2;
        bear.setCopulationTurn(0);

        bear.copulate(bear2, turnNb);

        assertEquals(4, bear.getCopulationTurn());
    }

    @Test
    void copulateAlreadyPregnant() {
        // Instantiate required values
        Bear bear = new Bear();
        Mammal bear2 = new Bear();
        bear.setSex(false);
        ((Bear)bear2).setSex(true);

        int turnNb = 4;
        bear.childrenCreationTime = 2;
        bear.setCopulationTurn(1);

        bear.copulate(bear2, turnNb);

        assertNotEquals(4, bear.getCopulationTurn());
    }

    @Test
    void checkBirth() {
        // Instantiate required values
        Bear bear = new Bear();
        int turnNb = 4;
        bear.childrenCreationTime = 2;
        bear.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Bear newAnimal = bear.checkBirth(turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(bear.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was reset
        assertEquals(0, bear.getCopulationTurn());
    }

    @Test
    void checkBirthNotPregnantAnimal() {
        Bear bear = new Bear();
        int turnNb = 4;
        bear.childrenCreationTime = 2;
        bear.setCopulationTurn(0);

        assertNull(bear.checkBirth(turnNb));
    }

    @Test
    void birth() {
        Bear bear = new Bear();
        Bear generatedBear = bear.birth();
        assertNotNull(generatedBear);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(bear.getSpecieName(), generatedBear.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(bear, generatedBear);
    }

}