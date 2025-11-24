package Model;

public class FilterData {
  private double minPrice;
  private double maxPrice;
  private int minRooms;
  private int maxRooms;
  private double minBath;
  private double maxBath;
  private String city;
  private boolean vacancy;
  private String type;

  public FilterData(double minPrice, double maxPrice, int minRooms, int maxRooms, double minBath, double maxBath, String city, boolean vacancy, String type) {
    this.minPrice = minPrice;
    this.maxPrice = maxPrice;
    this.minRooms = minRooms;
    this.maxRooms = maxRooms;
    this.minBath = minBath;
    this.maxBath = maxBath;
    this.city = city;
    this.vacancy = vacancy;
    this.type = type;
  }

  //Getters & Setters
  public double getMinPrice() {
    return minPrice;  }
  public void setMinPrice(double minPrice) {
    this.minPrice = minPrice;  }

  public double getMaxPrice() {
    return maxPrice;  }
  public void setMaxPrice(double maxPrice) {
    this.maxPrice = maxPrice;  }

  public int getMinRooms() {
    return minRooms;  }
  public void setMinRooms(int minRooms) {
    this.minRooms = minRooms;  }

  public int getMaxRooms() {
    return maxRooms;  }
  public void setMaxRooms(int maxRooms) {
    this.maxRooms = maxRooms;  }

  public double getMinBath() {
    return minBath;  }
  public void setMinBath(double minBath) {
    this.minBath = minBath;  }

  public double getMaxBath() {
    return maxBath;  }
  public void setMaxBath(double maxBath) {
    this.maxBath = maxBath;  }

  public String getCity() {
    return city;  }
  public void setCity(String city) {
    this.city = city;  }

  public boolean getVacancy() {
    return vacancy;  }
  public void setVacancy(boolean vacancy) {
    this.vacancy = vacancy; }

    public String getType() {
      return type;  }

    public void setType(String type) {
      this.type = type;  }
}
