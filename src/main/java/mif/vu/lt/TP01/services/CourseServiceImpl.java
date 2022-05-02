package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Course;
import mif.vu.lt.TP01.entities.Student;
import mif.vu.lt.TP01.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CourseServiceImpl implements CourseService {

    @Inject
    private EntityManager em;

    @Inject
    private UniversityServiceImpl universityService;

    @Inject
    private StudentServiceImpl studentService;

    @Override
    public Course createCourse(Course course, long universityId, List<Long> studentIds) {
        University selectedUniversity = universityService.getUniversityById(universityId);
        course.setUniversity(selectedUniversity);
        List<Student> students = new ArrayList<>();
        if (studentIds != null) {
        for (Long id : studentIds) {
            students.add(studentService.getStudentById(id));
        }
            course.setStudentSet(students);
        }
        return course;
    }

    @Override
    public void saveCourse(Course course, long universityId, List<Long> studentIds) {
        Course newCourse = createCourse(course, universityId, studentIds);
        this.em.persist(newCourse);
    }

    @Override
    public List<Course> getAllCourses() {
        return this.em.createNamedQuery("Course.findAll", Course.class).getResultList();
    }

    @Override
    public Course getCourseById(long id) {
        TypedQuery<Course> namedQuery = this.em.createNamedQuery("Course.findById", Course.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    @Override
    public void updateCourse(Course course) {
        this.em.merge(course);
    }

    @Override
    public void deleteCourseById(long id) {
        University u = em.find(University.class, id);
        em.remove(u);
    }
}
