package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Course;
import mif.vu.lt.TP01.entities.Student;
import mif.vu.lt.TP01.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class StudentServiceImpl implements StudentService {

    @Inject
    private EntityManager em;

    @Inject
    private UniversityServiceImpl universityService;

    @Inject
    private CourseServiceImpl courseService;

    @Override
    public void saveStudent(Student student, long universityId, List<Long> courseIds) {
        Student newStudent = createStudent(student, universityId, courseIds);
        this.em.persist(newStudent);
    }

    @Override
    public List<Student> getAllStudents() {
        return this.em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    @Override
    public Student getStudentById(long id) {
        TypedQuery<Student> namedQuery = this.em.createNamedQuery("Student.findById", Student.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    @Override
    public void deleteStudentById(long id) {
        Student s = em.find(Student.class, id);
        em.remove(s);
    }

    public Student createStudent(Student student, long universityId, List<Long> courseIds) {
        University selectedUniversity = universityService.getUniversityById(universityId);
        student.setUniversity(selectedUniversity);
        List<Course> selectedCourses = new ArrayList<>();
        for (Long id : courseIds) {
            selectedCourses.add(courseService.getCourseById(id));
        }
        if (!selectedCourses.isEmpty()) {
            student.setAttendingCourses(selectedCourses);
        }
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        this.em.merge(student);
    }


}
