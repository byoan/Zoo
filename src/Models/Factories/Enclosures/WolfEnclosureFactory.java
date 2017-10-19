package Models.Factories.Enclosures;

import Models.Animals.Wolf;
import Models.Enclosures.Enclosure;
import Models.Factories.AnimalFactory;
import Models.Factories.EnclosureFactory;
import Views.View;

import java.util.concurrent.ThreadLocalRandom;

public class WolfEnclosureFactory extends EnclosureFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static WolfEnclosureFactory instance = null;

    /**
     * Enclosure number, incremented at each Enclosure creation
     * Allows to have different enclosures name when we create several random enclosures using this Factory
     */
    private static int enclosureNb = 0;

    /**
     * Constructor for the WolfEnclosureFactory
     * Private accessibility as we do not want anyone to instantiate this class (Singleton)
     */
    private WolfEnclosureFactory() {
        super();
    }

    /**
     * Allows to create the concrete Enclosure instance which will be returned to the object that called the Factory
     * @param populate Whether the created enclosure should be populated with animals
     * @return A new Wolf Enclosure
     */
    public Enclosure<Wolf> createEnclosure(boolean populate) {
        enclosureNb++;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Enclosure<Wolf> enclosure = new Enclosure<Wolf>("Wolf Enclosure n°" + enclosureNb, random.nextInt(100, 501), 50);

        if (populate) {
            this.generateRandomPopulation(enclosure);
        }
        return enclosure;
    }

    /**
     * Allows to retrieve the instance of the WolfEnclosureFactory (Singleton)
     * Will create a new instance on first call
     * @return The WolfEnclosureFactory instance
     */
    public static WolfEnclosureFactory getInstance() {
        if (WolfEnclosureFactory.instance == null) {
            // synchronized allows use to keep the singleton even when using multiple threads
            synchronized(WolfEnclosureFactory.class) {
                if (WolfEnclosureFactory.instance == null) {
                    WolfEnclosureFactory.instance = new WolfEnclosureFactory();
                }
            }
        }
        return WolfEnclosureFactory.instance;
    }

    /**
     * Allows to generate animals to populate the created enclosure
     * Will be called if the createEnclosure method of the Factory is called with the true parameter, which represents the "Do you want to populate the enclosure you are creating ?"
     */
    public void generateRandomPopulation(Enclosure<Wolf> enclosure) {
        for (int i = 0; i < enclosure.getMaxAnimals() / 2; i++) {
            AnimalFactory animalFactory = AnimalFactory.getInstance();
            try {
                enclosure.add(animalFactory.createWolf());
            } catch (Exception e) {
                View.displayErrorMessage("An error occurred while trying to add an animal to an enclosure : " + e.getMessage());
            }
        }
    }

}
