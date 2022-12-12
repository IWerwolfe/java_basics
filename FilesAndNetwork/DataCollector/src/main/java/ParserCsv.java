import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ParserCsv extends Parser {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public ParserCsv() {
        super();
    }

    public ParserCsv(TreeSet<Line> lines, TreeSet<Station> stations) {
        super(lines, stations);
    }

    @Override
    void dataParser(String path) {
        ArrayList<File> files = FileSearch.getFileList(path, ".CSV");
        files.forEach(file -> {
            try {

                List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
                lines.remove(0);
                lines.forEach(this :: parseStation);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
    }

    public void parseStation(String stationString){

        String[] subLine = stationString.split(",");
        if (subLine.length == 0) {
            return;
        }

        String name = subLine[0].trim();
        LocalDate openData = LocalDate.parse(subLine[1], formatter);
        ArrayList<Station> stationsArray = getStation(name);

        for (Station station : stationsArray) {
            if (station.getOpenData() == null){
                station.setOpenData(openData);
                return;
            }
            if (station.getOpenData().compareTo(openData) == 0) {
                return;
            }
        }
        super.addStation(new Station(name, openData));
    }
}
