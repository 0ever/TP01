package mif.vu.lt.TP01.controllers;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.TP01.entities.Student;
import mif.vu.lt.TP01.services.StudentServiceImpl;


@ViewScoped
@Named
@Getter
@Setter
public class StudentController implements Serializable {

    @Inject
    private StudentServiceImpl studentService;

    private Student student = new Student();
    private Long universityId;
    private List<Long> courseIds;
    private Student studentToEdit = new Student();
    private List<Student> students;

    @PostConstruct
    void init() {
        this.students = studentService.getAllStudents();
    }

    @Transactional
    public String createStudent() {
        studentService.saveStudent(student, universityId, courseIds);
        student = new Student();
        return "index?faces-redirect=true";
    }

    @Transactional
    public String updateStudent() {
        studentService.updateStudent(studentToEdit);
        return "index?faces-redirect=true";
    }
}