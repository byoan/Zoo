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

    public static void displayWarningMessage(String message) {
        System.out.println("\033[31m" + message + "\033[37m");
    }

    public static void displaySuccessMessage(String message) {
        System.out.println("\033[34m" + message + "\033[37m");
    }

    public static void displayInformationMessage(String message) {
        System.out.println("\033[35m" + message +  "\033[37m");
    }

    public static void displayErrorMessage(String message) {
        System.out.println("\032[33m" + message +  "\033[37m");
    }

    public static void displayWelcomeMessage() {
        System.out.println("\n\n====== WELCOME TO ZOO SIMULATOR 3000 ======\n\n");
    }
}