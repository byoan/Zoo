package controllers.jobs;

import models.animals.packs.WolfPack;
import models.animals.Wolf;
import models.enclosures.Enclosure;
import models.enums.WolfRank;

import java.util.ArrayList;

public class CheckDominationFactorJob {

    /**
     * Represents the pack to test
     */
    ArrayList<Enclosure> enclosures;

    public CheckDominationFactorJob(ArrayList<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

    /**
     * Getter for the zoo enclosures list received during construction
     * @return The enclosures list
     */
    public ArrayList<Enclosure> getEnclosures() {
        return this.enclosures;
    }

    /**
     * Performs the Job role
     * Will check the domination factor of all the wolves, and decrease the ones that matches the conditions domination <= 50 && not the last of his sex in its pack
     */
    public void exec() {
        for (Enclosure<Wolf> enclosure : this.getEnclosures()) {
            if (enclosure.getWolfPack() != null) {
                for (Wolf animalToCheck : enclosure.getAnimals()) {
                    if (animalToCheck.getRank().getId() != 1 && animalToCheck.getDominationFactor() <= 50) {
                        WolfPack pack = animalToCheck.getPack();
                        boolean willBeRetrograded = false;
                        for (Wolf wolf : pack.getWolfList()) {
                            if (animalToCheck.getSex() == wolf.getSex() && animalToCheck.getRank().getId() == wolf.getRank().getId()) {
                                willBeRetrograded = true;
                                break;
                            } else {
                                willBeRetrograded = false;
                            }
                        }
                        if (willBeRetrograded) {
                            // We do not need to add anything to the current ID, as the array returned by .values() starts at 0
                            int newRankId = animalToCheck.getRank().getId();
                            // Second + 1, as .values() returns an array starting with 0, and our list begins with id 1
                            animalToCheck.setRank(WolfRank.values()[newRankId]);
                            // Reset so it doesn't get retrograded at each turn
                            animalToCheck.setDominationFactor(100);
                        }
                    }
                }
            }
        }
    }

}
