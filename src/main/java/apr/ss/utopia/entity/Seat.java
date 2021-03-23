package apr.ss.utopia.entity;

import java.util.Objects;

public class Seat {

    public static final String NAME = "seats";
    public static final String ID = "id";
    public static final String TYPE = "seat_type";
    public static final String AIRPLANE = "airplane_type";
    public static final String CAPACITY = "slots";
    public static final String RESERVED = "reserved_seats";

    private Integer id;
    private SeatType type;
    private AirplaneType airplaneType;
    private Integer capacity;
    private Integer reserved;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SeatType getType() {
        return type;
    }

    public void setType(SeatType type) {
        this.type = type;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public Integer getCapacity() {
        return capacity < 1? 0:capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getReserved() {
        return reserved;
    }

    public Integer getAvailable(){
        return capacity - reserved;
    }

    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return id.equals(seat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
