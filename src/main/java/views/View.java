package views;

import models.enums.UserActions;

public class View {

    /**
     * Empty constructor to prevent our View to be instantiated
     */
    private View() {

    }

    /**
     * Allows to display a generic back to menu message, preceded by a 0, which is the number that the user must type to go back to the menu
     */
    public static void displayBackToMenuMessage() {
        displayMenuMessage("0. Go back to menu\n");
    }

    /**
     * Allows to display the user main action menu
     */
    public static void displayPickAction() {
        View.displayConsoleMessage("Choose an action by entering it's number\n");
        for (UserActions action : UserActions.values()) {
            View.displayMenuMessage(action.display());
        }
    }

    /**
     * Allows to display the received message in the console
     * Currently uses System.out.println()
     * @param message The message to display
     */
    public static void displayMessage(String message) {
        System.out.println(Color.WHITE + message);
    }

    public static void displayWarningMessage(String message) {
        displayMessage(Color.YELLOW + Color.BOLD + message + Color.DEFAULT);
    }

    public static void displaySuccessMessage(String message) {
        displayMessage(Color.GREEN + message + Color.DEFAULT);
    }

    public static void displayInformationMessage(String message) {
        displayMessage(Color.BLUE + message + Color.DEFAULT);
    }

    public static void displayAnimalActionMessage(String message) {
        displayMessage(Color.PINK + message + Color.DEFAULT);
    }

    public static void displayMenuMessage(String message) {
        displayMessage(Color.WHITE + message + Color.DEFAULT);
    }

    public static void displayConsoleMessage(String message) {
        displayMessage(Color.CYAN + message + Color.DEFAULT);
    }

    public static void displayErrorMessage(String message) {
        displayMessage(Color.RED + Color.BOLD + message + Color.DEFAULT);
    }

    public static void displayWelcomeMessage() {
        displayMessage("\n\n" +
                "|¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯|\n" +
                "|===============================================================================================|\n" +
                "|                                                                                               |\n" +
                "|                    __      _____ _    ___ ___  __  __ ___    _____ ___                        |\n" +
                "|                    \\ \\    / / __| |  / __/ _ \\|  \\/  | __|  |_   _/ _ \\                       |\n" +
                "|                     \\ \\/\\/ /| _|| |_| (_| (_) | |\\/| | _|     | || (_) |                      |\n" +
                "|                      \\_/\\_/ |___|____\\___\\___/|_|  |_|___|    |_| \\___/                       |\n" +
                "|                                                                                               |\n" +
                "|                                                                                               |\n" +
                "|                                                                                               |\n" +
                "|     _______   ___     ___ ___ __  __ _   _ _  _____ _ _____ ___  ___    ____ __   __   __     |\n" +
                "|    |_  / _ \\ / _ \\   / __|_ _|  \\/  | | | | ||_   _/_\\_   _/ _ \\| _ \\  |__ //  \\ /  \\ /  \\    |\n" +
                "|     / / (_) | (_) |  \\__ \\| || |\\/| | |_| | |__| |/ _ \\| || (_) |   /   |_ \\ () | () | () |   |\n" +
                "|    /___\\___/ \\___/   |___/___|_|  |_|\\___/|____|_/_/ \\_\\_| \\___/|_|_\\  |___/\\__/ \\__/ \\__/    |\n" +
                "|                                                                                               |\n" +
                "|                                                                                               |\n" +
                "|===============================================================================================|\n" +
                "|_______________________________________________________________________________________________|\n\n");
    }
}