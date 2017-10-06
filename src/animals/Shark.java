package animals;

public class Shark extends Animal implements MarineAnimal, Oviparous {

    public Shark() {
        this.specieName = "Shark";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
    }

    public Shark(boolean sex, float weight, float size, int age) {
        this.specieName = "Shark";
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
