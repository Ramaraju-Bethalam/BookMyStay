import java.util.*;

/**
 * Use Case 9: Error Handling & Validation
 */
public class BookMyStay {

    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {
            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // validate booking
            validator.validate(roomType, inventory);

            // if valid, add to queue
            bookingQueue.addRequest(guestName, roomType);

            System.out.println("Booking successful.");

        } catch (InvalidBookingException e) {

            // Handle domain-specific validation errors
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}

/**
 * Custom Exception
 */
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

/**
 * Validator class
 */
class ReservationValidator {

    public void validate(String roomType, RoomInventory inventory)
            throws InvalidBookingException {

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        if (!inventory.isAvailable(roomType)) {
            throw new InvalidBookingException("Room not available.");
        }
    }
}

/**
 * Room Inventory
 */
class RoomInventory {

    private Map<String, Integer> rooms = new HashMap<>();

    public RoomInventory() {
        rooms.put("Single", 2);
        rooms.put("Double", 2);
        rooms.put("Suite", 1);
    }

    public boolean isValidRoomType(String type) {
        return rooms.containsKey(type);
    }

    public boolean isAvailable(String type) {
        return rooms.get(type) > 0;
    }
}

/**
 * Booking Queue
 */
class BookingRequestQueue {

    private Queue<String> queue = new LinkedList<>();

    public void addRequest(String guest, String roomType) {
        queue.add(guest + " - " + roomType);
    }
}
