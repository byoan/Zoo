package animals;

public class Wolf extends Animal implements Mammal {

    public Wolf() {
        this.specieName = "Wolf";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
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
