import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double bassPricePerDay;
    private boolean isAvailable;

    //create a parameterized constructor
    public Car(String carId, String brand, String model, double bassPricePerDay) {
        //valur assign
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.bassPricePerDay = bassPricePerDay;
        //new carr add that why it true
        this.isAvailable = true;
    }

    //no.6to 10 in all ofe this private that why we use geter methode to exit all data member
    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return bassPricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }
}
    class Customer{
        private String customerId;
        private String name;
        private int PhNumber;

        public Customer(String customerId, String name){
            this.customerId = customerId;
            this.name = name;
            this.PhNumber = PhNumber;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getName() {
            return name;
        }
        public int getPhNumber() {
            return PhNumber;
        }
    }
    class Rental{
        private Car car;
        private Customer customer;
        private int days;
        //Constructors
        public Rental(Car car, Customer customer,int day){
            this.car = car;
            this.customer = customer;
            this. days = days;
        }
        public Car getCar() {
            return car;
        }

        public Customer getCustomer() {
            return customer;
        }

        public int getDays() {
            return days;
        }

    }
    class CarRentalSystem {
        private List<Car> cars;
        private List<Customer> customers;
        private List<Rental> rentals;

        public CarRentalSystem(){
            cars = new ArrayList<>();
            customers = new ArrayList<>();
            rentals = new ArrayList<>();
        }
        public void addCar(Car car){
            cars.add(car);
        }

        public void addCustomer(Customer customer){
            customers.add(customer);
        }
        public void rentCar(Car car,Customer customer,int day){
            if (car.isAvailable()){
                car.rent();
                rentals.add(new Rental(car,customer,day));
            }else {
                System.out.println("Car is not available for rent.");
            }
        }
        public void returnCar(Car car){
            car.returnCar();
            Rental rentalToRemove = null;

            for(Rental rental : rentals){
                if(rental.getCar() == car){
                    rentalToRemove = rental;
                    //here assign rentalToRemove and print
                    break;
                }
            }
            if(rentalToRemove != null){
                rentals.remove(rentalToRemove);
            } else {
                System.out.println("Car was not rented.");
            }
        }
        public void menu(){
            Scanner scanner= new Scanner(System.in);
             while (true){
                 System.out.println("=====Car Rental System====");
                 System.out.println("1.Rent a Car");
                 System.out.println("2.Return a car");
                 System.out.println("3.Exit");
                 System.out.println("Enter your choice:   ");
                 int choice = scanner.nextInt();
                 scanner.nextLine();//consume newline
                 if(choice==1){
                     System.out.println("\n== Rent a Car ==\n");
                     System.out.println("Enter your name:  ");
                     String customerName = scanner.nextLine();
                     System.out.println("Enter your PhoneNo.:  ");
                     String customerPhoneNo = scanner.nextLine();
                     System.out.println("\nAvailable Cars: ");
                     System.out.println("\nCarId - Brand  - Model ");




                     for(Car car : cars){
                         if(car.isAvailable()){
                             System.out.println(car.getCarId()+ " - " + car.getBrand() + "    "+ car.getModel());
                         }
                     }
                     System.out.print("\nEnter the car ID you want to rent: ");
                     String carId = scanner.nextLine();
                     System.out.print("Enter the number of days for rental: ");
                     int rentalDays = scanner.nextInt();
                     scanner.nextLine();//consume newline

                      Customer newCustomer = new Customer("CUS"+ (customers.size() + 1), customerName);
                      addCustomer(newCustomer);
                       Car selectedCar = null;
                       for(Car car: cars ){
                           if(car.getCarId().equals(carId) && car.isAvailable()){
                               selectedCar = car;
                               break;
                           }
                       }
                       if(selectedCar != null){
                           double totalPrice = selectedCar.calculatePrice(rentalDays);
                           System.out.println("\n== Rental information ==\n");
                           System.out.println("Customer ID:" + newCustomer.getCustomerId());
                           System.out.println("customer Name: " + newCustomer.getName());
                           System.out.println("customer PhoneNo.: " + newCustomer.getPhNumber());
                           System.out.println("Car:"+ selectedCar.getBrand()+" "+ selectedCar.getModel());
                           System.out.println("Rental Days: "+ rentalDays);
                           System.out.printf("Total Price: $%.2f%n",totalPrice);

                           System.out.print("\nConfirm rental (Y/N): ");
                           String confirm = scanner.nextLine();

                           if(confirm.equalsIgnoreCase("Y")){
                               rentCar(selectedCar , newCustomer, rentalDays);
                               System.out.println("\nCar rental successfully.");
                           }else {
                               System.out.println("\nRental canceled.");
                 }


             }
                       else {
                           System.out.println("\nInvalid car selection or not available for rent. ");
                     }

                    }else if (choice == 2){
                     System.out.println("\n== Return a Car ==\n");
                     System.out.print("Enter the car IdD you want to return:  ");
                     String carId = scanner.nextLine();
                     Car carToReturn = null;
                     for(Car car: cars){
                         if (car.getCarId().equals(carId) && !car.isAvailable()) {
                         carToReturn = car;
                         break;
                     }
                     }
                          if (carToReturn != null) {
                             Customer customer = null;
                             for (Rental rental : rentals) {
                                 //we chake car exist or not
                                 if (rental.getCar() == carToReturn) {
                                     customer = rental.getCustomer();
                                     break;
                                 }
                             }

                             if (customer != null) {
                                 returnCar(carToReturn);
                                 System.out.println("Car returned successfully by " + customer.getName());
                                } else{
                                 System.out.println("Car was not rented or rental information is missing.");
                               }
                               } else {
                              System.out.println("Invalid car ID or car is not rented.");
                            }
                          } else if (choice == 3) {
                         break;
                        } else {
                         System.out.println("Invalid choice. Please enter a valid option.");
                     }
                 }

                 System.out.println("\nThank you for using the Car Rental System!");
             }

        }

public class Main {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001", "Toyota", "Camry", 60.0); // Different base price per day for each car
        Car car2 = new Car("C002", "Honda", "Accord", 70.0);
        Car car3 = new Car("C003", "Mahindra", "Thar", 150.0);
        Car car4 = new Car("C004", "Mahindra", "BE 6", 90.0);
        Car car5 = new Car("C005", "Maruti", "Ertiga", 70.0);
        Car car6 = new Car("C006", "Maruti", "FRONX", 75.0);
        Car car7 = new Car("C007", "Toyota", "Rumion", 110.0);
        rentalSystem.addCar(car1);
        rentalSystem.addCar(car2);
        rentalSystem.addCar(car3);
        rentalSystem.addCar(car4);
        rentalSystem.addCar(car5);
        rentalSystem.addCar(car6);
        rentalSystem.addCar(car7);

        rentalSystem.menu();
    }

}



