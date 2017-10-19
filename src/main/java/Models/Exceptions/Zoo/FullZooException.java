package Models.Exceptions.Zoo;

import Controllers.Zoo.Zoo;
import Models.Enclosures.Enclosure;

public class FullZooException extends Exception {

    /**
     * Represents the zoo involved in the exception
     */
    private Zoo zoo;

    /**
     * Represents the enclosure instance involved in the Exception
     */
    private Enclosure enclosure;

    /**
     * Constructor for the Exception
     * @param enclosure The enclosure which is involved in the Exception
     * @param zoo The zoo which caused the Exception
     */
    public FullZooException(Enclosure enclosure, Zoo zoo) {
        this.zoo = zoo;
        this.enclosure = enclosure;
    }

    /**
     * Getter for the Zoo which caused the Exception
     * @return The zoo instance
     */
    public Zoo getZoo() {
        return this.zoo;
    }

    /**
     * Getter for the enclosure involved in the Exception
     * @return The enclosure instance
     */
    public Enclosure getEnclosure() {
        return this.enclosure;
    }

    /**
     * Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message, with an indication regarding the maximum space of the enclosure
     */
    public String getMessage() {
        return "Could not add the " + this.getEnclosure().getName() + " enclosure to the " + this.getZoo().getName() + " zoo, as it is already full (" + this.getZoo().getEnclosureList().size() + "/" + this.getZoo().getMaxNbEnclosure() + ").";
    }
}
