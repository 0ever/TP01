package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.util.Random;

@Specializes
@ApplicationScoped
public class StudentPersonalIdGeneratorSpec extends StudentPersonalIdGenerator{
    public String generateId(Student student) {
        try {
            String studentPersonalId = student.getFname().substring(0, 1);
            studentPersonalId += student.getLname().substring(0, 1);
            studentPersonalId += new Random().nextInt(9999);

            Thread.sleep(5000);

            return studentPersonalId.toUpperCase();
        } catch (InterruptedException e) {
            return null;
        }
    }
}
