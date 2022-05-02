package mif.vu.lt.TP01.services;

import mif.vu.lt.TP01.entities.Course;
import mif.vu.lt.TP01.entities.University;

import java.util.List;


public interface CourseService {

    Course createCourse(Course course, long universityId, List<Long> studentIds);
    List<Course> getAllCourses();
    void saveCourse(Course course, long universityId, List<Long> studentIds);
    Course getCourseById(long id);
    void updateCourse (Course course);
    void deleteCourseById(long id);
}
