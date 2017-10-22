package models.factories.enclosures;

import models.animals.Eagle;
import models.enclosures.Aviary;
import models.enclosures.Enclosure;
import models.factories.AnimalFactory;
import models.factories.EnclosureFactory;
import views.View;

import java.util.concurrent.ThreadLocalRandom;

public class EagleEnclosureFactory extends EnclosureFactory {

    /**
     * The instance of the Factory, used by the Singleton
     */
    private static EagleEnclosureFactory instance = null;

    /**
     * Enclosure number, incremented at each Enclosure creation
     * Allows to have different enclosures name when we create several random enclosures using this Factory
     */
    protected static int enclosureNb = 0;

    /**
     * Constructor for the EagleEnclosureFactory
     * Private accessibility as we do not want anyone to instantiate this class (Singleton)
     */
    private EagleEnclosureFactory() {
        super();
    }

    /**
     * Allows to create the concrete Enclosure instance which will be returned to the object that called the Factory
     * @param populate Whether the created enclosure must be populated with animals
     * @return A new Eagle Aviary
     */
    public Aviary<Eagle> createEnclosure(boolean populate) {
        enclosureNb++;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Aviary<Eagle> aviary = new Aviary<Eagle>("Eagle Aviary n°" + enclosureNb, random.nextInt(50, 101), 50, random.nextInt(10, 16), random.nextInt(0, 3));

        if (populate) {
            this.generateRandomPopulation(aviary);
        }
        return aviary;
    }

    /**
     * Allows to retrieve the instance of the EagleEnclosureFactory (Singleton)
     * Will create a new instance on first call
     * synchronized keyword allows us to keep it a singleton even when using multiple threads
     * @return The EagleEnclosureFactory instance
     */
    public static synchronized EagleEnclosureFactory getInstance() {
        if (instance == null) {
            instance = new EagleEnclosureFactory();
        }
        return instance;
    }

    /**
     * Allows to generate animals to populate the created enclosure
     * Will be called if the createEnclosure method of the Factory is called with the true parameter, which represents the "Do you want to populate the enclosure you are creating ?"
     * @param enclosure The enclosure in which this method will generate some population (basically a newly created enclosure)
     */
    public void generateRandomPopulation(Enclosure<Eagle> enclosure) {
        for (int i = 0; i < enclosure.getMaxAnimals() / 2; i++) {
            AnimalFactory animalFactory = AnimalFactory.getInstance();
            try {
                enclosure.add(animalFactory.createEagle());
            } catch (Exception e) {
                View.displayErrorMessage("An error occurred while trying to add an animal to an enclosure : " + e.getMessage());
            }
        }
    }

}
