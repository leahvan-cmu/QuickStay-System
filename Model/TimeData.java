package Model;

public class TimeData {
    private int month;
    private int day;
    private int year;

    public TimeData(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    // Getters & Setters
    public int getMonth() {
        return month;  }
    public void setMonth(int month) {
        this.month = month;  }

    public int getDay() {
        return day;  }
    public void setDay(int day) {
        this.month = month;  }

    public int getYear() {
        return year;  }
      public void setYear(int year) {
        this.year = year;  }


    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }
}



