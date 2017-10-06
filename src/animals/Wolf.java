package animals;

public class Wolf extends Animal implements Mammal {

    public Wolf() {
        this.specieName = "Wolf";
        this.sex = true;
        this.weight = 50;
        this.size = 82;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
    }

    public Wolf(boolean sex, float weight, float size, int age) {
        this.specieName = "Wolf";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 69;
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

    public void wander() {
        System.out.println("I'm wandering...");
    }

    @Override
    public void birth() {
        if (this.sex == true) {
            return;
        }
    }
}
