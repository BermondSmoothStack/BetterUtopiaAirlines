package apr.ss.utopia.entity;

import java.util.Objects;

public class Airplane {
    public static final String NAME = "airplane";
    public static final String ID = "id";
    public static final String TYPE = "type_id";

    private Integer id;
    private AirplaneType type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AirplaneType getType() {
        return type;
    }

    public void setType(AirplaneType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Airplane airplane = (Airplane) o;
        return id.equals(airplane.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
