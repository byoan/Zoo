package views;

import models.enums.UserActions;

public class View {

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
        View.displayMenuMessage("Choose an action by entering it's number:\n");
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

    public static void displayAnimalActionMessage(String message) {
        System.out.println("\033[92m" + message +  "\033[37m");
    }

    public static void displayMenuMessage(String message) {
        System.out.println("\033[96m" + message +  "\033[37m");
    }

    public static void displayConsoleMessage(String message) {
        System.out.println("\033[95m" + message +  "\033[37m");
    }

    public static void displayErrorMessage(String message) {
        System.out.println("\032[33m" + "\032[1m" + message +  "\033[37m");
    }

    public static void displayWelcomeMessage() {
        System.out.println("\n\n|‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾|\n" +
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