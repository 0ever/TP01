package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Student;

import java.util.List;
import java.util.Set;

public interface StudentService {

    void updateStudent(Student student);
    List<Student> getAllStudents();
    void saveStudent (Student student, long universityId, List<Long> courseIds);
    Student getStudentById(long id);
    void deleteStudentById(long id);
}
