package Models.Factories.Enclosures;

import Models.Animals.Whale;
import Models.Enclosures.Aquarium;
import Models.Enclosures.Enclosure;
import Models.Factories.AnimalFactory;
import Models.Factories.EnclosureFactory;
import Views.View;

import java.util.concurrent.ThreadLocalRandom;

public class WhaleEnclosureFactory extends EnclosureFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static WhaleEnclosureFactory instance = null;

    /**
     * Enclosure number, incremented at each Enclosure creation
     * Allows to have different enclosures name when we create several random enclosures using this Factory
     */
    protected static int enclosureNb = 0;

    /**
     * Constructor for the WhaleEnclosureFactory
     * Private accessibility as we do not want anyone to instantiate this class (Singleton)
     */
    private WhaleEnclosureFactory() {
        super();
    }

    /**
     * Allows to create the concrete Enclosure instance which will be returned to the object that called the Factory
     * @param populate Whether the created enclosure should be populated
     * @return A new Whale Aquarium
     */
    public Aquarium<Whale> createEnclosure(boolean populate) {
        enclosureNb++;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Aquarium<Whale> aquarium = new Aquarium<Whale>("Whale Aquarium n°" + enclosureNb, random.nextInt(2, 5), 50, 2, 100, 2);

        if (populate) {
            this.generateRandomPopulation(aquarium);
        }
        return aquarium;
    }

    /**
     * Allows to retrieve the instance of the WhaleEnclosureFactory (Singleton)
     * Will create a new instance on first call
     * synchronized keyword allows us to keep it a singleton even when using multiple threads
     * @return The WhaleEnclosureFactory instance
     */
    public static synchronized WhaleEnclosureFactory getInstance() {
        if (instance == null) {
            instance = new WhaleEnclosureFactory();
        }
        return instance;
    }

    /**
     * Allows to generate animals to populate the created enclosure
     * Will be called if the createEnclosure method of the Factory is called with the true parameter, which represents the "Do you want to populate the enclosure you are creating ?"
     */
    public void generateRandomPopulation(Enclosure<Whale> enclosure) {
        for (int i = 0; i < enclosure.getMaxAnimals() / 2; i++) {
            AnimalFactory animalFactory = AnimalFactory.getInstance();
            try {
                enclosure.add(animalFactory.createWhale());
            } catch (Exception e) {
                View.displayErrorMessage("An error occurred while trying to add an animal to an enclosure : " + e.getMessage());
            }
        }
    }

}
