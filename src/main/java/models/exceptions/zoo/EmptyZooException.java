package models.exceptions.zoo;

import controllers.zoo.Zoo;

/**
 * Represents an exception thrown when
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public class EmptyZooException extends Exception {

    /**
     * Represents the zoo on which the operation was performed
     */
    private Zoo zoo;

    /**
     * Constructor for the Exception
     * @param zoo The zoo involved in the Exception
     */
    public EmptyZooException(Zoo zoo) {
        this.zoo = zoo;
    }

    /**
     * Getter for the zoo attribute which represents the zoo involved in the Exception
     * @return The Zoo instance that was given during Exception construction
     */
    public Zoo getZoo() {
        return this.zoo;
    }

    /**
     Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message indicating that the zoo has no enclosures
     */
    @Override
    public String getMessage() {
        return "The " + this.getZoo().getName() + " zoo hasn't any enclosure yet.";
    }
}
