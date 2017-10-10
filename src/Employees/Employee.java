package Employees;

import Enclosures.Enclosure;

abstract class Employee implements EmployeeInterface {

    protected String name;
    protected boolean sex;  //True if man
    protected int age;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void inspectEnclosure(Enclosure enclosure) {
        if (enclosure != null) {

        }
    }
}

