package mif.vu.lt.TP01.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Course.findAll", query = "select c from Course c"),
        @NamedQuery(name = "Course.findById", query = "select c from Course as c where c.id = :id")
})
@Table
@Getter
@Setter
@NoArgsConstructor
public class Course {
    public Course(long id, String courseName, float credits, int hours, University university) {
        this.id = id;
        this.courseName = courseName;
        this.credits = credits;
        this.hours = hours;
        this.university = university;
    }

    public Course(long id, String courseName, float credits, int hours) {
        this.id = id;
        this.courseName = courseName;
        this.credits = credits;
        this.hours = hours;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "credits", nullable = false)
    private float credits;

    @Column(name = "hours", nullable = false)
    private int hours;

    @ManyToOne
    @JoinColumn(name= "university_id")
    private University university;

    @ManyToMany(mappedBy = "attendingCourses")
    List<Student> studentSet;

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Course o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
