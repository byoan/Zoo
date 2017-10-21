package models.animals;

import models.animals.packs.WolfPack;
import models.enums.WolfRank;
import models.exceptions.animals.AnimalAlreadyPregnantException;
import models.factories.AnimalFactory;
import models.interfaces.animal.Mammal;
import models.interfaces.animal.WanderAnimal;
import views.Color;
import views.View;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal implements Mammal, WanderAnimal {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Wolf";

    /**
     * Represents the pack that contains this wolf
     */
    private WolfPack pack;

    /**
     * Represents the rank of the wolf within its pack (if any)
     */
    private WolfRank rank;

    /**
     * Represents the impetuosity level of the wolf, on a scale from 1 to 100
     */
    private int impetuosity;

    /**
     * Represents the strength level of the wolf, on a scale from 1 to 100
     */
    private int strength;

    /**
     * Represents the domination factor of the wolf, starting at 100
     * Will increase or decrease of 1 at each tried domination/taken domination
     */
    private int dominationFactor;

    /**
     * Represents the wolf level, calculated from its strength, domination factor and impetuosity
     */
    private int level;

    public Wolf() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.strength = this.generateStrength();
        this.weight = this.randomWeight(16, 50);
        this.size = this.randomSize(66, 81);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
        this.impetuosity = this.generateImpetuosity();
        this.dominationFactor = 100;
        this.pack = null;
        this.level = 0;
    }

    public Wolf(boolean sex, float weight, float size, int age) {
        this.specieName = SPECIE_NAME;
        this.sex = sex;
        this.strength = this.generateStrength();
        this.weight = weight;
        this.size = size;
        this.age = age;
        //Default ones
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
        this.impetuosity = this.generateImpetuosity();
        this.dominationFactor = 100;
        this.pack = null;
        this.level = 0;
    }

    /**
     * Getter for the pack of the Wolf
     * @return The pack to which the wolf belongs
     */
    public WolfPack getPack() {
        return this.pack;
    }

    /**
     * Setter for the pack of the wolf
     * @param pack The new pack of the wolf
     */
    public void setPack(WolfPack pack) {
        this.pack = pack;
    }

    /**
     * Setter for the strength of the wolf
     * Used for the Alpha couple, which will always have a better strength that the rest of the pack
     * @param strength
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Allows to generate the wolf level attribute, based on its impetuosity, strength and domination level
        return ThreadLocalRandom.current().nextInt(1, 101);
     */
    public void generateWolfLevel() {
        int bonusFromAge = 0;
        switch (this.getAge()) {
            case 1:
                bonusFromAge = 25;
                break;
            case 2:
                bonusFromAge = 50;
                break;
            case 3:
                bonusFromAge = 10;
                break;
            default:
                break;
        }

        // If male
        if (this.getSex()) {
            // 20% bonus as it is a male
            this.setLevel(((this.getStrength() * this.getDominationFactor() * bonusFromAge) * 120 / 100) / (this.getRank().getId() * 100));
        } else {
            this.setLevel((this.getStrength() * this.getDominationFactor() * bonusFromAge) / (this.getRank().getId() * 100));
        }
    }

    /**
     * Getter for the wolf level attribute, calculated from its impetuosity, strength and domination level
     * @return int
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Setter for the level of the wolf
     * @param level The new level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Allows the current wolf to attempt a domination on the given wolf
     */
    public void attemptDomination(Wolf target) {
        if (this.getPack() != null || target.getPack() != null) {
            // Be careful, the lower the id of the rank is, the better the wolf rank is within the pack
            if (this.getRank().getId() < target.getRank().getId() && target.getRank().getId() != 24) {
                return;
            } else {
                // We must always be able to fight an Omega and win against him without swapping ranks
                if (target.getRank().getId() == 24) {
                    this.increaseDominationFactor();
                    // Else, we use the level to compare wolf's chances
                } else if (this.getLevel() > target.getLevel()) {
                    this.increaseDominationFactor();
                    target.decreaseDominationFactor();
                    // In case we attack the alpha male, and we win, we must remove the alpha from the pack, and replace it with the winner
                    if (target.getRank().getId() == 1) {
                        target.getPack().remove(target);
                        this.setRank(WolfRank.Alpha);
                    } else {
                        // If it is not the alpha, we simply swap the ranks of the 2 fighters
                        this.swapRanks(target);
                    }
                } else {
                    // Failed attempt, the target looses domination though
                    target.decreaseDominationFactor();
                    target.increaseImpetuosity();
                }
            }
        }
    }

    /**
     * Allows to swap the ranks in a pack between the current Wolf instance and the given target
     */
    public void swapRanks(Wolf target) {
        WolfRank gainedRank = target.getRank();
        target.setRank(this.getRank());
        this.setRank(gainedRank);
    }

    /**
     * Getter for the domination factor of the wolf
     * @return The domination factor of the wolf
     */
    public int getDominationFactor() {
        return this.dominationFactor;
    }

    /**
     * Setter for the domination factor of the wolf
     * @param dominationFactor The new domination factor of the wolf
     */
    public void setDominationFactor(int dominationFactor) {
        this.dominationFactor = dominationFactor;
    }

    /**
     * Setter for the impetuosity level of the wolf
     * @param impetuosity The new level of impetuosity
     */
    public void setImpetuosity(int impetuosity) {
        this.impetuosity = impetuosity;
    }

    /**
     * Allows to increase the domination factor of the wolf
     * Used when an attempted domination on another wolf succeeds
     */
    public void increaseDominationFactor() {
        this.setDominationFactor(this.getDominationFactor() + 10);
    }

    /**
     * Allows to increase the impetuosity factor of the wolf
     * Used when an attempted domination on another wolf fails, and the target becomes more aggressive
     */
    public void increaseImpetuosity() {
        this.setImpetuosity(this.getImpetuosity() + 10);
    }

    /**
     * Allows to increase the domination factor of the wolf
     * Used when an attempted domination on another wolf succeeds
     */
    public void decreaseDominationFactor() {
        this.setDominationFactor(this.getDominationFactor() - 10);
    }

    /**
     * Getter for the impetuosity level of the wolf
     * @return The impetuosity level of the wolf
     */
    public int getImpetuosity() {
        return this.impetuosity;
    }

    /**
     * Getter for the strength of the wolf
     * @return The strength level of the wolf
     */
    public int getStrength() {
        return this.strength;
    }

    /**
     * Getter for the rank of the wolf within its pack (if any)
     * @return The wolf rank
     */
    public String getRankName() {
        return this.rank.getName();
    }

    /**
     * Raw getter for the Enum which represents the wolf rank
     * @return The wolf rank
     */
    public WolfRank getRank() {
        return this.rank;
    }

    /**
     * Setter for the rank of the wolf within the pack
     * @param rank The new wolf rank
     */
    public void setRank(WolfRank rank) {
        this.rank = rank;
    }

    /**
     * animal generic method to scream
     */
    @Override
    public void scream() {
        if (healthIndicator < 50 ){
            View.displayAnimalActionMessage("Oh my god i'm hurt !!");
        } else if (healthIndicator == 100){
            View.displayAnimalActionMessage("Oh yeah i'm good !!");
        }
    }

    /**
     * TODO
     */
    public void heardScream(){
        if (!this.isSleeping() && this.healthIndicator == 100){
            View.displayAnimalActionMessage("I can heard some wolf scream");
        } else if (this.isSleeping()){
            View.displayAnimalActionMessage("I'm sleeping I can't heard some wolf scream");
        } else if (this.getHealth() != 100){
            View.displayAnimalActionMessage("I'm hurt I can't heard some wolf scream");
        }else{
            View.displayAnimalActionMessage("I'm sleeping and hurt so how the fuck can I heard some wolf scream ?");
        }
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param wolf The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public <A extends Mammal> void copulate(A wolf, int turnNb) {
        // Same sex can't copulate
        if (wolf.getSex() != this.getSex()) {
            try {
                if (this.getCopulationTurn() == 0) {
                    this.setCopulationTurn(turnNb);
                } else {
                    throw new AnimalAlreadyPregnantException(this);
                }
            } catch (AnimalAlreadyPregnantException e) {
                View.displayErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Strength generator between 1 - 100
     * @return A randomly generated number
     */
    private int generateStrength()
    {
        return ThreadLocalRandom.current().nextInt(1, 101);
    }

    /**
     * Impetuosity generator between 1 - 100
     * @return A randomly generated number
     */
    private int generateImpetuosity()
    {
        return ThreadLocalRandom.current().nextInt(1, 101);
    }

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public Wolf checkBirth(int turnNb) {
        if (this.getCopulationTurn() == 0) {
            return null;
        } else if (turnNb - this.getCopulationTurn() == this.getChildrenCreationTime()) {
            this.setCopulationTurn(0);
            return this.birth();
        }
        return null;
    }

    /**
     * Generic method who say if animal wandering or not
     */
    public void wander() {
        View.displayAnimalActionMessage("I'm wandering...");
    }

    /**
     * Represents the birth of the children
     * @return A new Wolf instance (through the AnimalFactory), which represents the newly born animal
     */
    @Override
    public Wolf birth() {
        return AnimalFactory.getInstance().createWolf();
    }

    @Override
    public String toString() {
        return  super.toString() +
                "\n" + "       Membership status: " + Color.BLUE + ((this.getRank() == null) ? "Solitary" : "In a pack") + Color.WHITE + "  |  " +
                "Rank in the pack: " + Color.BLUE + ((this.getRank() == null) ? "none" : this.getRank()) + Color.WHITE + "  |  " +
                "Level: " + Color.BLUE + this.getLevel() + Color.WHITE + "\n" +
                "       Domination factor: " + Color.BLUE + this.getDominationFactor() + Color.WHITE + "  |  " +
                "Impetuosity: " + Color.BLUE + this.getImpetuosity() + "%" + Color.WHITE + "  |  " +
                "Strength: " + Color.BLUE + this.getStrength() + Color.WHITE + "\n" +"   } \n"
                ;
    }
}
