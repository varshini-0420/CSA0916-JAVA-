import java.util.HashMap;
import java.util.Map;

class ParkingTicket {
    private static int ticketCounter = 1001;

    private final int ticketNumber;
    private final String vehicleNo;
    private final String vehicleType;
    private final int slotNumber;
    private final int entryMinutes;

    public ParkingTicket(String vehicleNo, String vehicleType,
                         int slotNumber, int entryMinutes) {
        this.ticketNumber = ticketCounter++;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.slotNumber = slotNumber;
        this.entryMinutes = entryMinutes;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getEntryMinutes() {
        return entryMinutes;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }
}

public class CompleteParkingSystem {

    private final String[] slots;
    private final Map<String, ParkingTicket> activeTickets =
            new HashMap<>();

    public CompleteParkingSystem(int numberOfSlots) {
        slots = new String[numberOfSlots];
    }

    // Vehicle enters the parking facility
    public boolean enterVehicle(String vehicleNo, String vehicleType,
                                int entryMinutes) {

        // Check duplicate registration number
        if (activeTickets.containsKey(vehicleNo)) {
            System.out.println("Error: Vehicle already parked - "
                    + vehicleNo);
            return false;
        }

        // Validate vehicle type
        if (!vehicleType.equalsIgnoreCase("Car")
                && !vehicleType.equalsIgnoreCase("TwoWheeler")
                && !vehicleType.equalsIgnoreCase("ElectricCar")) {

            System.out.println("Error: Unknown vehicle type");
            return false;
        }

        // Find a free slot
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {

                slots[i] = vehicleNo;

                ParkingTicket ticket = new ParkingTicket(
                        vehicleNo, vehicleType, i, entryMinutes);

                activeTickets.put(vehicleNo, ticket);

                System.out.println("Vehicle entered successfully");
                System.out.println("Ticket Number: "
                        + ticket.getTicketNumber());
                System.out.println("Slot Number: "
                        + ticket.getSlotNumber());

                return true;
            }
        }

        System.out.println("Parking Facility is Full");
        return false;
    }

    // Vehicle exits and fee is calculated
    public boolean exitVehicle(String vehicleNo, int exitMinutes) {

        ParkingTicket ticket = activeTickets.get(vehicleNo);

        if (ticket == null) {
            System.out.println("Error: Vehicle not found");
            return false;
        }

        int duration = exitMinutes - ticket.getEntryMinutes();

        if (duration < 0) {
            System.out.println("Error: Invalid exit time");
            return false;
        }

        int hours = (duration + 59) / 60;
        double fee = calculateFee(ticket.getVehicleType(), hours);

        slots[ticket.getSlotNumber()] = null;
        activeTickets.remove(vehicleNo);

        System.out.println("Vehicle exited successfully");
        System.out.println("Parking Duration: "
                + duration + " minutes");
        System.out.println("Parking Fee: Rs " + fee);

        return true;
    }

    // Calculate fee based on vehicle type
    private double calculateFee(String vehicleType, int hours) {

        if (vehicleType.equalsIgnoreCase("TwoWheeler")) {
            return hours * 10.0;
        } else if (vehicleType.equalsIgnoreCase("Car")) {
            return hours * 30.0;
        } else if (vehicleType.equalsIgnoreCase("ElectricCar")) {
            return hours * 50.0;
        }

        return 0;
    }

    public static void main(String[] args) {

        CompleteParkingSystem parking =
                new CompleteParkingSystem(2);

        System.out.println("=== NORMAL TEST ===");
        parking.enterVehicle("TN01AB1234", "Car", 0);
        parking.exitVehicle("TN01AB1234", 120);

        System.out.println("\n=== BOUNDARY TEST ===");
        parking.enterVehicle("TN02CD5678", "TwoWheeler", 0);
        parking.exitVehicle("TN02CD5678", 60);

        System.out.println("\n=== ZERO MINUTE TEST ===");
        parking.enterVehicle("TN03EF9012", "Car", 100);
        parking.exitVehicle("TN03EF9012", 100);

        System.out.println("\n=== INVALID TYPE TEST ===");
        parking.enterVehicle("TN04XX1111", "Bus", 0);

        System.out.println("\n=== DUPLICATE TEST ===");
        parking.enterVehicle("TN05YY2222", "Car", 0);
        parking.enterVehicle("TN05YY2222", "Car", 10);
    }
}