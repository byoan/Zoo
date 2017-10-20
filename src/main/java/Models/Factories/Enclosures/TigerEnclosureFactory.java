package Models.Factories.Enclosures;

import Models.Animals.Tiger;
import Models.Enclosures.Enclosure;
import Models.Factories.AnimalFactory;
import Models.Factories.EnclosureFactory;
import Views.View;

import java.util.concurrent.ThreadLocalRandom;

public class TigerEnclosureFactory extends EnclosureFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static TigerEnclosureFactory instance = null;

    /**
     * Enclosure number, incremented at each Enclosure creation
     * Allows to have different enclosures name when we create several random enclosures using this Factory
     */
    protected static int enclosureNb = 0;

    /**
     * Constructor for the TigerEnclosureFactory
     * Private accessibility as we do not want anyone to instantiate this class (Singleton)
     */
    private TigerEnclosureFactory() {
        super();
    }

    /**
     * Allows to create the concrete Enclosure instance which will be returned to the object that called the Factory
     * @param populate Whether the created enclosure should be populated with animals
     * @return A new Tiger Enclosure
     */
    public Enclosure<Tiger> createEnclosure(boolean populate) {
        enclosureNb++;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Enclosure<Tiger> enclosure = new Enclosure<Tiger>("Tiger Enclosure n°" + enclosureNb, random.nextInt(100, 501), 50);

        if (populate) {
            this.generateRandomPopulation(enclosure);
        }
        return enclosure;
    }

    /**
     * Allows to retrieve the instance of the TigerEnclosureFactory (Singleton)
     * Will create a new instance on first call
     * synchronized keyword allows us to keep it a singleton even when using multiple threads
     * @return The TigerEnclosureFactory instance
     */
    public static synchronized TigerEnclosureFactory getInstance() {
        if (instance == null) {
            instance = new TigerEnclosureFactory();
        }
        return instance;
    }

    /**
     * Allows to generate animals to populate the created enclosure
     * Will be called if the createEnclosure method of the Factory is called with the true parameter, which represents the "Do you want to populate the enclosure you are creating ?"
     */
    public void generateRandomPopulation(Enclosure<Tiger> enclosure) {
        for (int i = 0; i < enclosure.getMaxAnimals() / 2; i++) {
            AnimalFactory animalFactory = AnimalFactory.getInstance();
            try {
                enclosure.add(animalFactory.createTiger());
            } catch (Exception e) {
                View.displayErrorMessage("An error occurred while trying to add an animal to an enclosure : " + e.getMessage());
            }
        }
    }

}
