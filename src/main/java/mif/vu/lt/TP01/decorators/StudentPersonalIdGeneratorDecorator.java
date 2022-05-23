package mif.vu.lt.TP01.decorators;

import mif.vu.lt.TP01.entities.Student;
import mif.vu.lt.TP01.services.IStudentPersonalIdGenerator;
import mif.vu.lt.TP01.services.StudentPersonalIdGenerator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class StudentPersonalIdGeneratorDecorator implements IStudentPersonalIdGenerator {
    @Delegate
    @Any
    @Inject
    public StudentPersonalIdGenerator studentPersonalIdGenerator;

    @Override
    public String generateId(Student student) {
        if (student.getPersonalId() == null || student.getPersonalId().isEmpty()) {
            return studentPersonalIdGenerator.generateId(student);
        }

        throw new IllegalArgumentException("Student already has an ID");
    }
}
