package Core.Factories;

import animals.*;

public final class AnimalFactory {

    private static AnimalFactory instance = null;

    private AnimalFactory() {
        super();
    }

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

    public Bear createBear() {
        return new Bear();
    }

    public Eagle createEagle() {
        return new Eagle();
    }

    public Fish createFish() {
        return new Fish();
    }

    public Pinguin createPinguin() {
        return new Pinguin();
    }

    public Shark createShark() {
        return new Shark();
    }

    public Tiger createTiger() {
        return new Tiger();
    }

    public Whale createWhale() {
        return new Whale();
    }

    public Wolf createWolf() {
        return new Wolf();
    }

}
