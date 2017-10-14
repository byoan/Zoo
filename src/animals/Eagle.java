package animals;

import java.util.Random;

public class Eagle extends Animal implements FlyingAnimal, Oviparous {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Eagle() {
        this.specieName = "Eagle";
        this.sex = this.getRandomBoolean();
        this.weight = 30;
        this.size = 1;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = 0;
        this.isHatched = false;
    }

    public Eagle(int copulationTurn) {
        this.specieName = "Eagle";
        this.sex = this.getRandomBoolean();
        this.weight = 30;
        this.size = 1;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Eagle(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = "Eagle";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public void setHatched(boolean value) {
        this.isHatched = value;
    }

    /**
     * Checks if the current animal is hatched, using its creation turn and the required amount of time to hatch
     * @param turnNb
     */
    public boolean checkIfHatched(int turnNb) {
        if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime() || this.getCopulationTurn() == 0) {
            this.setHatched(true);
        }
        return this.isHatched;
    }

    @Override
    public void scream() {
        System.out.println("An eagle is screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param eagle The animal instance that represents the male
     */
    public void copulate(Eagle eagle, int turnNb) {
        if (eagle.getSex() == this.getSex()) {
            System.out.println("Same sex can't copulate");
        } else {
            if (this.getSex() == false) {
                this.lay(turnNb);
            } else {
                // @TODO
                // Throw exception
                System.out.println("Man can't be pregnant");
            }
        }
    }

    public Eagle lay(int turnNb) {
        return new Eagle(turnNb);
    }

    @Override
    public Eagle lay() {
        return new Eagle();
    }

    @Override
    public void fly() {
        System.out.println("An eagle is flying");
    }

    @Override
    public String toString() {
        return "Eagle{" +
                "specieName='" + specieName + '\'' +
                ", sex=" + sex +
                ", weight=" + weight +
                ", size=" + size +
                ", age=" + age +
                ", hungerIndicator=" + hungerIndicator +
                ", sleepIndicator=" + sleepIndicator +
                ", healthIndicator=" + healthIndicator +
                ", copulationTurn=" + copulationTurn +
                ", isHatched=" + isHatched +
                ", childrenCreationTime=" + childrenCreationTime +
                ", isInEnclosure=" + isInEnclosure +
                '}';
    }
}
