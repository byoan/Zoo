package animals;

public class Fish extends Animal implements MarineAnimal, Oviparous {
    public Fish() {
        this.specieName = "Fish";
        this.sex = true;
        this.weight = 0.10f;
        this.size = 0.01f;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
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
        this.childrenCreationTime = 5;
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
    public void lay() {
        if (this.sex == true) {
            return;
        }
    }
}
