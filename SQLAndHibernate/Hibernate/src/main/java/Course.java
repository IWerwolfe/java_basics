import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Integer duration;
//    @Enumerated(EnumType.STRING)
//    @Column(columnDefinition = "enum")
    @Column
    @Convert(converter = ConverterEnum.class)
    private CoursesType type;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL)
    private Teacher teacher;
    @Column(name = "students_count")
    private Integer studentsCount;
    private Double price;
    @Column(name = "price_per_hour")
    private Float pricePerHour;
}
