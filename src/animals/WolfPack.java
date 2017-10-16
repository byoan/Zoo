package animals;

import Core.Enums.WolfRank;
import Enclosures.Enclosure;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class WolfPack {

    /**
     * Represents the wolves that compose the pack
     */
    ArrayList<Wolf> wolfList = new ArrayList<Wolf>();

    /**
     * Getter for the average strength of the pack
     * @return The average strength
     */
    public double getPackStrengthAverage() {
        double average = 0;
        for (Wolf wolf : this.getWolfList()) {
            average += (double)wolf.getStrength();
        }
        return average / this.getWolfList().size();
    }

    /**
     * Getter for the wolves in the pack
     * @return The wolves list composing the pack
     */
    public ArrayList<Wolf> getWolfList() {
        return wolfList;
    }

    /**
     * Setter for the wolves list of the pack
     * @param wolfList The new wolves list
     */
    public void setWolfList(ArrayList<Wolf> wolfList) {
        this.wolfList = wolfList;
    }

    /**
     * Allows to add a wolf to the pack
     * @param wolf The wolf to be added
     */
    public void add (Wolf wolf) {
        if (this.getWolfList().size() == 0) {
            wolf.setRank("Alpha");
        }
        if (wolf.getRank() == null) {
            this.generateWolfRank(wolf);
        }
        this.getWolfList().add(wolf);
    }

    /**
     * Allows to add all the wolves of an existing enclosure to a pack
     * @param enclosure The targeted enclosure
     */
    public void addAllWolvesToPack(Enclosure<Wolf> enclosure) {
        for (Wolf wolf : enclosure.getAnimals()) {
            // Check that it is not already in the pack
            if (wolf.getRank() == null) {
                this.generateWolfRank(wolf);
                this.getWolfList().add(wolf);
            }
        }
    }

    /**
     * Allows to define at least 1 omega wolf in a pack
     * @param enclosure
     */
    public void setAtLeastOneOmegaInPack(Enclosure<Wolf> enclosure) {
        for (Wolf wolf : enclosure.getAnimals()) {
            if (wolf.getRank() == "Alpha") {
                continue;
            } else {
                if (wolf.getRank() == "Omega") {
                    break;
                } else {
                    wolf.setRank("Omega");
                    break;
                }
            }
        }
    }

    /**
     * Allows to randomly generate a rank between Beta and Omega
     * @param wolf The wolf which will get the rank
     * @return
     */
    public void generateWolfRank(Wolf wolf) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rank = random.nextInt(2, 24);
        WolfRank[] rankList = WolfRank.values();
        wolf.setRank(rankList[rank - 1].toString());
    }

    /**
     * Getter for the alpha male of the pack
     * @return The alpha male
     */
    public Wolf getAlphaMale() {
        for (Wolf wolf : this.getWolfList()) {
            if (wolf.getSex() && wolf.getRank() == "Alpha") {
                return wolf;
            }
        }
        return null;
    }

    /**
     * Getter for the alpha female of the pack
     * @return The alpha female
     */
    public Wolf getAlphaFemale() {
        for (Wolf wolf : this.getWolfList()) {
            if (wolf.getSex() == false && wolf.getRank() == "Alpha") {
                return wolf;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Pack {\n" +
                " animals='" + this.getWolfList() + "', \n" +
                ((this.getAlphaMale() != null) ? " alpha male='" + this.getAlphaMale() + "', \n" : " No alpha male") +
                ((this.getAlphaFemale() != null) ? " alpha female='" + this.getAlphaFemale() + "', \n" : " No alpha female") +
                "}";
    }

}
