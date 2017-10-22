package models.enums;

/**
 * Represents all the possible Wolves ranks in a Pack
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public enum WolfRank {

    ALPHA(1, "Alpha"),
    BETA(2, "Beta"),
    GAMMA(3, "Gamma"),
    DELTA(4, "Delta"),
    EPSILON(5, "Epsilon"),
    ZETA(6, "Zeta"),
    ETA(7, "Eta"),
    THETA(8, "Theta"),
    IOTA(9, "Iota"),
    KAPPA(10, "Kappa"),
    LAMBDA(11, "Lambda"),
    MU(12, "Mu"),
    NU(13, "Nu"),
    XI(14, "Xi"),
    OMICRON(15, "Omicron"),
    PI(16, "Pi"),
    RHO(17, "Rhô"),
    SIGMA(18, "Sigma"),
    TAU(19, "Tau"),
    UPSILON(20, "Upsilon"),
    PHI(21, "Phi"),
    CHI(22, "Chi"),
    PSI(23, "Psi"),
    OMEGA(24, "Omega");

    /**
     * Represents the id of the rank
     */
    private int id;

    /**
     * Represents the name of the rank
     */
    private String name;

    /**
     * Constructor which will assign the received id and name to our attributes
     * @param id The id of the rank
     * @param name The name of the rank
     */
    WolfRank(int id , String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the rank's id
     * @return The id of the rank
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for the rank's name
     * @return The name of the rank
     */
    public String getName() {
        return this.name;
    }
}
