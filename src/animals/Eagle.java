package animals;

public class Eagle extends Animal implements FlyingAnimal, Oviparous {

    public Eagle() {
        this.specieName = "Eagle";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
    }

    public Eagle(boolean sex, float weight, float size, int age) {
        this.specieName = "Eagle";
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
    public void fly() {

    }

    @Override
    public void lay() {
        if (this.sex == true) {
            return;
        }
    }
}
