package Models.Exceptions.Zoo;

import Controllers.Zoo.Zoo;

public class EmptyZooException extends Exception {

    /**
     * Represents the Zoo on which the operation was performed
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
     * Getter for the Zoo attribute which represents the Zoo involved in the Exception
     */
    public Zoo getZoo() {
        return zoo;
    }

    /**
     Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message indicating that the zoo has no enclosures
     */
    public String getMessage() {
        return "The " + this.getZoo().getName() + " zoo hasn't any enclosure yet.";
    }
}
