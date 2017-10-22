package models.animals;

import models.interfaces.animal.Mammal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WhaleTest {
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
    void swim() {
    }

    @Test
    void copulate() {
        // Instantiate required values
        Whale whale = new Whale();
        Mammal whale2 = new Whale();
        whale.setSex(false);
        ((Whale)whale2).setSex(true);

        int turnNb = 4;
        whale.childrenCreationTime = 2;
        whale.setCopulationTurn(0);

        whale.copulate(whale2, turnNb);

        assertEquals(4, whale.getCopulationTurn());
    }

    @Test
    void copulateAlreadyPregnant() {
        // Instantiate required values
        Whale whale = new Whale();
        Mammal whale2 = new Whale();
        whale.setSex(false);
        ((Whale)whale2).setSex(true);

        int turnNb = 4;
        whale.childrenCreationTime = 2;
        whale.setCopulationTurn(1);

        whale.copulate(whale2, turnNb);

        assertNotEquals(4, whale.getCopulationTurn());
    }

    @Test
    void checkBirth() {
        // Instantiate required values
        Whale whale = new Whale();
        int turnNb = 4;
        whale.childrenCreationTime = 2;
        whale.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Whale newAnimal = whale.checkBirth(turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(whale.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was reset
        assertEquals(0, whale.getCopulationTurn());
    }

    @Test
    void checkBirthNotPregnantAnimal() {
        Whale whale = new Whale();
        int turnNb = 4;
        whale.childrenCreationTime = 2;
        whale.setCopulationTurn(0);

        assertNull(whale.checkBirth(turnNb));
    }

    @Test
    void birth() {
        Whale whale = new Whale();
        Whale generatedWhale = whale.birth();
        assertNotNull(generatedWhale);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(whale.getSpecieName(), generatedWhale.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(whale, generatedWhale);
    }

}