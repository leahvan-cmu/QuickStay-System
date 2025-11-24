package Service;

import java.util.ArrayList;
import java.util.List;
import Model.Property;
import Model.User;
import Model.Booking;

public class BookingService {
    private List<Booking> bookings = new ArrayList<>();

    /*
     * Creates a new booking for a property by a user for a specified number of
     * days.
     */
    public Booking createBooking(Property property, User user, int days) {
        Booking booking = new Booking(property, user, days);
        bookings.add(booking);
        return booking;
    }

    /*
     * Returns a list of all bookings made.
     */
    public List<Booking> getAllBookings() {
        return bookings;
    }

    /*
     * Returns a list of bookings made by a specific user.
     */
    public List<Booking> getBookingsByUser(User user) {
        List<Booking> userBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getUser().equals(user)) {
                userBookings.add(booking);
            }
        }
        return userBookings;
    }

}
