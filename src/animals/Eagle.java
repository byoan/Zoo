package animals;

public class Eagle extends Animal implements FlyingAnimal, Oviparous {

    public Eagle() {
        this.specieName = "Eagle";
        this.sex = true;
        this.weight = 30;
        this.size = 1;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 5;
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
    public void fly() {

    }

    @Override
    public Eagle lay() {
        return new Eagle();
    }
}
