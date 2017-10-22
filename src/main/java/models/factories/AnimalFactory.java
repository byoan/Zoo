package models.factories;

import models.animals.*;

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
     * synchronized keyword allows us to keep it a singleton even when using multiple threads
     * @return The AnimalFactory instance
     */
    public static synchronized AnimalFactory getInstance() {
        if (instance == null) {
            instance = new AnimalFactory();
        }
        return instance;
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
     * Allows to create an Eagle
     * Used for the copulation where the copulation turn is needed for hatching
     * @param copulationTurn The turn at which the copulation happened, used later to calculate at each turn if the animal hatched
     * @return The newly created eagle
     */
    public Eagle createEagle(int copulationTurn) {
        return new Eagle(copulationTurn);
    }

    /**
     * Allows to create a Fish
     * @return The newly created fish
     */
    public Fish createFish() {
        return new Fish();
    }

    /**
     * Allows to create a Fish
     * Used for the copulation where the copulation turn is needed for hatching
     * @param copulationTurn The turn at which the copulation happened, used later to calculate at each turn if the animal hatched
     * @return The newly created fish
     */
    public Fish createFish(int copulationTurn) {
        return new Fish(copulationTurn);
    }

    /**
     * Allows to create a Penguin
     * @return The newly created penguin
     */
    public Penguin createPenguin() {
        return new Penguin();
    }

    /**
     * Allows to create a Penguin
     * Used for the copulation where the copulation turn is needed for hatching
     * @param copulationTurn The turn at which the copulation happened, used later to calculate at each turn if he hatched
     * @return The newly created penguin
     */
    public Penguin createPenguin(int copulationTurn) {
        return new Penguin(copulationTurn);
    }

    /**
     * Allows to create a Shark
     * @return The newly created shark
     */
    public Shark createShark() {
        return new Shark();
    }

    /**
     * Allows to create a Shark
     * Used for the copulation where the copulation turn is needed for hatching
     * @param copulationTurn The turn at which the copulation happened, used later to calculate at each turn if he hatched
     * @return The newly created shark
     */
    public Shark createShark(int copulationTurn) {
        return new Shark(copulationTurn);
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
