package models.factories;

import models.animals.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalFactoryTest {

    private AnimalFactory factory;

    @BeforeEach
    void setUp() {
        this.factory = AnimalFactory.getInstance();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
        AnimalFactory factory = AnimalFactory.getInstance();
        assertEquals(factory, this.factory);
    }

    @Test
    void createBear() {
        Bear bear = new Bear();
        Bear generatedBear = this.factory.createBear();

        assertNotNull(generatedBear);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(bear.getSpecieName(), generatedBear.getSpecieName());
    }

    @Test
    void createEagle() {
        Eagle eagle = new Eagle();
        Eagle generatedEagle = this.factory.createEagle();

        assertNotNull(generatedEagle);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(eagle.getSpecieName(), generatedEagle.getSpecieName());
    }

    @Test
    void createEagleWithCopulationTurn() {
        Eagle eagle = new Eagle(10);
        Eagle generatedEagle = this.factory.createEagle(10);

        assertNotNull(generatedEagle);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(eagle.getSpecieName(), generatedEagle.getSpecieName());
        assertEquals(eagle.getCopulationTurn(), generatedEagle.getCopulationTurn());
    }

    @Test
    void createFish() {
        Fish fish = new Fish();
        Fish generatedFish = this.factory.createFish();

        assertNotNull(generatedFish);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(fish.getSpecieName(), generatedFish.getSpecieName());
    }

    @Test
    void createFishWithCopulationTurn() {
        Fish fish = new Fish(10);
        Fish generatedFish = this.factory.createFish(10);

        assertNotNull(generatedFish);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(fish.getSpecieName(), generatedFish.getSpecieName());
        assertEquals(fish.getCopulationTurn(), generatedFish.getCopulationTurn());
    }

    @Test
    void createPenguin() {
        Penguin penguin = new Penguin();
        Penguin generatedPenguin = this.factory.createPenguin();

        assertNotNull(generatedPenguin);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(penguin.getSpecieName(), generatedPenguin.getSpecieName());
    }

    @Test
    void createPenguinWithCopulationTurn() {
        Penguin penguin = new Penguin(10);
        Penguin generatedPenguin = this.factory.createPenguin(10);

        assertNotNull(generatedPenguin);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(penguin.getSpecieName(), generatedPenguin.getSpecieName());
        assertEquals(penguin.getCopulationTurn(), generatedPenguin.getCopulationTurn());
    }

    @Test
    void createShark() {
        Shark shark = new Shark();
        Shark generatedShark = this.factory.createShark();

        assertNotNull(generatedShark);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(shark.getSpecieName(), generatedShark.getSpecieName());
    }

    @Test
    void createSharkWithCopulationTurn() {
        Shark shark = new Shark(10);
        Shark generatedShark = this.factory.createShark(10);

        assertNotNull(generatedShark);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(shark.getSpecieName(), generatedShark.getSpecieName());
        assertEquals(shark.getCopulationTurn(), generatedShark.getCopulationTurn());
    }

    @Test
    void createTiger() {
        Tiger tiger = new Tiger();
        Tiger generatedTiger = this.factory.createTiger();

        assertNotNull(generatedTiger);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(tiger.getSpecieName(), generatedTiger.getSpecieName());
    }

    @Test
    void createWhale() {
        Whale whale = new Whale();
        Whale generatedWhale = this.factory.createWhale();

        assertNotNull(generatedWhale);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(whale.getSpecieName(), generatedWhale.getSpecieName());
    }

    @Test
    void createWolf() {
        Wolf wolf = new Wolf();
        Wolf generatedWolf = this.factory.createWolf();

        assertNotNull(generatedWolf);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(wolf.getSpecieName(), generatedWolf.getSpecieName());
    }

}