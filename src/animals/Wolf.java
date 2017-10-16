package animals;

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

    public Wolf() {
        this.specieName = "Wolf";
        this.sex = this.getRandomBoolean();
        this.strength = this.strength(100);
        this.weight = 50;
        this.size = 82;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
    }

    public Wolf(boolean sex, int strength, float weight, float size, int age) {
        this.specieName = "Wolf";
        this.sex = sex;
        this.strength = strength;
        this.weight = weight;
        this.size = size;
        this.age = age;
        //Default ones
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
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
            System.out.println("Oh my god i'm hurt !!");
        } else if (healthIndicator == 100){
            System.out.println("Oh yeah i'm good !!");
        }
    }

    public void heardScream(){
        if (this.sleepIndicator == false && this.healthIndicator == 100){
            System.out.println("I can heard some wolf scream");
        } else if (this.sleepIndicator == true ){
            System.out.println("I'm sleeping I can't heard some wolf scream");
        } else if (this.healthIndicator != 100){
            System.out.println("I'm hurt I can't heard some wolf scream");
        }else{
            System.out.println("I'm sleeping and hurt so how the fuck can I heard some wolf scream ?");
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
        System.out.println("I'm wandering...");
    }

    /**
     * Represents the birth of the children
     * @return
     */
    @Override
    public Wolf birth() {
        return new Wolf();
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
