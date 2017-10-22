package models.animals;

import models.interfaces.animal.Mammal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TigerTest {
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
        Tiger tiger = new Tiger();
        Mammal tiger2 = new Tiger();
        tiger.setSex(false);
        ((Tiger)tiger2).setSex(true);

        int turnNb = 4;
        tiger.childrenCreationTime = 2;
        tiger.setCopulationTurn(0);

        tiger.copulate(tiger2, turnNb);

        assertEquals(4, tiger.getCopulationTurn());
    }

    @Test
    void copulateAlreadyPregnant() {
        // Instantiate required values
        Tiger tiger = new Tiger();
        Mammal tiger2 = new Tiger();
        tiger.setSex(false);
        ((Tiger)tiger2).setSex(true);

        int turnNb = 4;
        tiger.childrenCreationTime = 2;
        tiger.setCopulationTurn(1);

        tiger.copulate(tiger2, turnNb);

        assertNotEquals(4, tiger.getCopulationTurn());
    }

    @Test
    void checkBirth() {
        // Instantiate required values
        Tiger tiger = new Tiger();
        int turnNb = 4;
        tiger.childrenCreationTime = 2;
        tiger.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Tiger newAnimal = tiger.checkBirth(turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(tiger.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was reset
        assertEquals(0, tiger.getCopulationTurn());
    }

    @Test
    void checkBirthNotPregnantAnimal() {
        Tiger tiger = new Tiger();
        int turnNb = 4;
        tiger.childrenCreationTime = 2;
        tiger.setCopulationTurn(0);

        assertNull(tiger.checkBirth(turnNb));
    }

    @Test
    void birth() {
        Tiger tiger = new Tiger();
        Tiger generatedTiger = tiger.birth();
        assertNotNull(generatedTiger);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(tiger.getSpecieName(), generatedTiger.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(tiger, generatedTiger);
    }

    @Test
    void wander() {

    }

}