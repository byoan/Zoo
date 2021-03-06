package models.animals.packs;

import models.animals.Wolf;
import models.enums.WolfRank;
import models.enclosures.Enclosure;
import views.Color;
import views.View;

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
        if (this.getWolfList().isEmpty()) {
            wolf.setRank(WolfRank.ALPHA);
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
            // In case we are trying to remove the alpha male
            if (wolf.getRank().getId() == 1) {
                View.displayInformationMessage("Trying to find a new Alpha as the previous one left ...");
                this.designateNewAlphaMaleBeforeDeletion(wolf);
            } else {
                this.removeWolfPackAttributes(wolf);
                this.getWolfList().remove(wolf);
            }
        }
    }

    /**
     * Allows to reset the wolf's attributes that are related to a pack
     * @param wolf The wolf that needs to be taken care of
     */
    private void removeWolfPackAttributes(Wolf wolf) {
        wolf.setPack(null);
        wolf.setRank(null);
        wolf.setLevel(0);
    }

    /**
     * Allows to sort the pack by the Wolf rank in its pack
     * @param wolfPack The WolfPack to sort by insertion
     * @return The sorted WolfPack having its wolves sorted by their ranks
     */
    public ArrayList<Wolf> insertionSort(ArrayList<Wolf> wolfPack) {
        int i;
        int j;
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
                    wolf.setRank(WolfRank.OMEGA);
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
        ThreadLocalRandom random = ThreadLocalRandom.current();

        // Generate a random rank between 2 and 24, as we do not want anyone to be randomly ranked as Alpha
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
            if (!wolf.getSex() && wolf.getRank().getId() == 1) {
                return wolf;
            }
        }
        return null;
    }

    /**
     * Getter for all omega of the pack
     * @return all omega
     */
    public Wolf getOmega() {
        for (Wolf wolf : this.getWolfList()) {
            if (wolf.getRank().getId() == 24) {
                return wolf;
            }
        }
        return null;
    }

    /**
     * Allows to designate a new Alpha male, in case the current one walks away or dies
     * @param wolf The alpha male that is being deleted
     */
    public void designateNewAlphaMaleBeforeDeletion(Wolf wolf) {

        Wolf potentialCandidate = null;
        int potentialCandidateRank = 24;
        int candidateId = 0;

        for (int i = 0; i < this.getWolfList().size(); i++) {
            Wolf wolfToTest = this.getWolfList().get(i);

            if (wolfToTest.getSex() && !wolfToTest.equals(this.getAlphaMale()) && wolfToTest.getRank().getId() < potentialCandidateRank && wolfToTest.getRank().getId() != 24) {
                potentialCandidate = wolfToTest;
                potentialCandidateRank = wolfToTest.getRank().getId();
                candidateId = i;
            }
        }

        // In case after trying all other members of the pack, we have no better candidate, then disband the pack
        if (potentialCandidate == null || potentialCandidateRank == 24) {
            View.displayInformationMessage("No candidate for the Alpha male role was found. The pack disbanded.\n");
            this.disband();
        } else {
            View.displayInformationMessage("A new alpha male has been designated for the pack.\n");
            this.getWolfList().get(candidateId).setRank(WolfRank.ALPHA);

            // Remove the old alpha, and re-order the pack
            this.removeWolfPackAttributes(wolf);
            this.getWolfList().remove(wolf);
            this.setWolfList(this.insertionSort(this.getWolfList()));
        }
    }

    /**
     * Allows to disband the pack why removing all of its member, and resetting their pack related attributes
     */
    public void disband() {
        int originalSize = this.getWolfList().size();
        for (int i = 0; i < originalSize; i++) {
            Wolf wolf = this.getWolfList().get(0);
            // Delete pack related attributes
            wolf.setPack(null);
            wolf.setRank(null);
            wolf.setLevel(0);
            this.getWolfList().remove(wolf);
        }
    }

    @Override
    public String toString() {
        return  "\n" + Color.BLUE + Color.UNDERLINE + "\nWolf pack:\n" + Color.DEFAULT +
                this.getWolfList() + " \n" +
                ((this.getAlphaMale() != null) ? Color.BLUE + Color.UNDERLINE + "Alpha male:" + Color.DEFAULT + this.getAlphaMale() + "\n" : Color.BLUE + "No alpha male\n") +
                ((this.getAlphaFemale() != null) ? Color.BLUE + Color.UNDERLINE + "Alpha female:" + Color.DEFAULT + this.getAlphaFemale() + "\n" : Color.BLUE + "No alpha female\n")+
                ((this.getOmega() != null) ? Color.BLUE + Color.UNDERLINE + "Wolf omega:" + Color.DEFAULT + this.getOmega() + "\n" : Color.BLUE + "No omega\n")
                ;
    }

}
