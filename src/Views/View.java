package Views;

import Models.Enums.UserActions;

public class View {

    /**
     * Allows to display a generic back to menu message, preceded by a 0, which is the number that the user must type to go back to the menu
     */
    public static void displayBackToMenuMessage() {
        displayMessage("0. Go back to menu\n");
    }

    /**
     * Allows to display the user main action menu
     */
    public static void displayPickAction() {
        View.displayMessage("Choose an action by entering it's number:\n");
        for (UserActions action : UserActions.values()) {
            View.displayMessage(action.display());
        }
    }

    /**
     * Allows to display the received message in the console
     * Currently uses System.out.println()
     * @param message The message to display
     */
    public static void displayMessage(String message) {
        System.out.println(message);
    }

}
