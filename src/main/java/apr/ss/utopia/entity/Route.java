package apr.ss.utopia.entity;

import java.util.Objects;

public class Route {

    public static final String NAME = "route";
    public static final String ID = "id";
    public static final String ORIGIN_CODE = "origin_id";
    public static final String DEST_CODE = "destination_id";

    private Integer id;
    private Airport destinationAirport;
    private Airport originAirport;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Airport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(Airport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public Airport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(Airport originAirport) {
        this.originAirport = originAirport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id.equals(route.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
