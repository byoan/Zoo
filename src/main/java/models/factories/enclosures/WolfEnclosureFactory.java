package models.factories.enclosures;

import models.animals.Wolf;
import models.enclosures.Enclosure;
import models.factories.AnimalFactory;
import models.factories.EnclosureFactory;
import views.View;

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
    protected static int enclosureNb = 0;

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
     * synchronized keyword allows us to keep it a singleton even when using multiple threads
     * @return The WolfEnclosureFactory instance
     */
    public static synchronized WolfEnclosureFactory getInstance() {
        if (instance == null) {
            instance = new WolfEnclosureFactory();
        }
        return instance;
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
