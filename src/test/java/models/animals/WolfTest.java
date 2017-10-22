package models.animals;

import models.animals.packs.WolfPack;
import models.enums.WolfRank;
import models.interfaces.animal.Mammal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WolfTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getPack() {
        Wolf wolf = new Wolf();
        WolfPack pack = new WolfPack();
        // WolfPack.add() will call wolf.setRank()
        pack.add(wolf);

        assertEquals(pack, wolf.getPack());
    }

    @Test
    void setPack() {
        Wolf wolf = new Wolf();
        WolfPack pack = new WolfPack();
        // WolfPack.add() will call wolf.setRank()
        pack.add(wolf);

        assertEquals(pack, wolf.getPack());
    }

    @Test
    void setStrength() {
        Wolf wolf = new Wolf();
        wolf.setStrength(500);

        assertEquals(500, wolf.getStrength());
    }

    @Test
    void generateWolfLevel() {
        Wolf wolf = new Wolf();
        WolfPack pack = new WolfPack();
        pack.add(wolf);
        wolf.generateWolfLevel();

        assertNotNull(wolf.getLevel());
    }

    @Test
    void getLevel() {
        Wolf wolf = new Wolf();
        wolf.setLevel(50);

        assertEquals(50, wolf.getLevel());
    }

    @Test
    void setLevel() {
        Wolf wolf = new Wolf();
        wolf.setLevel(50);

        assertEquals(50, wolf.getLevel());
    }

    @Test
    void attemptDomination() {
        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        WolfPack pack = new WolfPack();
        pack.add(wolf);
        pack.add(wolf2);

        wolf.setRank(WolfRank.OMICRON);
        wolf.setLevel(2);
        wolf2.setRank(WolfRank.BETA);
        wolf2.setLevel(1);

        wolf.attemptDomination(wolf2);

        assertEquals(WolfRank.BETA, wolf.getRank());
        assertEquals(110, wolf.getDominationFactor());
        assertEquals(WolfRank.OMICRON, wolf2.getRank());
        assertEquals(90, wolf2.getDominationFactor());
    }

    @Test
    void attemptDominationSuperiorRank() {
        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        WolfPack pack = new WolfPack();
        pack.add(wolf);
        pack.add(wolf2);

        wolf.setRank(WolfRank.BETA);
        wolf.setLevel(2);
        wolf2.setRank(WolfRank.OMICRON);
        wolf2.setLevel(1);

        wolf.attemptDomination(wolf2);

        assertEquals(WolfRank.BETA, wolf.getRank());
        assertEquals(100, wolf.getDominationFactor());
        assertEquals(WolfRank.OMICRON, wolf2.getRank());
        assertEquals(100, wolf2.getDominationFactor());
    }

    @Test
    void attemptDominationOmega() {
        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        WolfPack pack = new WolfPack();
        pack.add(wolf);
        pack.add(wolf2);

        wolf.setRank(WolfRank.BETA);
        wolf.setLevel(1);
        wolf2.setRank(WolfRank.OMEGA);
        wolf2.setLevel(2);

        wolf.attemptDomination(wolf2);

        assertEquals(WolfRank.BETA, wolf.getRank());
        assertEquals(110, wolf.getDominationFactor());
        assertEquals(WolfRank.OMEGA, wolf2.getRank());
        assertEquals(100, wolf2.getDominationFactor());
    }

    @Test
    void attemptDominationAlpha() {
        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        WolfPack pack = new WolfPack();
        pack.add(wolf);
        pack.add(wolf2);

        wolf.setRank(WolfRank.ALPHA);
        wolf.setLevel(1);
        wolf2.setRank(WolfRank.BETA);
        wolf2.setLevel(2);

        wolf.attemptDomination(wolf2);

        assertEquals(WolfRank.ALPHA, wolf.getRank());
        assertEquals(100, wolf.getDominationFactor());
        assertEquals(WolfRank.BETA, wolf2.getRank());
        assertEquals(100, wolf2.getDominationFactor());
    }

    @Test
    void attemptDominationAlphaSuccess() {
        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        WolfPack pack = new WolfPack();
        pack.add(wolf);
        pack.add(wolf2);

        wolf.setRank(WolfRank.ALPHA);
        wolf.setLevel(1);
        wolf2.setRank(WolfRank.BETA);
        wolf2.setLevel(2);

        wolf2.attemptDomination(wolf);

        // Null because he got kicked
        assertNull(wolf.getRank());
        assertEquals(WolfRank.ALPHA, wolf2.getRank());
        assertEquals(110, wolf2.getDominationFactor());
    }

    @Test
    void swapRanks() {
        Wolf wolf = new Wolf();
        Wolf wolf2 = new Wolf();
        wolf.setRank(WolfRank.BETA);
        wolf2.setRank(WolfRank.CHI);

        wolf.swapRanks(wolf2);

        assertEquals(wolf.getRank(), WolfRank.CHI);
        assertEquals(wolf2.getRank(), WolfRank.BETA);
    }

    @Test
    void getDominationFactor() {
        Wolf wolf = new Wolf();
        wolf.setDominationFactor(200);

        assertEquals(200, wolf.getDominationFactor());
    }

    @Test
    void setDominationFactor() {
        Wolf wolf = new Wolf();
        wolf.setDominationFactor(300);

        assertEquals(300, wolf.getDominationFactor());
    }

    @Test
    void setImpetuosity() {
        Wolf wolf = new Wolf();
        wolf.setImpetuosity(200);

        assertEquals(200, wolf.getImpetuosity());
    }

    @Test
    void increaseDominationFactor() {
        Wolf wolf = new Wolf();
        wolf.setDominationFactor(1000);
        wolf.increaseDominationFactor();

        assertEquals(1010, wolf.getDominationFactor());
    }

    @Test
    void increaseImpetuosity() {
        Wolf wolf = new Wolf();
        wolf.setImpetuosity(1000);
        wolf.increaseImpetuosity();

        assertEquals(1010, wolf.getImpetuosity());
    }

    @Test
    void decreaseDominationFactor() {
        Wolf wolf = new Wolf();
        wolf.setDominationFactor(1000);
        wolf.decreaseDominationFactor();

        assertEquals(990, wolf.getDominationFactor());
    }

    @Test
    void getImpetuosity() {
        Wolf wolf = new Wolf();
        wolf.setImpetuosity(200);

        assertEquals(200, wolf.getImpetuosity());
    }

    @Test
    void getStrength() {
        Wolf wolf = new Wolf();
        wolf.setStrength(500);

        assertEquals(500, wolf.getStrength());
    }

    @Test
    void getRankName() {
        Wolf wolf = new Wolf();
        wolf.setRank(WolfRank.ALPHA);

        assertEquals(WolfRank.ALPHA.getName(), wolf.getRankName());
    }

    @Test
    void getRank() {
        Wolf wolf = new Wolf();
        wolf.setRank(WolfRank.ALPHA);

        assertEquals(WolfRank.ALPHA, wolf.getRank());
    }

    @Test
    void setRank() {
        Wolf wolf = new Wolf();
        wolf.setRank(WolfRank.ALPHA);

        assertEquals(WolfRank.ALPHA, wolf.getRank());
    }

    @Test
    void scream() {
    }

    @Test
    void copulate() {
        // Instantiate required values
        Wolf wolf = new Wolf();
        Mammal wolf2 = new Wolf();
        wolf.setSex(false);
        ((Wolf)wolf2).setSex(true);

        int turnNb = 4;
        wolf.childrenCreationTime = 2;
        wolf.setCopulationTurn(0);

        wolf.copulate(wolf2, turnNb);

        assertEquals(4, wolf.getCopulationTurn());
    }

    @Test
    void copulateAlreadyPregnant() {
        // Instantiate required values
        Wolf wolf = new Wolf();
        Mammal wolf2 = new Wolf();
        wolf.setSex(false);
        ((Wolf)wolf2).setSex(true);

        int turnNb = 4;
        wolf.childrenCreationTime = 2;
        wolf.setCopulationTurn(1);

        wolf.copulate(wolf2, turnNb);

        assertNotEquals(4, wolf.getCopulationTurn());
    }

    @Test
    void checkBirth() {
        // Instantiate required values
        Wolf wolf = new Wolf();
        int turnNb = 4;
        wolf.childrenCreationTime = 2;
        wolf.setCopulationTurn(2);
        wolf.setRank(WolfRank.ALPHA);

        // Check birth with an expected animal as return
        Wolf newAnimal = wolf.checkBirth(turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(wolf.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was reset
        assertEquals(0, wolf.getCopulationTurn());
    }

    @Test
    void checkBirthSolitary() {
        // Instantiate required values
        Wolf wolf = new Wolf();
        int turnNb = 4;
        wolf.childrenCreationTime = 2;
        wolf.setCopulationTurn(2);

        // Check birth with an expected animal as return
        Wolf newAnimal = wolf.checkBirth(turnNb);

        // Check that we successfully got an animal and not a null
        assertNotNull(newAnimal);
        assertEquals(wolf.getSpecieName(), newAnimal.getSpecieName());

        // Check that the copulation turn was reset
        assertEquals(0, wolf.getCopulationTurn());
    }

    @Test
    void checkBirthNotAlpha() {
        // Instantiate required values
        Wolf wolf = new Wolf();
        int turnNb = 4;
        wolf.childrenCreationTime = 2;
        wolf.setCopulationTurn(2);
        wolf.setPack(new WolfPack());
        wolf.setRank(WolfRank.CHI);

        // Check birth with an expected animal as return
        Wolf newAnimal = wolf.checkBirth(turnNb);
        System.out.println(newAnimal);
        // Check that we didn't got an animal
        assertNull(newAnimal);
    }

    @Test
    void checkBirthNotPregnantAnimal() {
        Wolf wolf = new Wolf();
        int turnNb = 4;
        wolf.childrenCreationTime = 2;
        wolf.setCopulationTurn(0);

        assertNull(wolf.checkBirth(turnNb));
    }

    @Test
    void wander() {
    }

    @Test
    void birth() {
        Wolf wolf = new Wolf();
        Wolf generatedWolf = wolf.birth();
        assertNotNull(generatedWolf);
        // Check with the specie name as they do have randomly generated attributes
        assertEquals(wolf.getSpecieName(), generatedWolf.getSpecieName());
        // Check that they are not the sames
        assertNotEquals(wolf, generatedWolf);
    }

}