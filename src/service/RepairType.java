package service;

public class RepairType {
    private final String name;
    private final double price;
    private final int estimatedMinutes;

    public RepairType(String name, double price, int estimatedMinutes) {
        this.name = name;
        this.price = price;
        this.estimatedMinutes = estimatedMinutes;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ", " + estimatedMinutes + " min)";
    }
}
