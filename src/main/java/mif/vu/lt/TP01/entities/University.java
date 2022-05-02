package mif.vu.lt.TP01.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "University.findAll", query = "select u from University u"),
        @NamedQuery(name = "University.findById", query = "select u from University as u where u.id = :id")
})
@Table
@Getter
@Setter
@NoArgsConstructor
public class University {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "rank", nullable = false)
    private int rank;

    @OneToMany(mappedBy="university")
    private List<Student> studentSet;

    @OneToMany(mappedBy="university")
    private List<Course> courseSet;


    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof University o)) {
            return false;
        }
        return o.id == this.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
