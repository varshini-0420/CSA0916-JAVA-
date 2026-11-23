package parking;

public class Vehicle {

    private String registrationNumber;
    private String vehicleType;

    public Vehicle(String registrationNumber, String vehicleType) {
        setRegistrationNumber(registrationNumber);
        setVehicleType(vehicleType);
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            System.out.println("Invalid registration number.");
        } else {
            this.registrationNumber = registrationNumber;
        }
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        if (vehicleType == null || vehicleType.trim().isEmpty()) {
            System.out.println("Invalid vehicle type.");
        } else {
            this.vehicleType = vehicleType;
        }
    }

    public void displayDetails() {
        System.out.println("Registration Number: " + registrationNumber);
        System.out.println("Vehicle Type: " + vehicleType);
    }
}