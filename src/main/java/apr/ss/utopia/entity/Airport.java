package apr.ss.utopia.entity;

import java.util.List;
import java.util.Objects;

public class Airport {

    public static final String NAME = "airport";
    public static final String CODE = "iata_id";
    public static final String CITY = "city";

    private String airportCode;
    private String city;
    private List<Route> routes;

    public String getAirportCode() {
        return null == airportCode ? "" : airportCode;
    }

    public void setAirportCode(String airportCode) {
        if (airportCode.length() < 3) {
            airportCode += "00";
        }
        this.airportCode = airportCode.substring(0, 3).toUpperCase();
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return airportCode + " " + city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airport airport = (Airport) o;
        return airportCode.equals(airport.airportCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(airportCode);
    }
}