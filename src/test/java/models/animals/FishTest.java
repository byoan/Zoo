package models.animals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FishTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIsHatched() {
        Fish fish = new Fish();
        fish.setHatched(true);

        assertEquals(true, fish.getIsHatched());
    }

    @Test
    void setHatched() {
        Fish fish = new Fish();
        fish.setHatched(true);

        assertEquals(true, fish.getIsHatched());
    }

    @Test
    void checkIfHatched() {
        Fish fish = new Fish();
        fish.childrenCreationTime = 2;
        fish.setCopulationTurn(2);
        int turnNb = 2;

        assertEquals(true, fish.checkIfHatched(turnNb));
        assertEquals(true, fish.getIsHatched());
    }

    @Test
    void checkIfHatchedAlreadyHatched() {
        Fish fish = new Fish();
        fish.setHatched(true);
        int turnNb = 2;

        assertEquals(true, fish.checkIfHatched(turnNb));
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
        Fish fish = new Fish();
        fish.setSex(false);
        Fish fish2 = new Fish();
        fish2.setSex(true);
        int turnNb = 4;
        fish.childrenCreationTime = 2;
        fish.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = fish.copulate(fish2, turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(fish.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was properly set
        assertEquals(4, newAnimal.getCopulationTurn());
        assertEquals(false, ((Fish)newAnimal).getIsHatched());
    }

    @Test
    void copulateSameSex() {
        // Instantiate required values
        Fish fish = new Fish();
        fish.setSex(true);
        Fish fish2 = new Fish();
        fish2.setSex(true);
        int turnNb = 4;
        fish.childrenCreationTime = 2;
        fish.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = fish.copulate(fish2, turnNb);

        // Check that we successfully got an animal and not a null
        assertEquals(null, newAnimal);
    }

    @Test
    void lay() {
        Fish fish = new Fish();
        Fish generatedFish = fish.lay();
        assertNotNull(generatedFish);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(fish.getSpecieName(), generatedFish.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(fish, generatedFish);
    }

}