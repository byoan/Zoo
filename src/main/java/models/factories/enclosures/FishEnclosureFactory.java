package models.factories.enclosures;

import models.animals.Fish;
import models.enclosures.Aquarium;
import models.enclosures.Enclosure;
import models.factories.AnimalFactory;
import models.factories.EnclosureFactory;
import views.View;

import java.util.concurrent.ThreadLocalRandom;

public class FishEnclosureFactory extends EnclosureFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static FishEnclosureFactory instance = null;

    /**
     * Enclosure number, incremented at each Enclosure creation
     * Allows to have different enclosures name when we create several random enclosures using this Factory
     */
    protected static int enclosureNb = 0;

    /**
     * Constructor for the FishEnclosureFactory
     * Private accessibility as we do not want anyone to instantiate this class (Singleton)
     */
    private FishEnclosureFactory() {
        super();
    }

    /**
     * Allows to create the concrete Enclosure instance which will be returned to the object that called the Factory
     * @param populate Whether the created enclosure should be populated with animals
     * @return A new Fish Aquarium
     */
    public Aquarium<Fish> createEnclosure(boolean populate) {
        enclosureNb++;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Aquarium<Fish> aquarium = new Aquarium<Fish>("Fish Aquarium n°" + enclosureNb, random.nextInt(2, 5), 50, 2, 100, 2);

        if (populate) {
            this.generateRandomPopulation(aquarium);
        }
        return aquarium;
    }

    /**
     * Allows to retrieve the instance of the FishEnclosureFactory (Singleton)
     * Will create a new instance on first call
     * synchronized keyword allows us to keep it a singleton even when using multiple threads
     * @return The FishEnclosureFactory instance
     */
    public static synchronized FishEnclosureFactory getInstance() {
        if (instance == null) {
            instance = new FishEnclosureFactory();
        }
        return instance;
    }

    /**
     * Allows to generate animals to populate the created enclosure
     * Will be called if the createEnclosure method of the Factory is called with the true parameter, which represents the "Do you want to populate the enclosure you are creating ?"
     */
    public void generateRandomPopulation(Enclosure<Fish> enclosure) {
        for (int i = 0; i < enclosure.getMaxAnimals() / 2; i++) {
            AnimalFactory animalFactory = AnimalFactory.getInstance();
            try {
                enclosure.add(animalFactory.createFish());
            } catch (Exception e) {
                View.displayErrorMessage("An error occurred while trying to add an animal to an enclosure : " + e.getMessage());
            }
        }
    }

}
