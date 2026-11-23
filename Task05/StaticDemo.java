public class StaticDemo {

    // Static variable shared by all objects
    private static int ticketCounter = 0;

    private String vehicleNo;
    private int ticketNumber;

    public StaticDemo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
        ticketCounter++;
        this.ticketNumber = ticketCounter;
    }

    public void displayTicket() {
        System.out.println("Ticket Number: " + ticketNumber
                + " | Vehicle: " + vehicleNo);
    }

    // Static method
    public static int getTotalTickets() {
        return ticketCounter;
    }

    public static void main(String[] args) {

        StaticDemo ticket1 = new StaticDemo("TN01AB1234");
        StaticDemo ticket2 = new StaticDemo("TN02CD5678");
        StaticDemo ticket3 = new StaticDemo("TN03EF9012");

        ticket1.displayTicket();
        ticket2.displayTicket();
        ticket3.displayTicket();

        System.out.println("Total Tickets Created: "
                + StaticDemo.getTotalTickets());
    }
}