package apr.ss.utopia.entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Passenger {

    public static final String NAME = "passenger";
    public static final String ID = "id";
    public static final String BOOKING = "booking_id";
    public static final String GVN_NAME = "given_name";
    public static final String FAM_NAME = "family_name";
    public static final String DOB = "dob";
    public static final String GENDER = "gender";
    public static final String ADDR = "address";

    private Integer id;
    private Booking booking;
    private String givenName;
    private String familyName;
    private Date dob;
    private String gender;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getDob() {
        return dob.toLocalDate();
    }

    public void setDob(LocalDate dob) {
        this.dob = Date.valueOf(dob);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return id.equals(passenger.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
