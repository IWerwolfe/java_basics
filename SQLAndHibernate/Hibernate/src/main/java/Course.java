import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int duration;

    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "enum")
    private CoursesType type;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @Column(name = "students_count")
    private int studentsCount;
    private Integer price;
    @Column(name = "price_per_hour")
    private float pricePerHour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Course course = (Course) o;
        return id != null && Objects.equals(id, course.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
