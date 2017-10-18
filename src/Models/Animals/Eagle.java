package Models.Animals;

import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.AnimalInterface;
import Models.Interfaces.Animal.FlyingAnimal;
import Models.Interfaces.Animal.Oviparous;
import Views.View;

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
        this.isHatched = true;
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

    /**
     * Getter for hatched status
     * @return Return hatched status
     */
    public boolean getIsHatched() {
        return this.isHatched;
    }

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
            View.displayMessage("An eagle hatched !");
            this.setHatched(true);
        }
        return this.isHatched;
    }

    @Override
    public void scream() {
        View.displayMessage("An eagle is screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param eagle The animal instance that represents the male
     */
    public <A extends AnimalInterface> Animal copulate(A eagle, int turnNb) {
        // Same sex can't copulate
        if (eagle.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (this.getSex() == false) {
                return new Eagle(turnNb);
            } else {
                return null;
            }
        }
    }

    @Override
    public Eagle lay() {
        return AnimalFactory.getInstance().createEagle();
    }

    @Override
    public void fly() {
        View.displayMessage("An eagle is flying");
    }

    @Override
    public String toString() {
        return super.toString()  + "       Already hatched: " +((this.getIsHatched()) ? "Yes" : "No")  + " \n    }\n";
    }
}
