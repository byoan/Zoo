package animals;

public class Whale extends Animal implements MarineAnimal, Mammal {

    /**
     * Defines the turn number at which the animal copulated, allowing us to calculate the difference
     * with the current turn number to compare with the gestation time
     */
    private int copulationTurn;

    public Whale() {
        this.specieName = "Whale";
        this.sex = this.getRandomBoolean();
        this.weight = 140000;
        this.size = 25;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }

    public Whale(boolean sex, float weight, float size, int age) {
        this.specieName = "Whale";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        //Default ones
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }

    /**
     * Animal generic method to scream
     */
    @Override
    public void scream() {
        System.out.println("I'm screaming");
    }

    @Override
    public void swim() {
        System.out.println("A whale is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param whale The animal instance that represents the male
     * @param turnNb The current turn number, to mark the beginning of the pregnancy
     */
    public <A extends Mammal> void copulate(A whale, int turnNb) {
        // Same sex can't copulate
        if (whale.getSex() == this.getSex()) {

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
     * Checks if the pregnancy is at term
     * @param turnNb the current turn number
     */
    public Whale checkBirth(int turnNb) {
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
    public Whale birth() {
        return new Whale();
    }

    @Override
    public String toString() {
        return "Whale {\n" +
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
                '}';
    }
}
