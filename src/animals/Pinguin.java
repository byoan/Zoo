package animals;

import java.util.Random;

public class Pinguin extends Animal implements MarineAnimal, Oviparous, FlyingAnimal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Pinguin() {
        this.specieName = "Pinguin";
        this.sex = true;
        this.weight = 3;
        this.size = 0.05f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = 0;
        this.isHatched = false;
    }

    public Pinguin(int copulationTurn) {
        this.specieName = "Pinguin";
        this.sex = true;
        this.weight = 3;
        this.size = 0.5f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Pinguin(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = "Pinguin";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    /**
     * Allows to set the current state of hatching of our Animal instance
     */
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
        System.out.println("A pinguin is screaming");
    }

    @Override
    public void swim() {
        System.out.println("A pinguin is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param pinguin The animal instance that represents the male
     */
    public void copulate(Pinguin pinguin, int turnNb) {
        if (pinguin.getSex() == this.getSex()) {
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

    public Pinguin lay(int turnNb) {
        return new Pinguin(turnNb);
    }

    @Override
    public Pinguin lay() {
        return new Pinguin();
    }

    @Override
    public void fly() {
        System.out.println("A pinguin is flying");
    }

    @Override
    public String toString() {
        return "Pinguin {\n" +
                "  specieName='" + specieName + "', \n" +
                "  sex=" + sex + ", \n" +
                "  weight=" + weight + ", \n" +
                "  size=" + size + ", \n" +
                "  age=" + age + ", \n" +
                "  hungerIndicator=" + hungerIndicator + ", \n" +
                "  sleepIndicator=" + sleepIndicator + ", \n" +
                "  healthIndicator=" + healthIndicator + ", \n" +
                "  copulationTurn=" + copulationTurn + ", \n" +
                "  isHatched=" + isHatched + ", \n" +
                "  childrenCreationTime=" + childrenCreationTime + ", \n" +
                "  isInEnclosure=" + isInEnclosure + ", \n" +
                '}';
    }
}
