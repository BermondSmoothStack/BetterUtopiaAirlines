package apr.ss.utopia.entity;

import java.util.Objects;

public class SeatType {

    public static final String NAME = "seat_type";
    public static final String TYPE_NAME = "name";
    public static final String ID = "id";

    public static final String FIRST_CLASS = "first";
    public static final String BUSINESS_CLASS = "business";
    public static final String ECONOMY_CLASS = "economy";

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatType seatType = (SeatType) o;
        return id.equals(seatType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
