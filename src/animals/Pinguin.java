package animals;

public class Pinguin extends Animal implements MarineAnimal, Oviparous {

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
