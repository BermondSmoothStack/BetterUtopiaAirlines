package apr.ss.utopia.entity;

import java.util.List;
import java.util.Objects;

public class AirplaneType {

    public static final String NAME = "airplane_type";
    public static final String ID = "id";
    public static final String CAPACITY = "max_capacity";

    private Integer id;
    private Integer capacity;
    private List<Airplane> airplanes;

    public AirplaneType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Airplane> getAirplanes() {
        return airplanes;
    }

    public void setAirplanes(List<Airplane> airplanes) {
        this.airplanes = airplanes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AirplaneType airplaneType = (AirplaneType) o;
        return id.equals(airplaneType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
