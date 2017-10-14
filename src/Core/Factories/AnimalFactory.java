package Core.Factories;

import animals.*;

public final class AnimalFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static AnimalFactory instance = null;

    /**
     * Constructor for the AnimalFactory
     * Private accessibility as we do not want anyone to instantiate this class (Singleton)
     */
    private AnimalFactory() {
        super();
    }

    /**
     * Allows to retrieve the instance of the AnimalFactory (Singleton)
     * Will create a new instance on first call
     * @return The AnimalFactory instance
     */
    public static AnimalFactory getInstance() {
        if (AnimalFactory.instance == null) {
            // synchronized allows use to keep the singleton even when using multiple threads
            synchronized(AnimalFactory.class) {
                if (AnimalFactory.instance == null) {
                    AnimalFactory.instance = new AnimalFactory();
                }
            }
        }
        return AnimalFactory.instance;
    }

    /**
     * Allows to create a Bear
     * @return The newly created bear
     */
    public Bear createBear() {
        return new Bear();
    }

    /**
     * Allows to create an Eagle
     * @return The newly created eagle
     */
    public Eagle createEagle() {
        return new Eagle();
    }

    /**
     * Allows to create a Fish
     * @return The newly created fish
     */
    public Fish createFish() {
        return new Fish();
    }

    /**
     * Allows to create a Pinguin
     * @return The newly created pinguin
     */
    public Pinguin createPinguin() {
        return new Pinguin();
    }

    /**
     * Allows to create a Shark
     * @return The newly created shark
     */
    public Shark createShark() {
        return new Shark();
    }

    /**
     * Allows to create a Tiger
     * @return The newly created tiger
     */
    public Tiger createTiger() {
        return new Tiger();
    }

    /**
     * Allows to create a Whale
     * @return The newly created whale
     */
    public Whale createWhale() {
        return new Whale();
    }

    /**
     * Allows to create a Wolf
     * @return The newly created wolf
     */
    public Wolf createWolf() {
        return new Wolf();
    }

}
