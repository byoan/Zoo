package models.animals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class SharkTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIsHatched() {
        Shark shark = new Shark();
        shark.setHatched(true);

        assertEquals(true, shark.getIsHatched());
    }

    @Test
    void setHatched() {
        Shark shark = new Shark();
        shark.setHatched(true);

        assertEquals(true, shark.getIsHatched());
    }

    @Test
    void checkIfHatched() {
        Shark shark = new Shark();
        shark.childrenCreationTime = 2;
        shark.setCopulationTurn(2);
        int turnNb = 2;

        assertEquals(true, shark.checkIfHatched(turnNb));
        assertEquals(true, shark.getIsHatched());
    }

    @Test
    void checkIfHatchedAlreadyHatched() {
        Shark shark = new Shark();
        shark.setHatched(true);
        int turnNb = 2;

        assertEquals(true, shark.checkIfHatched(turnNb));
    }

    @Test
    void scream() {
    }

    @Test
    void copulate() {
        // Instantiate required values
        Shark shark = new Shark();
        shark.setSex(false);
        Shark shark2 = new Shark();
        shark2.setSex(true);
        int turnNb = 4;
        shark.childrenCreationTime = 2;
        shark.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = shark.copulate(shark2, turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(shark.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was properly set
        assertEquals(4, newAnimal.getCopulationTurn());
        assertEquals(false, ((Shark)newAnimal).getIsHatched());
    }

    @Test
    void copulateSameSex() {
        // Instantiate required values
        Shark shark = new Shark();
        shark.setSex(true);
        Shark shark2 = new Shark();
        shark2.setSex(true);
        int turnNb = 4;
        shark.childrenCreationTime = 2;
        shark.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = shark.copulate(shark2, turnNb);

        // Check that we successfully got an animal and not a null
        assertNull(newAnimal);
    }

    @Test
    void lay() {
        Shark shark = new Shark();
        Shark generatedShark = shark.lay();
        assertNotNull(generatedShark);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(shark.getSpecieName(), generatedShark.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(shark, generatedShark);
    }

    @Test
    void fly() {
    }

    @Test
    void swim() {

    }

}