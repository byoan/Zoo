package models.animals;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class PenguinTest {
    @Test
    void getIsHatched() {
        Penguin penguin = new Penguin();
        penguin.setHatched(true);

        assertEquals(true, penguin.getIsHatched());
    }

    @Test
    void setHatched() {
        Penguin penguin = new Penguin();
        penguin.setHatched(true);

        assertEquals(true, penguin.getIsHatched());
    }

    @Test
    void checkIfHatched() {
        Penguin penguin = new Penguin();
        penguin.childrenCreationTime = 2;
        penguin.setCopulationTurn(2);
        int turnNb = 2;

        assertEquals(true, penguin.checkIfHatched(turnNb));
        assertEquals(true, penguin.getIsHatched());
    }

    @Test
    void checkIfHatchedAlreadyHatched() {
        Penguin penguin = new Penguin();
        penguin.setHatched(true);
        int turnNb = 2;

        assertEquals(true, penguin.checkIfHatched(turnNb));
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
        Penguin penguin = new Penguin();
        penguin.setSex(false);
        Penguin penguin2 = new Penguin();
        penguin2.setSex(true);
        int turnNb = 4;
        penguin.childrenCreationTime = 2;
        penguin.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = penguin.copulate(penguin2, turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(penguin.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was properly set
        assertEquals(4, newAnimal.getCopulationTurn());
        assertEquals(false, ((Penguin)newAnimal).getIsHatched());
    }

    @Test
    void copulateSameSex() {
        // Instantiate required values
        Penguin penguin = new Penguin();
        penguin.setSex(true);
        Penguin penguin2 = new Penguin();
        penguin2.setSex(true);
        int turnNb = 4;
        penguin.childrenCreationTime = 2;
        penguin.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = penguin.copulate(penguin, turnNb);

        // Check that we successfully got an animal and not a null
        assertNull(newAnimal);
    }

    @Test
    void lay() {
        Penguin penguin = new Penguin();
        Penguin generatedPenguin = penguin.lay();
        assertNotNull(generatedPenguin);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(penguin.getSpecieName(), generatedPenguin.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(penguin, generatedPenguin);
    }

    @Test
    void fly() {
    }

}