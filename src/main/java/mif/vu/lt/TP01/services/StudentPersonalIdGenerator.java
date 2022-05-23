package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class StudentPersonalIdGenerator implements IStudentPersonalIdGenerator, Serializable {

    public String generateId(Student student) {
        try {
            String studentPersonalId = student.getFname().substring(0, 2);
            studentPersonalId += student.getLname().substring(0, 2);
            studentPersonalId += new Random().nextInt(9999);

            Thread.sleep(5000);

            return studentPersonalId.toUpperCase();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
