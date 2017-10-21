package controllers.jobs;

import models.animals.packs.WolfPack;
import models.animals.Wolf;
import models.enclosures.Enclosure;
import models.enums.WolfRank;
import views.Lang;
import views.View;

import java.util.ArrayList;

public class CheckDominationFactorJob implements Runnable {

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
    @Override
    public void run() {
        // Browse all the enclosures in the Zoo
        for (Enclosure<Wolf> enclosure : this.getEnclosures()) {
            if (enclosure.getWolfPack() != null) {
                // Check each animal within the enclosure that has a wolf pack
                for (Wolf animalToCheck : enclosure.getAnimals()) {
                    // If an animal is not an Alpha, and has a 50 or less domination factor, we have to check if we can retrograde him
                    if (animalToCheck.getRank().getId() != 1 && animalToCheck.getDominationFactor() <= 50) {
                        WolfPack pack = animalToCheck.getPack();

                        this.compareAnimalWithItsPack(pack, animalToCheck);
                    }
                }
            }
        }
    }

    /**
     * Allows to retrograde a wolf by 1 rank
     * @param wolf The wolf which must be retrograded
     */
    public void retrogradeWolf(Wolf wolf) {
        // We do not need to add anything to the current ID, as the array returned by .values() starts at 0
        int newRankId = wolf.getRank().getId();
        // Second + 1, as .values() returns an array starting with 0, and our list begins with id 1
        wolf.setRank(WolfRank.values()[newRankId]);
        // Reset so it doesn't get retrograded at each turn
        wolf.setDominationFactor(100);
    }

    /**
     * Checks within the Pack's wolves if there is another wolf with the same sex and same rank
     * @param pack The Wolf Pack to check
     * @param animalToCheck The animal we have to check with the pack's wolves
     * @return Will return true if we found another wolf which has the same sex and rank, or false if not
     */
    public void compareAnimalWithItsPack(WolfPack pack, Wolf animalToCheck) {
        for (Wolf wolf : pack.getWolfList()) {
            if (animalToCheck.getSex() == wolf.getSex() && animalToCheck.getRank().getId() == wolf.getRank().getId()) {
                this.retrogradeWolf(animalToCheck);
                View.displayInformationMessage(Lang.PACK_WOLF_RETROGRADED);
            }
        }
    }
}
