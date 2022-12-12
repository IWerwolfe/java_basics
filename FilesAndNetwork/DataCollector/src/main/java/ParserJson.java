import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ParserJson extends Parser {
    public ParserJson() {
        super();
    }

    public ParserJson(TreeSet<Line> lines, TreeSet<Station> stations) {
        super(lines, stations);
    }

    @Override
    void dataParser(String path) {

        ArrayList<File> files = FileSearch.getFileList(path, ".JSON");
        files.forEach(file -> {

            JSONParser jsonParser = new JSONParser();

            try {
                JSONArray stationArray = (JSONArray) jsonParser.parse(getStringFile(file.getAbsolutePath()));
                stationArray.forEach(this :: parseStation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void parseStation(Object objectStation){

        JSONObject jsonObject  = (JSONObject) objectStation;
        String name = ((String) jsonObject.get("station_name")).trim();
        double depth = getDepth((String) jsonObject.get("depth"));

        ArrayList<Station> stationsArray = getStation(name);

        if (stationsArray.size() == 1){
            Station station = stationsArray.get(0);
            station.setDepth(Math.min(station.getDepth(), depth));
        }

        for (Station station : stationsArray) {
            if (station.getDepth() == depth){
                return;
            }
            if (station.getDepth() == 0.0){
                station.setDepth(depth);
                return;
            }
        }
        super.addStation(new Station(name, depth));
    }

    private double getDepth(String jsonObjectDepth){
        String depth = jsonObjectDepth.trim();
        depth = depth.equals("?") ? "0.0" : depth.replaceAll(",", ".");
        return Double.parseDouble(depth);
    }

    private String getStringFile(String fileName) throws IOException {
        StringBuilder builder = new StringBuilder();
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        lines.forEach(builder :: append);

        return builder.toString();
    }
}
