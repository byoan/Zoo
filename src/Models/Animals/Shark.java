package Models.Animals;

import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.AnimalInterface;
import Models.Interfaces.Animal.MarineAnimal;
import Models.Interfaces.Animal.Oviparous;
import Views.View;

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
        this.weight = randomWeight(680000, 1100000);
        this.size = randomWeight(3, 6);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
        this.copulationTurn = 0;
        this.isHatched = true;
    }

    public Shark(int copulationTurn) {
        this.specieName = "Shark";
        this.sex = true;
        this.weight = randomWeight(680000, 1100000);
        this.size = randomWeight(3, 6);
        this.age = 1;
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
     * Getter for hatched status
     * @return Return hatched status
     */
    public boolean getIsHatched() {
        return this.isHatched;
    }

    /**
     * Allows to set the current state of hatching of our Animal instance
     */
    public void setHatched(boolean value) {
        this.isHatched = value;
    }

    /**
     * Checks if the current animal is hatched, using its creation turn and the required amount of time to hatch
     * @param turnNb The current turn number of the simulation
     */
    public boolean checkIfHatched(int turnNb) {
        if (this.isHatched) {
            return true;
        } else if (turnNb - this.getCopulationTurn() >= this.getChildrenCreationTime()) {
            View.displayMessage("A shark hatched !");
            this.setHatched(true);
        }
        return this.isHatched;
    }

    @Override
    public void scream() {
        View.displayMessage("A shark is screaming");
    }

    @Override
    public void swim() {
        View.displayMessage("A shark is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param shark The animal instance that represents the male
     */
    public <A extends AnimalInterface> Animal copulate(A shark, int turnNb) {
        // Same sex can't copulates
        if (shark.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (this.getSex() == false) {
                return new Shark(turnNb);
            } else {
                return null;
            }
        }
    }

    @Override
    public Shark lay() {
        return AnimalFactory.getInstance().createShark();
    }

    @Override
    public String toString() {
        return super.toString() + "       Already hatched: " + ((this.getIsHatched()) ? "Yes" : "No") + " \n    }\n";
    }
}
