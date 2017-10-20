package models.enums;

public enum UserActions {

    INSPECT(1, "Inspect an enclosure"),
    CLEAN(2, "Clean an enclosure"),
    FEED(3, "Feed an enclosure"),
    HEAL(4, "Heal an animal"),
    TRANSFER(5, "Transfer an animal"),
    NBANIMALS(6, "Display the current number of animals in the zoo"),
    ANIMALSINZOO(7, "Display all the animals currently in the zoo"),
    NOTHING(8, "Do nothing");

    private int id;
    private String name;

    UserActions(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the ID of the UserAction
     * @return The UserAction id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for the name of the UserAction
     * @return The UserAction name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the user action in a displayable format
     * @return The user action with its id and name
     */
    public String display() {
        return this.getId() + ". " + this.getName();
    }
}
