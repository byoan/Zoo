package animals;

public class Whale extends Animal implements MarineAnimal, Mammal {

    public Whale() {
        this.specieName = "Whale";
        this.sex = true;
        this.weight = 140000;
        this.size = 25;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }

    public Whale(boolean sex, float weight, float size, int age) {
        this.specieName = "Whale";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 330;
    }
    @Override
    public void eat() {

    }

    @Override
    public void scream() {

    }

    @Override
    public void heal() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void swim() {

    }

    @Override
    public void birth() {
        if (this.sex == true) {
            return;
        }
    }
}
