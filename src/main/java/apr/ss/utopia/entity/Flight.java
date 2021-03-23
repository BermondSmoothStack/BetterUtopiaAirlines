package apr.ss.utopia.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Flight {

    public final static String NAME = "flight";
    public final static String ID = "id";
    public final static String ROUTE = "route_id";
    public final static String AIRPLANE = "airplane_id";
    public final static String DEPARTURE = "departure_time";
    public final static String DURATION = "duration_mins";
    public final static String RESERVED_SEATS = "reserved_seats";
    public final static String SEAT_PRICE = "seat_price";

    private Integer id;
    private Route route;
    private Airplane airplane;
    private LocalDateTime departureTime;
    private Integer duration;
    private Integer reservedSeats;
    private Float seatPrice;
    private List<Seat> seats;
    private Seat firstClass;
    private Seat businessClass;
    private Seat economyClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Float getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(Float seatPrice) {
        this.seatPrice = seatPrice;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Integer getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(Integer reservedSeats) {
        this.reservedSeats = reservedSeats;
    }

    public LocalDateTime getArrivalTime() {
        return departureTime.plusMinutes(duration);
    }

    public String showRoute() {
        return getRoute().getOriginAirport().getCity() +
                " → " +
                getRoute().getDestinationAirport().getCity();
    }

    public Seat getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(Seat firstClass) {
        this.firstClass = firstClass;
    }

    public Seat getBusinessClass() {
        return businessClass;
    }

    public void setBusinessClass(Seat businessClass) {
        this.businessClass = businessClass;
    }

    public Seat getEconomyClass() {
        return economyClass;
    }

    public void setEconomyClass(Seat economyClass) {
        this.economyClass = economyClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id.equals(flight.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
