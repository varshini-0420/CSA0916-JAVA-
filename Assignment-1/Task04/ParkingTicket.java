public class ParkingTicket {

    private static int ticketCounter = 1001;

    private int ticketNumber;
    private String vehicleNo;
    private String vehicleType;
    private int entryMinutes;

    // Default constructor
    public ParkingTicket() {
        this("UNKNOWN", "UNKNOWN", 0);
    }

    // Constructor with vehicle number and type
    public ParkingTicket(String vehicleNo, String vehicleType) {
        this(vehicleNo, vehicleType, 0);
    }

    // Main constructor
    public ParkingTicket(String vehicleNo, String vehicleType, int entryMinutes) {
        this.ticketNumber = ticketCounter++;
        this.vehicleNo = vehicleNo;
        this.vehicleType = vehicleType;
        this.entryMinutes = entryMinutes;
    }

    public void displayTicket() {
        System.out.println("Ticket Number: " + ticketNumber);
        System.out.println("Vehicle Number: " + vehicleNo);
        System.out.println("Vehicle Type: " + vehicleType);
        System.out.println("Entry Time (minutes): " + entryMinutes);
        System.out.println();
    }

    public static void main(String[] args) {

        ParkingTicket ticket1 =
                new ParkingTicket("TN01AB1234", "Car", 120);

        ParkingTicket ticket2 =
                new ParkingTicket("TN02CD5678", "TwoWheeler");

        ParkingTicket ticket3 = new ParkingTicket();

        System.out.println("Parking Ticket Details");
        System.out.println("----------------------");

        ticket1.displayTicket();
        ticket2.displayTicket();
        ticket3.displayTicket();
    }
}