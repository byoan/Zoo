package Models.Animals;

import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.Mammal;
import Models.Interfaces.Animal.WanderAnimal;
import Views.View;

import java.util.concurrent.ThreadLocalRandom;

public class Wolf extends Animal implements Mammal, WanderAnimal {

    /**
     * Represents the rank of the wolf within its pack (if any)
     */
    private String rank;

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    /**
     * Represents the impetuosity level of the wolf, on a scale from 0 to 100
     */
    private int impetuosity;

    /**
     * Represents the strength level of the wolf, on a scale from 0 to 100
     */
    private int strength;

    /**
     * Represents the domination factor of the wolf, starting at 100
     * Will increase or decrease of 1 at each tried domination/taken domination
     */
    private int dominationFactor;

    // TODO : level of the wolf
//    private

    public Wolf() {
        this.specieName = "Wolf";
        this.sex = this.getRandomBoolean();
        this.strength = this.generateStrength();
        this.weight = 50;
        this.size = 82;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
        this.impetuosity = this.generateImpetuosity();
    }

    public Wolf(boolean sex, float weight, float size, int age) {
        this.specieName = "Wolf";
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
     * Allows to increase the domination factor of the wolf
     * Used when an attempted domination on another wolf succeeds
     */
    public void increaseDominationFactor() {
        this.setDominationFactor(this.getDominationFactor() + 1);
    }

    /**
     * Allows to increase the domination factor of the wolf
     * Used when an attempted domination on another wolf succeeds
     */
    public void decreaseDominationFactor() {
        this.setDominationFactor(this.getDominationFactor() - 1);
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
    public String getRank() {
        return this.rank;
    }

    /**
     * Setter for the rank of the wolf within the pack
     * @param rank The new wolf rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        if (healthIndicator < 50 ){
            View.displayMessage("Oh my god i'm hurt !!");
        } else if (healthIndicator == 100){
            View.displayMessage("Oh yeah i'm good !!");
        }
    }

    /**
     * TODO
     */
    public void heardScream(){
        if (this.sleepIndicator == false && this.healthIndicator == 100){
            View.displayMessage("I can heard some wolf scream");
        } else if (this.sleepIndicator == true ){
            View.displayMessage("I'm sleeping I can't heard some wolf scream");
        } else if (this.healthIndicator != 100){
            View.displayMessage("I'm hurt I can't heard some wolf scream");
        }else{
            View.displayMessage("I'm sleeping and hurt so how the fuck can I heard some wolf scream ?");
        }
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param wolf The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public <A extends Mammal> void copulate(A wolf, int turnNb) {
        // Same sex can't copulate
        if (wolf.getSex() == this.getSex()) {

        } else {
            if (this.getSex() == false) {
                if (this.getCopulationTurn() == 0) {
                    this.setCopulationTurn(turnNb);
                } else {
                    // @TODO
                    // Throw exception This animal is already pregnant
                }
            } else {
                // @TODO
                // Throw exception Man can't be pregnant;
            }
        }
    }

    /**
     * Strength generator between 0 - 100
     * @return A randomly generated number
     */
    private int generateStrength()
    {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

    /**
     * Impetuosity generator between 0 - 100
     * @return A randomly generated number
     */
    private int generateImpetuosity()
    {
        return ThreadLocalRandom.current().nextInt(0, 100);
    }

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public Wolf checkBirth(int turnNb) {
        if (this.getCopulationTurn() == 0) {
            return null;
        } else if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime()) {
            this.setCopulationTurn(0);
            return this.birth();
        }
        return null;
    }

    /**
     * Generic method who say if animal wandering or not
     */
    public void wander() {
        View.displayMessage("I'm wandering...");
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
        return "Wolf {\n" +
                "  specieName='" + specieName + "', \n" +
                "  sex=" + sex + ", \n" +
                "  weight=" + weight + ", \n" +
                "  size=" + size + ", \n" +
                "  age=" + age + ", \n" +
                "  hungerIndicator=" + hungerIndicator + ", \n" +
                "  sleepIndicator=" + sleepIndicator + ", \n" +
                "  healthIndicator=" + healthIndicator + ", \n" +
                "  copulationTurn=" + copulationTurn + ", \n" +
                "  childrenCreationTime=" + childrenCreationTime + ", \n" +
                "  isInEnclosure=" + isInEnclosure + ", \n" +
                "  isInPack=" + ((this.getRank() == null) ? "Solitary" : "In a pack") + ", \n" +
                "  rankInPack=" + ((this.getRank() == null) ? "none" : this.getRank()) + ", \n" +
                '}';
    }
}
