import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class DataAccelerator {

    private TreeSet<Line> lines;
    private TreeSet<Station> stations;
    private String pathHtml;
    private String pathCsv;
    private String pathFile;
    private String pathOut;
    private final String fileNameStations = "stations.json";
    private final String fileNameLines = "lines.json";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public DataAccelerator(String pathHtml, String pathCsv, String pathFile) {
        this.pathHtml = pathHtml;
        this.pathCsv = pathCsv;
        this.pathFile = pathFile;

        lines = new TreeSet<>();
        stations = new TreeSet<>();
    }

    public void save(String pathOut) throws Exception {

        Parser parserHttp = new ParserHtml(lines, stations);
        Parser parserJson = new ParserJson(lines, stations);
        Parser parserCsv = new ParserCsv(lines, stations);

        parserHttp.dataParser(pathHtml);
        parserCsv.dataParser(pathCsv);
        parserJson.dataParser(pathFile);

        JSONArray stationsJson = new JSONArray();
        stations.forEach(s -> stationsJson.add(getStation(s)));

        JSONObject fileStations = new JSONObject();
        fileStations.put("stations", stationsJson);
        saveFile(getFileName(pathOut, fileNameStations), fileStations);

        JSONArray linesJson = new JSONArray();
        lines.forEach(s -> linesJson.add(getLine(s)));

        JSONObject fileLine = new JSONObject();
        fileLine.put("lines", linesJson);
        saveFile(getFileName(pathOut, fileNameLines), fileLine);

        System.out.println("Файлы успешно сохранены");
    }

    private void saveFile(String pathFile, JSONObject data) throws IOException {
        FileWriter writer = new FileWriter(pathFile);
        writer.write(data.toJSONString());
        writer.flush();
        writer.close();
    }

    private String getFileName(String path, String fileName){
        String regex = "^[^\\.].+/$";
        if (!path.matches(regex)) {
            path += "/";
        }
        return path + fileName;
    }

    private JSONObject getLine(Line line){
        JSONObject lineJson = new JSONObject();
        lineJson.put("name", JSONObject.escape(line.getName()));
        lineJson.put("number", JSONObject.escape(line.getNumber()));
        return lineJson;
    }

    private JSONObject getStation(Station station){

        JSONObject stationJson = new JSONObject();
        stationJson.put("name", JSONObject.escape(station.getName()));
        if (station.getLine() != null) {
            stationJson.put("line", JSONObject.escape(station.getLine().getName()));
        }
        if (station.getOpenData() != null) {
            stationJson.put("date", JSONObject.escape(station.getOpenData().format(formatter)));
        }
        stationJson.put("depth", station.getDepth());
        stationJson.put("hasConnection", station.isHasConnection());

        return stationJson;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        String separator = System.lineSeparator();
        stations.forEach(s -> stringBuilder.append(s.toString()).append(separator));
        return stringBuilder.toString();
    }
}
