package Enclosures;

import animals.FlyingAnimal;

public class Aviary<A extends FlyingAnimal> extends Enclosure {

    /**
     * Represents the height of the aviary
     */
    private int height;

    /**
     * Represents the roof current cleanliness
     * 0 = bad, 1 = correct, 2 = good
     */
    private int roofState;

    public Aviary(String name, int surface, int maxAnimals, int height, int roofState) {
        super(name, surface, maxAnimals);
        this.height = height;
        this.roofState = roofState;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRoofState() {
        return this.roofState;
    }

    public void setRoofState(int roofState) {
        this.roofState = roofState;
    }

    public void cleanEnclosure() {
        super.cleanEnclosure();
        if (this.getRoofState() <= 1) {
            this.setRoofState(2);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Aviary{" +
                "height=" + height +
                ", roofState=" + roofState +
                '}';
    }
}
