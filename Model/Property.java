package Model;

public class Property {

    private String name;
    private String address;
    private String city;
    private int bedroom;
    private double bathroom;
    private double price;
    private int stayLength;
    private boolean isAvailable;

    // Property constructors
    public Property(String name, String address, String city, int bedroom, double bathroom, double price, int stayLength, boolean isAvailable) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.bedroom = bedroom;
        this.bathroom = bathroom;
        this.price = price;
        this.stayLength = stayLength;
        this.isAvailable = isAvailable;
    }

    public Property() {
        this.name = "";
        this.address = "";
        this.city = "";
        this.bedroom = 0;
        this.bathroom = 0;
        this.price = 0.0;
        this.stayLength = 0;
        this.isAvailable = true;
    }

    // Getters and setters for name, address, city, bedroom, bathroom, price,
    // stayLength, isAvailable
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public int getBedroom() {
        return bedroom;
    }

    public double getBathroom() {
        return bathroom;
    }

    public double getPrice() {
        return price;
    }

    public int getStayLength() {
        return stayLength;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBedroom(int bedroom) {
        this.bedroom = bedroom;
    }

    public void setBathroom(double bathroom) {
        this.bathroom = bathroom;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStayLength(int stayLength) {
        this.stayLength = stayLength;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // toString method
    public String toString() {
        return "Property Name - " + name + " Address: " + address + ", " + city + ", " + bedroom
                + " Bedrooms " + bathroom + " Bathrooms $" + price + " per night (USD), Vacancy: "
                + isAvailable;

    }
}

