package views;

/**
 * Represents the color text that are used in display
 *
 * @author Yoan Ballesteros
 * @author Antoine Sirven
 * @version 1.0
 */
public final class Color {

    /**
     * Private constructor as we do not want this class to be instantiated at any time
     */
    private Color() {

    }

    /**
     * Reset the style and color to default
     */
    public static final String DEFAULT ="\033[0m";

    /**
     * Set some color to use
     */
    public static final String WHITE = "\033[37m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String CYAN = "\033[36m";
    public static final String PINK = "\033[35m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";

    /**
     * Set some style to use
     */
    public static final String BOLD ="\033[01m";
    public static final String UNDERLINE ="\033[04m";
    public static final String FLASH ="\033[05m";
    public static final String OVER ="\033[07m";


}
