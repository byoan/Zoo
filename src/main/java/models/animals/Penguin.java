package models.animals;

import models.factories.AnimalFactory;
import models.interfaces.animal.AnimalInterface;
import models.interfaces.animal.FlyingAnimal;
import models.interfaces.animal.MarineAnimal;
import models.interfaces.animal.Oviparous;
import views.Color;
import views.View;

public class Penguin extends Animal implements MarineAnimal, Oviparous, FlyingAnimal {

    /**
     * Represents the specie name of the Animal, which is basically it's class name in a String
     */
    private static final String SPECIE_NAME = "Penguin";

    /**
     * Whether the current animal is hatched
     */
    private boolean isHatched;

    public Penguin() {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(500, 700);
        this.size = this.randomSize(37, 39);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = 0;
        this.childrenCreationTime = 30;
        this.isHatched = true;
    }

    public Penguin(int copulationTurn) {
        this.specieName = SPECIE_NAME;
        this.sex = this.getRandomBoolean();
        this.weight = this.randomWeight(500, 700);
        this.size = this.randomSize(37, 39);
        this.age = 1;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.copulationTurn = copulationTurn;
        this.childrenCreationTime = 30;
        this.isHatched = false;
    }

    public Penguin(boolean sex, float weight, float size, int age, int copulationTurn) {
        this.specieName = SPECIE_NAME;
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
     * Getter for hatched status
     * @return Return hatched status
     */
    public boolean getIsHatched() {
        return this.isHatched;
    }

    /**
     * Allows to set the current state of hatching of our animal instance
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
            View.displaySuccessMessage("A penguin hatched !");
            this.setHatched(true);
        }
        return this.getIsHatched();
    }

    @Override
    public void scream() {
        View.displayAnimalActionMessage("A penguin is screaming");
    }

    @Override
    public void swim() {
        View.displayAnimalActionMessage("A penguin is swimming");
    }

    /**
     * Performs a copulation between the current animal instance and the given animal (which must be the same)
     * @param penguin The animal instance that represents the male
     */
    public <A extends AnimalInterface> Animal copulate(A penguin, int turnNb) {
        // Same sex can't copulate
        if (penguin.getSex() == this.getSex()) {
            return null;
        } else {
            // Man can't be pregnant
            if (!this.getSex()) {
                return AnimalFactory.getInstance().createPenguin(turnNb);
            } else {
                return null;
            }
        }
    }

    @Override
    public Penguin lay() {
        return AnimalFactory.getInstance().createPenguin();
    }

    @Override
    public void fly() {
        View.displayAnimalActionMessage("A penguin is flying");
    }

    @Override
    public String toString() {
        return super.toString()  + "       Already hatched: " + Color.BLUE + ((this.getIsHatched()) ? "Yes" : "No") + Color.WHITE + " \n    }";
    }
}
