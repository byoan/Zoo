package models.animals;

import models.factories.AnimalFactory;
import models.interfaces.animal.AnimalInterface;
import models.interfaces.animal.MarineAnimal;
import models.interfaces.animal.Oviparous;
import views.Color;
import views.View;

public class Fish extends Animal implements MarineAnimal, Oviparous {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Fish";

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Fish() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
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
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
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

    /**
     * Setter for the isHatched attribute, which represents whether or not the Animal has hatched or is still in an egg
     * @param value The new isHatched value to assign
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
            View.displaySuccessMessage(Color.BOLD + "A fish hatched !\n" + Color.DEFAULT);
            this.setHatched(true);
        }
        return this.getIsHatched();
    }

    @Override
    public void scream() {
        View.displayAnimalActionMessage("A fish is screaming blblblblbl");
    }

    @Override
    public void swim() {
        View.displayAnimalActionMessage("A fish is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param fish The animal instance that represents the male
     */
    @Override
    public <A extends AnimalInterface> Animal copulate(A fish, int turnNb) {
        // Same sex can't copulate
        if (fish.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (!this.getSex()) {
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
        return super.toString()  + "       Already hatched: " + Color.BLUE + ((this.getIsHatched()) ? "Yes" : "No") + Color.DEFAULT + "     }\n";
    }
}
