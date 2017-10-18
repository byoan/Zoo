package Models.Animals.Packs;

import Models.Animals.Wolf;
import Models.Enums.WolfRank;
import Models.Enclosures.Enclosure;

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
    public double getPackAverageStrength() {
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
            wolf.setRank(WolfRank.Alpha);
            wolf.setStrength(101);
        }
        if (wolf.getRank() == null) {
            this.generateWolfRank(wolf);
        }
        this.getWolfList().add(wolf);
        wolf.setPack(this);
        wolf.generateWolfLevel();
    }

    /**
     * Allows to remove a wolf from the pack
     * @param wolf The wolf to remove
     */
    public void remove (Wolf wolf) {
        if (this.getWolfList().contains(wolf)) {
            // Delete pack related attributes
            wolf.setPack(null);
            wolf.setRank(null);
            wolf.setLevel(0);
            this.getWolfList().remove(wolf);
        }
    }

    /**
     * Allows to sort the pack by the Wolf rank in its pack
     */
    public ArrayList<Wolf> insertionSort(ArrayList<Wolf> wolfPack) {
        int i, j;
        int currentWolfRank;

        for (i = 1; i < wolfPack.size(); i++) {
            // Retrieve the current wolf level
            currentWolfRank = wolfPack.get(i).getRank().getId();
            j = i - 1;
            // Second loop used for comparison between the previous entry and the wolf with the j position in the list
            while (j >= 0) {
                // If it is superior, then the pair is correctly ordered (between themselves)
                if (currentWolfRank > wolfPack.get(j).getRank().getId()) {
                    break;
                }
                // If it's not, then we have to invert them
                Wolf wolf = wolfPack.get(j + 1);
                wolfPack.set(j + 1, wolfPack.get(j));
                wolfPack.set(j, wolf);

                // Decrease so we can compare our currentWolfLevel with the value of the "next previous" wolf
                --j;
            }
        }
        return wolfPack;
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
                this.add(wolf);
            }
        }
    }

    /**
     * Allows to define at least 1 omega wolf in a pack
     * @param enclosure The enclosure in which we must check if there is any Omega, and in which we'll define one if not
     */
    public void setAtLeastOneOmegaInPack(Enclosure<Wolf> enclosure) {
        for (Wolf wolf : enclosure.getAnimals()) {
            if (wolf.getRankName() == "Alpha") {
                continue;
            } else {
                if (wolf.getRankName() == "Omega") {
                    break;
                } else {
                    wolf.setRank(WolfRank.Omega);
                    break;
                }
            }
        }
    }

    /**
     * Allows to randomly generate a rank between Beta and Omega
     * @param wolf The wolf which will get the rank
     */
    public void generateWolfRank(Wolf wolf) {
        // TODO Retry if it falls on Alpha and there is already a couple
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int rank = random.nextInt(2, 24);
        WolfRank[] rankList = WolfRank.values();
        wolf.setRank(rankList[rank - 1]);
    }

    /**
     * Getter for the alpha male of the pack
     * @return The alpha male
     */
    public Wolf getAlphaMale() {
        for (Wolf wolf : this.getWolfList()) {
            if (wolf.getSex() && wolf.getRank().getId() == 1) {
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
            if (wolf.getSex() == false && wolf.getRank().getId() == 1) {
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
