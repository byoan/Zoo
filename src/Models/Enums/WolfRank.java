package Models.Enums;

/**
 * Enum of all possible rank in a pack
 */
public enum WolfRank {

    Alpha(1, "Alpha"),
    Beta(2, "Beta"),
    Gamma(3, "Gamma"),
    Delta(4, "Delta"),
    Epsilon(5, "Epsilon"),
    Zeta(6, "Zeta"),
    Eta(7, "Eta"),
    Theta(8, "Theta"),
    Iota(9, "Iota"),
    Kappa(10, "Kappa"),
    Lambda(11, "Lambda"),
    Mu(12, "Mu"),
    Nu(13, "Nu"),
    Xi(14, "Xi"),
    Omicron(15, "Omicron"),
    Pi(16, "Pi"),
    Rhô(17, "Rhô"),
    Sigma(18, "Sigma"),
    Tau(19, "Tau"),
    Upsilon(20, "Upsilon"),
    Phi(21, "Phi"),
    Chi(22, "Chi"),
    Psi(23, "Psi"),
    Omega(24, "Omega");

    private int id;
    private String name;

    WolfRank(int id , String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}
