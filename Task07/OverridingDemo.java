class Vehicle {

    private final String registrationNumber;

    public Vehicle(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    // This method will be overridden by subclasses
    public double calculateFee(int hours) {
        return hours * 20.0;
    }

    @Override
    public String toString() {
        return "Vehicle Registration Number: " + registrationNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vehicle) {
            Vehicle other = (Vehicle) obj;
            return registrationNumber.equals(other.registrationNumber);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return registrationNumber.hashCode();
    }
}

class Car extends Vehicle {

    public Car(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 30.0;
    }

    @Override
    public String toString() {
        return "Car Registration Number: " + getRegistrationNumber();
    }
}

class TwoWheeler extends Vehicle {

    public TwoWheeler(String registrationNumber) {
        super(registrationNumber);
    }

    @Override
    public double calculateFee(int hours) {
        return hours * 10.0;
    }

    @Override
    public String toString() {
        return "TwoWheeler Registration Number: " + getRegistrationNumber();
    }
}

public class OverridingDemo {

    public static void main(String[] args) {

        Vehicle car = new Car("TN01AB1234");
        Vehicle bike = new TwoWheeler("TN02XY5678");

        System.out.println(car);
        System.out.println("Car Parking Fee for 2 hours: Rs "
                + car.calculateFee(2));

        System.out.println();

        System.out.println(bike);
        System.out.println("TwoWheeler Parking Fee for 2 hours: Rs "
                + bike.calculateFee(2));

        System.out.println();

        Vehicle car1 = new Car("TN01AB1234");

        System.out.println("Are car and car1 equal? "
                + car.equals(car1));
    }
}