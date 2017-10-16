package Views;

public class View {

    public void display() {

    }

    /**
     * Allows to display the user main action menu
     */
    public static void displayPickAction() {
        View.displayMessage("Choose an action by entering it's number:\n");
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
