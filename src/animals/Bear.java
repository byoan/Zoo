package animals;

public class Bear extends Animal implements Mammal {

    public Bear() {
        this.specieName = "Bear";
        this.hungerIndicator = 100;
        this.sleepIndicator = false;
        this.healthIndicator = 100;
    }

    public Bear(boolean sex, float weight, float size, int age) {
        this.specieName = "Bear";
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
    public void birth() {
        if (this.sex == true) {
            return;
        }
    }
}
