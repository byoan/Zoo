package animals;

import java.util.Random;

public class Wolf extends Animal implements Mammal, WanderAnimal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    public Wolf() {
        this.specieName = "Wolf";
        this.sex = this.getRandomBoolean();
        this.weight = 50;
        this.size = 82;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
    }

    public Wolf(boolean sex, float weight, float size, int age) {
        this.specieName = "Wolf";
        this.sex = sex;
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
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        System.out.println("I'm screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param wolf The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public void copulate(Wolf wolf, int turnNb) {
        if (wolf.getSex() == this.getSex()) {
            System.out.println("Same sex can't copulate");
        } else {
            if (this.getSex() == false) {
                if (this.getCopulationTurn() <= 0) {
                    this.setCopulationTurn(turnNb);
                } else {
                    // @TODO
                    // Throw exception
                    System.out.println("This animal is already pregnant");
                }
            } else {
                // @TODO
                // Throw exception
                System.out.println("Man can't be pregnant");
            }
        }
    }

    /**
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public void checkBirth(int turnNb) {
        if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime()) {
            this.birth();
        }
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
        this.setCopulationTurn(0);
        return new Wolf();
    }

    @Override
    public String toString() {
        return "Wolf{" +
                "specieName='" + specieName + '\'' +
                ", sex=" + sex +
                ", weight=" + weight +
                ", size=" + size +
                ", age=" + age +
                ", hungerIndicator=" + hungerIndicator +
                ", sleepIndicator=" + sleepIndicator +
                ", healthIndicator=" + healthIndicator +
                ", copulationTurn=" + copulationTurn +
                ", childrenCreationTime=" + childrenCreationTime +
                ", isInEnclosure=" + isInEnclosure +
                '}';
    }
}
