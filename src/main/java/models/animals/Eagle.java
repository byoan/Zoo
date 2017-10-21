package models.animals;

import models.factories.AnimalFactory;
import models.interfaces.animal.AnimalInterface;
import models.interfaces.animal.FlyingAnimal;
import models.interfaces.animal.Oviparous;
import views.Color;
import views.View;

public class Eagle extends Animal implements FlyingAnimal, Oviparous {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Eagle";

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Eagle() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(3000, 5000);
        this.size = this.randomSize(70, 84);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = 0;
        this.isHatched = true;
    }

    public Eagle(int copulationTurn) {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(3000, 5000);
        this.size = this.randomSize(70, 84);
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
        this.copulationTurn = copulationTurn;
        this.isHatched = false;
    }

    public Eagle(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = SPECIE_NAME;
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
        if (this.getIsHatched()) {
            return true;
        } else if (turnNb - this.getCopulationTurn() == this.getChildrenCreationTime()) {
            View.displaySuccessMessage("An eagle hatched !");
            this.setHatched(true);
        }
        return this.getIsHatched();
    }

    @Override
    public void scream() {
        View.displayAnimalActionMessage("An eagle is screaming");
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param eagle The animal instance that represents the male
     */
    public <A extends AnimalInterface> Animal copulate(A eagle, int turnNb) {
        // Same sex can't copulate
        if (eagle.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (!this.getSex()) {
                return AnimalFactory.getInstance().createEagle(turnNb);
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
        View.displayAnimalActionMessage("An eagle is flying");
    }

    @Override
    public String toString() {
        return super.toString()  + "       Already hatched: " + Color.BLUE + ((this.getIsHatched()) ? "Yes" : "No") + Color.WHITE + " \n    }";
    }
}
