package animals;

public class Fish extends Animal implements MarineAnimal, Oviparous {
    public Fish() {
        this.specieName = "Fish";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
    }

    public Fish(boolean sex, float weight, float size, int age) {
        this.specieName = "Fish";
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
    public void lay() {
        if (this.sex == true) {
            return;
        }
    }
}
