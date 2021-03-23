package apr.ss.utopia.entity;

import java.util.Objects;

public class Booking {

    public static final String NAME = "booking";
    public static final String ID = "id";
    public static final String ACTIVE = "is_active";
    public static final String CONFIRMATION_CODE = "confirmation_code";

    private Integer id;
    private Boolean isActive;
    private String confirmationCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
