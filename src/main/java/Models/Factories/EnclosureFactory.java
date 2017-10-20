package Models.Factories;

import Models.Animals.*;
import Models.Enclosures.Enclosure;
import Models.Factories.Enclosures.*;
import Views.View;

public abstract class EnclosureFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static EnclosureFactory instance = null;

    /**
     * Constructor for the EnclosureFactory
     * Protected accessibility as we do not want anyone to instantiate this class (Singleton), only the concrete factories
     */
    protected EnclosureFactory() {
        super();
    }

    /**
     * Abstract method to create a typed enclosure, which will later be implemented in each children factory
     * @param populate Whether the created enclosure should be populated or not
     * @return The newly created enclosure
     */
    public abstract Enclosure createEnclosure(boolean populate);

    /**
     * Allows to retrieve the instance of the EnclosureFactory (Singleton)
     * Will create a new instance on first call
     * @return The EnclosureFactory instance
     */
    public static <A extends Animal> EnclosureFactory getInstance(int type) {

            switch (type) {
                case 1:
                    return BearEnclosureFactory.getInstance();

                case 2:
                    return EagleEnclosureFactory.getInstance();

                case 3:
                    return FishEnclosureFactory.getInstance();

                case 4:
                    return PenguinEnclosureFactory.getInstance();

                case 5:
                    return SharkEnclosureFactory.getInstance();

                case 6:
                    return TigerEnclosureFactory.getInstance();

                case 7:
                    return WhaleEnclosureFactory.getInstance();

                case 8:
                    return WolfEnclosureFactory.getInstance();

                default:
                    View.displayErrorMessage("An error occurred while trying to create an enclosure");
                    return null;
            }
    }
}
