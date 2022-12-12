import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NonNull
public class Station implements Comparable<Station>
{
    private Line line;
    private String name;
    private LocalDate openData;
    private double depth;
    private boolean hasConnection;

    public Station(Line line, String name, boolean hasConnection) {
        this.line = line;
        this.name = name;
        this.hasConnection = hasConnection;
    }

    public Station(String name, double depth) {
        this.name = name;
        this.depth = depth;
    }

    public Station(String name, LocalDate openData) {
        this.name = name;
        this.openData = openData;
    }

    @Override
    public int compareTo(Station station)
    {
        if (this.line != null) {
            int lineComparison = line.compareTo(station.getLine());
            if (lineComparison != 0) {
                return lineComparison;
            }
        }
        return name.compareToIgnoreCase(station.getName());
    }

    @Override
    public boolean equals(Object obj)
    {
        return compareTo((Station) obj) == 0;
    }

//    @Override
//    public String toString()
//    {
//        return name;
//    }
}