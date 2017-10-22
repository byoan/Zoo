package models.animals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class EagleTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getIsHatched() {
        Eagle eagle = new Eagle();
        eagle.setHatched(true);

        assertEquals(true, eagle.getIsHatched());
    }

    @Test
    void setHatched() {
        Eagle eagle = new Eagle();
        eagle.setHatched(true);

        assertEquals(true, eagle.getIsHatched());
    }

    @Test
    void checkIfHatched() {
        Eagle eagle = new Eagle();
        eagle.childrenCreationTime = 2;
        eagle.setCopulationTurn(2);
        int turnNb = 2;

        assertEquals(true, eagle.checkIfHatched(turnNb));
        assertEquals(true, eagle.getIsHatched());
    }

    @Test
    void checkIfHatchedAlreadyHatched() {
        Eagle eagle = new Eagle();
        eagle.setHatched(true);
        int turnNb = 2;

        assertEquals(true, eagle.checkIfHatched(turnNb));
    }

    @Test
    void scream() {
    }

    @Test
    void copulate() {
        // Instantiate required values
        Eagle eagle = new Eagle();
        eagle.setSex(false);
        Eagle eagle2 = new Eagle();
        eagle2.setSex(true);
        int turnNb = 4;
        eagle.childrenCreationTime = 2;
        eagle.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = eagle.copulate(eagle2, turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(eagle.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was properly set
        assertEquals(4, newAnimal.getCopulationTurn());
        assertEquals(false, ((Eagle)newAnimal).getIsHatched());
    }

    @Test
    void copulateSameSex() {
        // Instantiate required values
        Eagle eagle = new Eagle();
        eagle.setSex(true);
        Eagle eagle2 = new Eagle();
        eagle2.setSex(true);
        int turnNb = 4;
        eagle.childrenCreationTime = 2;
        eagle.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Animal newAnimal = eagle.copulate(eagle2, turnNb);

        // Check that we successfully got an animal and not a null
        assertNull(newAnimal);
    }

    @Test
    void lay() {
        Eagle eagle = new Eagle();
        Eagle generatedEagle = eagle.lay();
        assertNotNull(generatedEagle);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(eagle.getSpecieName(), generatedEagle.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(eagle, generatedEagle);
    }

    @Test
    void fly() {
    }

}