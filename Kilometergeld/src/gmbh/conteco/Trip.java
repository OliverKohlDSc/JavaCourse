package gmbh.conteco;

import java.time.LocalDate;

public class Trip {
    private String licensePlate;
    private String driver;
    private LocalDate date;
    private long kmAtStart;
    private long kmAtEnd;
    private long kmDriven;
    private boolean isBusinessTrip;
    private String purpose;
    private String route;

    public Trip(String licensePlate, String driver, LocalDate date, long kmAtStart, long kmAtEnd, long kmDriven, boolean isBusinessTrip, String purpose, String route) {
        this.licensePlate = licensePlate;
        this.driver = driver;
        this.date = date;
        this.kmAtStart = kmAtStart;
        this.kmAtEnd = kmAtEnd;
        this.kmDriven = kmDriven;
        this.isBusinessTrip = isBusinessTrip;
        this.purpose = purpose;
        this.route = route;
    }

    public static Trip importNewFromCSV(String line) throws IllegalArgumentException{
        String[] input = line.split(",");
        String licensePlate = input[0].trim();
        String driver = input[1].trim();
        LocalDate date = LocalDate.parse(input[2].trim());
        long kmAtStart = Long.parseLong(input[3].trim());
        long kmAtEnd = Long.parseLong(input[4].trim());
        long kmDriven = Long.parseLong(input[5].trim());
        boolean isBusinessTrip = Boolean.valueOf(input[6].trim());
        String purpose = input[7].trim();
        String route = input[8].trim();

        return new Trip(licensePlate, driver, date, kmAtStart, kmAtEnd, kmDriven, isBusinessTrip, purpose, route);
    }

    public String exportToCSV() {
        StringBuilder sb = new StringBuilder();
        sb.append(licensePlate + ",");
        sb.append(driver + ",");
        sb.append(date + ",");
        sb.append(kmAtStart + ",");
        sb.append(kmAtEnd + ",");
        sb.append(kmDriven + ",");
        sb.append(isBusinessTrip + ",");
        sb.append(licensePlate + ",");
        sb.append(purpose + ",");
        sb.append(route);
        return sb.toString();
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    public String getDriver() {
        return driver;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getDateAsString() {
        return date.toString();
    }
    public long getKmAtStart() {
        return kmAtStart;
    }
    public long getKmAtEnd() {
        return kmAtEnd;
    }
    public long getKmDriven() {
        return kmDriven;
    }
    public boolean isBusinessTrip() {
        return isBusinessTrip;
    }
    public String getIsBusinessTrip() {
        return isBusinessTrip ? "Yes" : "No";
    }
    public String getPurpose() {
        return purpose;
    }
    public String getRoute() {
        return route;
    }
}

