package parking;

public class EncapsulationDemo {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle("TN01AB1234", "Car");

        System.out.println("Vehicle Details:");
        vehicle.displayDetails();

        System.out.println("\nUpdating vehicle type...");
        vehicle.setVehicleType("Electric Car");

        System.out.println("\nUpdated Details:");
        vehicle.displayDetails();

        System.out.println("\nTesting invalid value:");
        vehicle.setRegistrationNumber("");
    }
}