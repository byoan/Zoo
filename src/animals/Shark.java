package animals;

import java.util.Random;

public class Shark extends Animal implements MarineAnimal, Oviparous {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Shark() {
        this.specieName = "Shark";
        this.sex = true;
        this.weight = 907;
        this.size = 3.5f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
        this.copulationTurn = 0;
        this.isHatched = false;
    }

    public Shark(int copulationTurn) {
        this.specieName = "Shark";
        this.sex = true;
        this.weight = 907;
        this.size = 3.5f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Shark(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = "Shark";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
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
        System.out.println("A shark is screaming");
    }

    @Override
    public void swim() {
        System.out.println("A shark is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param shark The animal instance that represents the male
     */
    public void copulate(Shark shark, int turnNb) {
        if (shark.getSex() == this.getSex()) {
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

    public Shark lay(int turnNb) {
        return new Shark(turnNb);
    }

    @Override
    public Shark lay() {
        return new Shark();
    }

    @Override
    public String toString() {
        return "Shark {\n" +
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
