import java.util.*;

// Car Class
class Car {
    private int id;
    private String brand;
    private String model;
    private String type;
    private double rentalPricePerDay;
    private boolean isAvailable;

    public Car(int id, String brand, String model, String type, double rentalPricePerDay) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.rentalPricePerDay = rentalPricePerDay;
        this.isAvailable = true;
    }

    public int getId() { return id; }

    public String getBrand() { return brand; }

    public String getModel() { return model; }

    public String getType() { return type; }

    public double getRentalPricePerDay() { return rentalPricePerDay; }

    public boolean isAvailable() { return isAvailable; }

    public void setAvailability(boolean isAvailable) { this.isAvailable = isAvailable; }

    public void displayDetails() {
        System.out.println("Car ID: " + id + ", Brand: " + brand + ", Model: " + model +
                ", Type: " + type + ", Price/Day: " + rentalPricePerDay +
                ", Available: " + (isAvailable ? "Yes" : "No"));
    }
}

// Customer Class
class Customer {
    private int id;
    private String name;
    private String phone;

    public Customer(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getPhone() { return phone; }

    public void displayDetails() {
        System.out.println("Customer ID: " + id + ", Name: " + name + ", Phone: " + phone);
    }
}

// Rental Class
class Rental {
    private int rentalId;
    private int customerId;
    private int carId;
    private int rentalDays;
    private double totalCost;

    public Rental(int rentalId, int customerId, int carId, int rentalDays, double totalCost) {
        this.rentalId = rentalId;
        this.customerId = customerId;
        this.carId = carId;
        this.rentalDays = rentalDays;
        this.totalCost = totalCost;
    }

    public void displayDetails() {
        System.out.println("Rental ID: " + rentalId + ", Customer ID: " + customerId + ", Car ID: " + carId +
                ", Days: " + rentalDays + ", Total Cost: " + totalCost);
    }
}

// Main System
public class CarRentalSystem {
    private static List<Car> cars = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Rental> rentals = new ArrayList<>();
    private static int rentalCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample Data
        cars.add(new Car(1, "Toyota", "Camry", "Sedan", 500));
        cars.add(new Car(2, "Honda", "Civic", "Sedan", 450));
        cars.add(new Car(3, "Ford", "Explorer", "SUV", 800));
        cars.add(new Car(4, "Chevrolet", "Impala", "Sedan", 550));
        cars.add(new Car(5, "Nissan", "Altima", "Sedan", 480));
        cars.add(new Car(6, "Jeep", "Wrangler", "SUV", 850));
        cars.add(new Car(7, "BMW", "X5", "SUV", 1000));
        cars.add(new Car(8, "Mercedes", "C-Class", "Sedan", 900));
        cars.add(new Car(9, "Hyundai", "Elantra", "Sedan", 400));
        cars.add(new Car(10, "Kia", "Sportage", "SUV", 700));
        cars.add(new Car(11, "Volkswagen", "Passat", "Sedan", 600));
        cars.add(new Car(12, "Subaru", "Forester", "SUV", 750));
        cars.add(new Car(13, "Mazda", "CX-5", "SUV", 680));
        cars.add(new Car(14, "Audi", "A4", "Sedan", 850));
        cars.add(new Car(15, "Tesla", "Model 3", "Sedan", 1200));

        customers.add(new Customer(1, "Ajith", "9123456780"));
        customers.add(new Customer(2, "Vijay", "9876543210"));

        while (true) {
            System.out.println("\nCar Rental System Menu");
            System.out.println("1. List All Cars");
            System.out.println("2. List Available Cars");
            System.out.println("3. Add New Customer");
            System.out.println("4. Book a Car");
            System.out.println("5. View Rentals");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllCars();
                    break;
                case 2:
                    listAvailableCars();
                    break;
                case 3:
                    addCustomer(scanner);
                    break;
                case 4:
                    bookCar(scanner);
                    break;
                case 5:
                    viewRentals();
                    break;
                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void listAllCars() {
        System.out.println("\nAll Cars:");
        for (Car car : cars) {
            car.displayDetails();
        }
    }

    private static void listAvailableCars() {
        System.out.println("\nAvailable Cars:");
        for (Car car : cars) {
            if (car.isAvailable()) {
                car.displayDetails();
            }
        }
    }

    private static void addCustomer(Scanner scanner) {
        System.out.print("Enter Customer Name: ");
        String name = scanner.next();
        System.out.print("Enter Customer Phone: ");
        String phone = scanner.next();
        int id = customers.size() + 1;
        customers.add(new Customer(id, name, phone));
        System.out.println("Customer added successfully.");
    }

    private static void bookCar(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Car ID: ");
        int carId = scanner.nextInt();
        System.out.print("Enter Rental Days: ");
        int rentalDays = scanner.nextInt();

        Car car = null;
        for (Car c : cars) {
            if (c.getId() == carId && c.isAvailable()) {
                car = c;
                break;
            }
        }

        if (car == null) {
            System.out.println("Car not available or does not exist.");
            return;
        }

        double totalCost = car.getRentalPricePerDay() * rentalDays;
        rentals.add(new Rental(rentalCounter++, customerId, carId, rentalDays, totalCost));
        car.setAvailability(false);
        System.out.println("Car booked successfully. Total Cost: " + totalCost);
    }

    private static void viewRentals() {
        System.out.println("\nRental Records:");
        for (Rental rental : rentals) {
            rental.displayDetails();
        }
    }
}
