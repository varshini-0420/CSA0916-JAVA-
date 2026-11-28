class UnsafeBooking {

    private final boolean[] booked = new boolean[10];
    private int bookingsMade = 0;

    public void book(int seat) {
        if (!booked[seat]) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            booked[seat] = true;
            bookingsMade++;
        }
    }

    public int getBookingsMade() {
        return bookingsMade;
    }
}

class SafeMethodBooking {

    private final boolean[] booked = new boolean[10];
    private int bookingsMade = 0;

    public synchronized void book(int seat) {
        if (!booked[seat]) {
            booked[seat] = true;
            bookingsMade++;
        }
    }

    public int getBookingsMade() {
        return bookingsMade;
    }
}

class SafeBlockBooking {

    private final boolean[] booked = new boolean[10];
    private int bookingsMade = 0;

    public void book(int seat) {
        synchronized (this) {
            if (!booked[seat]) {
                booked[seat] = true;
                bookingsMade++;
            }
        }
    }

    public int getBookingsMade() {
        return bookingsMade;
    }
}

public class CinemaBookingDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== WITHOUT SYNCHRONIZATION ===");

        UnsafeBooking unsafe = new UnsafeBooking();
        Thread[] users1 = new Thread[100];

        for (int i = 0; i < 100; i++) {
            final int seat = i % 10;
            users1[i] = new Thread(() -> unsafe.book(seat));
            users1[i].start();
        }

        for (Thread t : users1) {
            t.join();
        }

        System.out.println("Seats sold: " + unsafe.getBookingsMade());

        System.out.println("\n=== SYNCHRONIZED METHOD ===");

        SafeMethodBooking safeMethod = new SafeMethodBooking();
        Thread[] users2 = new Thread[100];

        for (int i = 0; i < 100; i++) {
            final int seat = i % 10;
            users2[i] = new Thread(() -> safeMethod.book(seat));
            users2[i].start();
        }

        for (Thread t : users2) {
            t.join();
        }

        System.out.println("Seats sold: " + safeMethod.getBookingsMade());

        System.out.println("\n=== SYNCHRONIZED BLOCK ===");

        SafeBlockBooking safeBlock = new SafeBlockBooking();
        Thread[] users3 = new Thread[100];

        for (int i = 0; i < 100; i++) {
            final int seat = i % 10;
            users3[i] = new Thread(() -> safeBlock.book(seat));
            users3[i].start();
        }

        for (Thread t : users3) {
            t.join();
        }

        System.out.println("Seats sold: " + safeBlock.getBookingsMade());
    }
}