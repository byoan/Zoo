package animals;

public class Whale extends Animal implements MarineAnimal, Mammal {

    public Whale() {
        this.specieName = "Whale";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
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
    }
    @Override
    public void eat() {

    }

    @Override
    public void sound() {

    }

    @Override
    public void heal() {

    }

    @Override
    public void sleepAction() {

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
