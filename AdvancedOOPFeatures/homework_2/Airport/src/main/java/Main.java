import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Airport airport = Airport.getInstance();
        List<Flight> lanes = findPlanesLeavingInTheNextTwoHours(airport);

        System.out.println("Список рейсов");
        lanes.stream().forEach(System.out::println);
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

        Date dateFlight = new Date(System.currentTimeMillis() + 60 * 60 * 2 * 1000);
        Date now = new Date();
        ArrayList<Flight> flightList = new ArrayList<>();

        airport.getTerminals()
                .stream().forEach(terminal -> {
                    terminal.getFlights().stream().filter(flight ->
                                    flight.getDate().compareTo(dateFlight) <= 0 &&
                                            flight.getDate().compareTo(now) >= 0 &&
                                            flight.getType() == Flight.Type.DEPARTURE)
                            .forEach(flightList::add);
                });

        return flightList;
    }
}
