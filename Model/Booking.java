package Model;

public class Booking {
    private User user;
    private Property property;
    private int daysBooked;
    private double totalPrice;

    public Booking(Property property, User user, int days) {
        // if the property is not available or if days is less than or equal to 0, throw
        // an exception
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

    // Getters for user, property, daysBooked, totalPrice and setter for daysBooked
    public User getUser() {
        return user;
    }

    public Property getProperty() {
        return property;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getDays() {
        return daysBooked;
    }

    public void setDays(int days) {
        this.daysBooked = days;
    }

    // toString method
    public String toString() {
        return "Booking Details:" +
                "\nUserID: " + user.getUsername() +
                "\nProperty: " + property.getName() +
                "\nDays: " + daysBooked +
                "\nTotal Cost: $" + totalPrice;
    }
}
