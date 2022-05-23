package mif.vu.lt.TP01.controllers;

import lombok.Getter;
import lombok.Setter;
import mif.vu.lt.TP01.entities.Course;
import mif.vu.lt.TP01.services.CourseServiceImpl;
import mif.vu.lt.TP01.services.ITimeConverter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
@Getter
@Setter
public class CourseController implements Serializable {

    @Inject
    private CourseServiceImpl courseService;

    @Inject
    private ITimeConverter timeConverter;

    @Setter
    @Getter
    private Course course = new Course();
    private long universityId;
    private List<Long> studentIds;

    @Transactional
    public String createCourse() {
        int time = timeConverter.minsToHours(course.getHours());
        course.setHours(time);
        courseService.saveCourse(course, universityId, studentIds);
        course = new Course();
        return "index?faces-redirect=true";
    }

    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}
