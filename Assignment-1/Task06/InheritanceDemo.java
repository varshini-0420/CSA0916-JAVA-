class Vehicle {

    protected String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
        System.out.println("Vehicle constructor executed");
    }

    public void displayVehicle() {
        System.out.println("Registration Number: " + registrationNumber);
    }
}

class Car extends Vehicle {

    public Car(String registrationNumber) {
        super(registrationNumber);
        System.out.println("Car constructor executed");
    }

    public void displayType() {
        System.out.println("Vehicle Type: Car");
    }
}

class TwoWheeler extends Vehicle {

    public TwoWheeler(String registrationNumber) {
        super(registrationNumber);
        System.out.println("TwoWheeler constructor executed");
    }

    public void displayType() {
        System.out.println("Vehicle Type: TwoWheeler");
    }
}

class ElectricCar extends Car {

    public ElectricCar(String registrationNumber) {
        super(registrationNumber);
        System.out.println("ElectricCar constructor executed");
    }

    public void displayElectricCar() {
        System.out.println("Vehicle Type: Electric Car");
    }
}

public class InheritanceDemo {

    public static void main(String[] args) {

        System.out.println("Creating ElectricCar:");
        ElectricCar electricCar = new ElectricCar("TN01EV1234");

        electricCar.displayVehicle();
        electricCar.displayType();
        electricCar.displayElectricCar();

        System.out.println();

        System.out.println("Creating TwoWheeler:");
        TwoWheeler bike = new TwoWheeler("TN02TW5678");

        bike.displayVehicle();
        bike.displayType();
    }
}