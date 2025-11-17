package Model;

public class Booking {
    private User user;
    private Property property;
    private int daysBooked;
    private double totalPrice;

    public Booking(Property property, User user, int days) {
        if (!property.isAvailable()) {
            throw new IllegalStateException("Property is not available!");
        }
        if (days <= 0) {
            throw new IllegalArgumentException("Days must be greater than 0");
        }

        this.property = property;
        this.user = user;
        this.daysBooked = days;
        this.totalPrice = property.getPrice() * days;

        property.setAvailable(false);
    }

    public User getUser() {
        return user;
    }

    public Property getProperty() {
        return property;
    }

    public int getDays() {
        return daysBooked;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // toString method
    public String toString() {
        return "Booking Details:" +
                "\nUserID: " + user.getUserID() +
                "\nProperty: " + property.getName() +
                "\nDays: " + daysBooked +
                "\nTotal Cost: $" + totalPrice;
    }
}
