package models.factories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class EnclosureFactoryTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createEnclosure() {
        // Abstract method
    }

    @Test
    void getInstance() {
        int randomType = ThreadLocalRandom.current().nextInt(1, 8);
        EnclosureFactory factory = EnclosureFactory.getInstance(randomType);

        assertNotNull(factory);
    }

}