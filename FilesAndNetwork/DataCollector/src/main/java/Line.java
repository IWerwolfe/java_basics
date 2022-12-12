import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line implements Comparable<Line> {
    private String number;
    private String name;

    @Override
    public int compareTo(Line line) {
        if (line != null) {
            String number = line.getNumber();
            if (this.number.length() == number.length()) {
                return this.number.compareTo(number);
            }
            if (this.number.length() < number.length()) {
                return number.compareTo("0" + this.number);
            }
            return this.number.compareTo("0" + number);
        }
        return -100;
    }

    @Override
    public boolean equals(Object obj) {
        return compareTo((Line) obj) == 0;
    }

    @Override
    public String toString() {
        return new StringBuilder(number).append(". ").append(name).toString();
    }
}