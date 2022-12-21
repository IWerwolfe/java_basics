import jakarta.persistence.AttributeConverter;

public class ConverterEnum implements AttributeConverter<CoursesType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CoursesType coursesType) {
        return coursesType == null ? null : coursesType.getId();
    }

    @Override
    public CoursesType convertToEntityAttribute(Integer id) {
        return CoursesType.getType(id);
    }
}
