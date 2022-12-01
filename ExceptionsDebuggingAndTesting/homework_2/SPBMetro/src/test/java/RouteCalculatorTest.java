import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

/*
 *created by WerWolfe on
 */
@DisplayName("Проверка класса RouteCalculator")
public class RouteCalculatorTest extends TestCase {

    private ArrayList<Station> stationList;
    private RouteCalculator routeCalculator;

    @Override
    public void setUp() throws Exception {

        Line line1 = new Line(1, "Первая");
        Line line2 = new Line(2, "Вторая");
        Line line3 = new Line(3, "Третья");

        stationList = new ArrayList<>();
        stationList.add(new Station("Станция 0", line1));
        stationList.add(new Station("Станция 1", line1));
        stationList.add(new Station("Станция 2", line1));
        stationList.add(new Station("Станция 3", line2));
        stationList.add(new Station("Станция 4", line2));
        stationList.add(new Station("Станция 5", line3));
        stationList.add(new Station("Станция 6", line3));

        ArrayList<Station> connection1 = new ArrayList<>();
        connection1.add(stationList.get(2));
        connection1.add(stationList.get(3));

        ArrayList<Station> connection2 = new ArrayList<>();
        connection2.add(stationList.get(4));
        connection2.add(stationList.get(5));

        for (int i = 0; i < 3; ){
            line1.addStation(stationList.get(i++));
        }

        for (int i = 3; i < 5; ){
            line2.addStation(stationList.get(i++));
        }

        line3.addStation(stationList.get(5));
        line3.addStation(stationList.get(6));

        StationIndex stationIndex = new StationIndex();
        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);
        stationIndex.addConnection(connection1);
        stationIndex.addConnection(connection2);

        routeCalculator = new RouteCalculator(stationIndex);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @DisplayName("Проверка расчета времени пути")
    public void testCalculateDuration() {

        double actual = RouteCalculator.calculateDuration(stationList);
        double expected = 17.0;
        assertEquals("Расчет времени пути", actual, expected);

        actual = RouteCalculator.calculateDuration(new ArrayList<>());
        expected = 0;
        assertEquals("Передан пустой список", actual, expected);
    }

    @DisplayName("Проверка вывода станций на одной линии")
    public void testGetShortRouteOnTheLine() {

        List actual;
        final String expected = "[Станция 0, Станция 1, Станция 2]";

        actual = routeCalculator.getShortestRoute(stationList.get(0), stationList.get(2));
        assertEquals(actual.toString(), expected);
    }

    @DisplayName("Проверка вывода станций c одной пересадкой")
    public void testGetShorRouteWithOneConnection() {

        List actual;
        final String expected = "[Станция 0, Станция 1, Станция 2, Станция 3, Станция 4]";

        actual = routeCalculator.getShortestRoute(stationList.get(0), stationList.get(4));
        assertEquals(actual.toString(), expected);
    }

    @DisplayName("Проверка вывода станций с двумя пересадками")
    public void testGetShorRouteWithTwoConnections() {

        List actual;
        final String expected = "[Станция 0, Станция 1, Станция 2, Станция 3, Станция 4, Станция 5, Станция 6]";

        actual = routeCalculator.getShortestRoute(stationList.get(0), stationList.get(6));
        assertEquals(actual.toString(), expected);
    }
}