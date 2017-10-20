package Models.Animals;

import Models.Factories.AnimalFactory;
import Models.Interfaces.Animal.AnimalInterface;
import Models.Interfaces.Animal.MarineAnimal;
import Models.Interfaces.Animal.Oviparous;
import Views.View;

public class Fish extends Animal implements MarineAnimal, Oviparous {

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Fish() {
        this.specieName = "Fish";
        this.sex = this.getRandomBoolean();;
        this.weight = this.randomWeight(100, 2500);
        this.size = this.randomSize(10, 15);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = 0;
        this.isHatched = true;
    }

    public Fish(int copulationTurn) {
        this.specieName = "Fish";
        this.sex = this.getRandomBoolean();;
        this.weight = this.randomWeight(100, 2500);
        this.size = this.randomSize(10, 15);
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Fish(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = "Fish";
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
        if (this.getIsHatched()) {
            return true;
        } else if (turnNb - this.getCopulationTurn() == this.getChildrenCreationTime()) {
            View.displayMessage("A fish hatched !");
            this.setHatched(true);
        }
        return this.getIsHatched();
    }

    @Override
    public void scream() {
        View.displayMessage("A fish is screaming blblblblbl");
    }

    @Override
    public void swim() {
        View.displayMessage("A fish is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given Animal (which must be the same)
     * @param fish The animal instance that represents the male
     */
    public <A extends AnimalInterface> Animal copulate(A fish, int turnNb) {
        // Same sex can't copulate
        if (fish.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (this.getSex() == false) {
                return AnimalFactory.getInstance().createFish(turnNb);
            } else {
                return null;
            }
        }
    }

    @Override
    public Fish lay() {
        return AnimalFactory.getInstance().createFish();
    }

    @Override
    public String toString() {
        return super.toString() + "       Already hatched: \033[34m" +((this.getIsHatched()) ? "Yes" : "No")  + "\033[37m \n    }";
    }
}
