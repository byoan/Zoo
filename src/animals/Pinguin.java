package animals;

public class Pinguin extends Animal implements MarineAnimal, Oviparous, FlyingAnimal {

    public Pinguin() {
        this.specieName = "Pinguin";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
    }

    public Pinguin(boolean sex, float weight, float size, int age) {
        this.specieName = "Pinguin";
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
    public void scream() {

    }

    @Override
    public void heal() {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void wake() {

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

    @Override
    public void fly() {

    }
}
