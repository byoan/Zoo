package models.exceptions.zoo;

public class CantFindEnclosureByNameException extends Exception {

    /**
     * Represents the searched enclosure name that could not be found
     */
    private String enclosureName;

    /**
     * Constructor for the exception
     * @param enclosureName The enclosure name that was searched
     */
    public CantFindEnclosureByNameException(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    /**
     * Getter for the searched enclosure name attribute
     * @return The searched enclosure name attribute that could not be found
     */
    public String getEnclosureName() {
        return this.enclosureName;
    }

    /**
     Allows to display a more customized error message when calling e.getMessage() in the catch
     * @return The error message indicating that the searched enclosure name could not be found in the zoo
     */
    public String getMessage() {
        return "The enclosure named " + this.getEnclosureName() + " could not be found.";
    }

}
