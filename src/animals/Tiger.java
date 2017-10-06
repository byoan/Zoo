package animals;

public class Tiger extends Animal implements Mammal {

    public Tiger() {
        this.specieName = "Tiger";
        this.sex = true;
        this.weight = 180;
        this.size = 100;
        this.age = 0;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 109;
    }

    public Tiger(boolean sex, float weight, float size, int age) {
        this.specieName = "Tiger";
        this.sex = sex;
        this.weight = weight;
        this.size = size;
        this.age = age;
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
        this.childrenCreationTime = 109;
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
