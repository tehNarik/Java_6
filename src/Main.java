import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створюємо кілька об'єктів Car
        Car car1 = new Car("Toyota Camry", 30000, 8.5, 210);
        Car car2 = new Car("Honda Accord", 28000, 7.5, 200);
        Car car3 = new Car("Ford Focus", 25000, 6.5, 190);

        // Створюємо новий LinkedList і додаємо автомобілі
        LinkedList carList = new LinkedList();
        carList.add(car1);
        carList.add(car2);
        carList.add(car3);

        // Виводимо розмір списку
        System.out.println("Розмір списку: " + carList.size());

        // Виводимо дані автомобілів
        System.out.println("Автомобілі у списку:");
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i); // Отримуємо автомобіль за індексом
            System.out.println(car.getModel() + " - Ціна: " + car.getPrice() + ", Витрати палива: " + car.getFuelConsumption() + " л/100 км, Макс. швидкість: " + car.getMaxSpeed() + " км/год");
        }

        // Видаляємо один автомобіль
        carList.remove(car2);
        System.out.println("Після видалення Honda Accord:");

        // Виводимо розмір списку після видалення
        System.out.println("Розмір списку: " + carList.size());

        // Виводимо залишені автомобілі
        for (int i = 0; i < carList.size(); i++) {
            Car car = carList.get(i); // Отримуємо автомобіль за індексом
            System.out.println(car.getModel() + " - Ціна: " + car.getPrice() + ", Витрати палива: " + car.getFuelConsumption() + " л/100 км, Макс. швидкість: " + car.getMaxSpeed() + " км/год");
        }
    }
}
