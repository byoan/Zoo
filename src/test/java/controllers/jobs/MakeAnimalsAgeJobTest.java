package controllers.jobs;

import controllers.zoo.Zoo;
import models.animals.Bear;
import models.employees.Employee;
import models.enclosures.Enclosure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MakeAnimalsAgeJobTest {

    private int turnNb;
    private Zoo zoo;

    @BeforeEach
    void setUp() {
        this.turnNb = 1;
        this.zoo = new Zoo("Tests", Employee.getInstance(), 50);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getZoo() {
        MakeAnimalsAgeJob makeAnimalsAgeJob = new MakeAnimalsAgeJob(this.zoo, this.turnNb);
        assertEquals(this.zoo, makeAnimalsAgeJob.getZoo());
    }

    @Test
    void getTurnNb() {
        MakeAnimalsAgeJob makeAnimalsAgeJob = new MakeAnimalsAgeJob(this.zoo, this.turnNb);
        assertEquals(this.turnNb, makeAnimalsAgeJob.getTurnNb());
    }

    @Test
    void run() {
        Zoo zoo = new Zoo("zoo", Employee.getInstance(), 50);
        Enclosure<Bear> enclosure = new Enclosure<Bear>("Name", 10, 10);
        Bear bear = new Bear();

        enclosure.add(bear);
        zoo.getEnclosureList().add(enclosure);

        MakeAnimalsAgeJob job = new MakeAnimalsAgeJob(zoo, 1);
        job.run();

        assertEquals(2, bear.getAge());
    }
}