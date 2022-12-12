import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class ParserHtml extends Parser {

    private final String regex = "(HTTP||http)s?:\\/\\/.+";
    private final String nameClassLine = "span.js-metro-line";
    private final String nameAttNumLine = "data-line";
    private final String nameClassStationName = "span.name";
    private final int countAttSimpleStation = 2;

    public ParserHtml() {
        super();
    }

    public ParserHtml(TreeSet<Line> lines, TreeSet<Station> stations) {
        super(lines, stations);
    }

    @Override
    void dataParser(String path) throws IllegalAccessException, IOException {

        if (!path.matches(regex)) {
            throw new IllegalAccessException("Не верно указан адрес сайта");
        }

        Document document = Jsoup.connect(path).get();

        document.select(nameClassLine)
                .stream()
                .forEach(element -> {

                    Line line = new Line(element.attr(nameAttNumLine), element.text().trim());
                    addLine(line);

                    parseStations(document
                                    .getElementsByAttributeValue(nameAttNumLine, line.getNumber())
                                    .select("p")
                            , line);
                });
    }

    public void parseStation(Element stationElement, Line line) {

        String name = stationElement.select(nameClassStationName).text().trim();
        boolean isConnection = stationElement.children().size() > countAttSimpleStation;
        ArrayList<Station> stationsArray = getStation(name);

        for (Station station : stationsArray) {
            if (station.getLine() != null && !station.getLine().equals(line) && station.isHasConnection() && isConnection) {
                return;
            }
        }
        super.addStation(new Station(line, name, isConnection));
    }

    private void parseStations(Elements stationsElement, Line line) {
        stationsElement.forEach(element -> parseStation(element, line));
    }
}
