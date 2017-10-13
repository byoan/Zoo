package animals;

abstract class Animal implements AnimalInterface {

    protected String specieName;
    protected boolean sex; // True if male
    protected float weight;
    protected float size;
    protected int age;
    protected int hungerIndicator;
    protected boolean sleepIndicator;
    protected int healthIndicator; // Percentage
    protected int childrenCreationTime;
    protected boolean isInEnclosure = false;
}
