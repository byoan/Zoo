package views;

public final class Lang {

    /**
     * Private constructor as we do not want this class to be instantiated at any time
     */
    private Lang() {

    }

    public static final String NO_ENCLOSURES_IN_ZOO = "No enclosures are currently in the zoo.\n";
    public static final String WRONG_ENCLOSURE_NUMBER_USER_INPUT = "Can't enter a value greater than the maximum enclosure. Please try again:\n";
    public static final String NOT_AVAILABLE_ENCLOSURE = "This enclosure is not available. Please chose another one\n";
    public static final String ENCLOSURES_DETERIORATED = "Enclosures are deteriorated. You should clean them.\n";
    public static final String WRONG_USER_ACTION = "Did not understood your action, please try again";
    public static final String CURRENT_NUMBER_ANIMALS_IN_ZOO = "Current number of animals in the zoo --> ";

    public static final String NOT_DOING_ANYTHING_THIS_TURN = "Not doing anything this turn ...";
    public static final String SELECT_ENCLOSURE_TO_FEED = Color.CYAN + "Select the enclosure that you want to feed" + Color.DEFAULT + "\n";
    public static final String ENCLOSURE_FED = "This enclosure has been fed";

    public static final String SELECT_ENCLOSURE_TO_HEAL = Color. CYAN + "Select the enclosure containing the animal you want to heal" + Color.DEFAULT + "\n";
    public static final String SELECT_ANIMAL_TO_HEAL = Color.CYAN + "Select the animal you want to heal" + Color.DEFAULT + "\n";


    public static final String SELECT_ORIGIN_ENCLOSURE_FOR_TRANSFER = Color.CYAN + "Select the enclosure containing the animal you want to transfer" + Color.DEFAULT + "\n";
    public static final String SELECT_ANIMAL_FOR_TRANSFER = Color.CYAN + "Select the animal you want to transfer" + Color.DEFAULT + "\n";
    public static final String SELECT_TARGET_ENCLOSURE_FOR_TRANSFER = Color.CYAN + "Select the enclosure in which you would like to send this animal" + Color.CYAN + "\n";

    public static final String TRANSFER_SAME_ENCLOSURE = "Can't transfer this animal in its current enclosure. Please choose another one.";


    public static final String FIGHT_TWO_ANIMALS_FOUGHT = "2 animals fought in the ";
    public static final String FIGHT_FIRST_ANIMAL_DIED = "The first one died.\n";
    public static final String FIGHT_SECOND_ANIMAL_DIED = "The second one died.\n";

    public static final String PACK_WOLF_RETROGRADED = "A wolf has been retrograded due to a low domination factor.\n";

    public static final String ENTER_VALID_VALUE = "Please enter a valid value";
    public static final String PLEASE_TRY_AGAIN = "Please try again.";
    public static final String ERROR = "Error: ";

    public static final String PICK_ENCLOSURE_TO_INSPECT = "\nWhich enclosure would you like to inspect?\n";
    public static final String INSPECTING_ENCLOSURE_N = "Inspecting the enclosure n°";

    public static final String PICK_ENCLOSURE_TO_CLEAN= "\nWhich enclosure would you like to clean?\n";
    public static final String CLEANING_ENCLOSURE_N = "Cleaning the enclosure n°";
}
