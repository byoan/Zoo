package models.enums;


public enum AnimalTypes {

    BEAR(1, "Bear"),
    EAGLE(2, "Eagle"),
    FISH(3, "Fish"),
    PENGUIN(4, "Penguin"),
    SHARK(5, "Shark"),
    TIGER(6, "Tiger"),
    WHALE(7, "Whale"),
    WOLF(8, "Wolf");

    /**
     * Represents the ID associated with the animal, which will allow us to pick an random number in all the types
     */
    private int id;

    /**
     * Represents the name of the animal type
     */
    private String name;

    /**
     * Constructor for an Enum value
     * @param id The Id of the animal type (basically its order within the animals package)
     * @param name The name of the animal type
     */
    AnimalTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the id of the animal type
     * @return The animal type id
     */
    public int getId() {
        return this.id;
    }

}
