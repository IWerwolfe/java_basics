import jakarta.persistence.AttributeConverter;

public class ConverterEnum implements AttributeConverter<CoursesTypeIndex, Integer> {

    @Override
    public Integer convertToDatabaseColumn(CoursesTypeIndex coursesType) {
        return coursesType == null ? null : coursesType.getId();
    }

    @Override
    public CoursesTypeIndex convertToEntityAttribute(Integer id) {
        return CoursesTypeIndex.getType(id);
    }
}
