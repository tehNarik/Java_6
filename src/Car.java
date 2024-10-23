// Клас Car - узагальнений клас для легкових автомобілів
public class Car {
    private String model;
    private double price;
    private double fuelConsumption; // Витрати палива (л/100 км)
    private int maxSpeed;

    public Car(String model, double price, double fuelConsumption, int maxSpeed) {
        this.model = model;
        this.price = price;
        this.fuelConsumption = fuelConsumption;
        this.maxSpeed = maxSpeed;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

}
