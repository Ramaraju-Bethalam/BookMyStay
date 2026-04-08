import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 */
public class BookMyStay {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        // confirmed bookings added to history
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        System.out.println("Booking History and Reporting\n");

        System.out.println("Booking History Report");
        for (Reservation r : history.getReservations()) {
            System.out.println(r);
        }
    }
}

/**
 * Reservation class
 */
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Guest: " + guestName + ", Room Type: " + roomType;
    }
}

/**
 * Booking history maintains list of reservations
 */
class BookingHistory {

    private List<Reservation> reservations;

    public BookingHistory() {
        reservations = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
