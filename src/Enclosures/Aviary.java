package Enclosures;

import animals.FlyingAnimal;

import java.util.List;

public class Aviary<A extends FlyingAnimal> extends Enclosure {

    private int height;

    /**
     * Represents the roof current cleanliness
     * 0 = bad, 1 = correct, 2 = good
     */
    private int roofState;

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

}
