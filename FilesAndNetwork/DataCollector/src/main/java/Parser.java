import java.util.ArrayList;
import java.util.TreeSet;

public abstract class Parser {

    private TreeSet<Line> lines;
    private TreeSet<Station> stations;

    public Parser() {
        this.lines = new TreeSet<>();
        this.stations = new TreeSet<>();
    }

    public Parser(TreeSet<Line> lines, TreeSet<Station> stations) {
        this.lines = lines;
        this.stations = stations;
    }

    public TreeSet<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        this.lines.add(line);
    }

    public TreeSet<Station> getStations() {
        return stations;
    }

    public ArrayList<Station> getStation(String stationName){
        ArrayList<Station> stationsList = new ArrayList<>();
        stations.stream().filter(s -> s.getName().compareToIgnoreCase(stationName) == 0).forEach(stationsList :: add);
        return stationsList;
    }

    public ArrayList<Station> getStation(Station station){
        ArrayList<Station> stationsList = new ArrayList<>();
        stations.stream().filter(s -> s.equals(station)).forEach(stationsList :: add);
        return stationsList;
    }

    public boolean isStation(String stationName){
        return getStation(stationName).size() > 0;
    }

    public boolean isStation(Station station) {
        return getStation(station).size() > 0;
    }

    public void addStation(Station station) {
        this.stations.add(station);
    }

    public int getCountStation() {
        return stations.size();
    }

    public int getCountLine() {
        return lines.size();
    }

    abstract void dataParser(String path) throws Exception;

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String separator = System.lineSeparator();
        stations.forEach(s -> stringBuilder.append(s.toString()).append(separator));
        return stringBuilder.toString();
    }
}
