class ScannerThread extends Thread {

    private int stationId;
    private int scripts;
    private static int totalScanned = 0;

    ScannerThread(int stationId, int scripts) {
        this.stationId = stationId;
        this.scripts = scripts;
    }

    public void run() {
        try {
            System.out.println("Scanner Station " + stationId + " started.");

            for (int i = 1; i <= scripts; i++) {
                Thread.sleep(200);
            }

            System.out.println("Scanner Station " + stationId +
                    " completed " + scripts + " scripts.");

            synchronized (ScannerThread.class) {
                totalScanned += scripts;
            }

        } catch (InterruptedException e) {
            System.out.println("Scanner interrupted: " + stationId);
            Thread.currentThread().interrupt();
        }
    }

    static int getTotalScanned() {
        return totalScanned;
    }
}

class RunnableScanner implements Runnable {

    private int stationId;
    private int scripts;

    RunnableScanner(int stationId, int scripts) {
        this.stationId = stationId;
        this.scripts = scripts;
    }

    public void run() {
        try {
            System.out.println("Runnable Station " + stationId + " started.");

            for (int i = 1; i <= scripts; i++) {
                Thread.sleep(200);
            }

            System.out.println("Runnable Station " + stationId +
                    " completed " + scripts + " scripts.");

        } catch (InterruptedException e) {
            System.out.println("Station interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}

public class ScannerStationDemo {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("=== USING THREAD CLASS ===");

        ScannerThread[] stations = {
            new ScannerThread(1, 5),
            new ScannerThread(2, 6),
            new ScannerThread(3, 4),
            new ScannerThread(4, 5)
        };

        for (ScannerThread station : stations) {
            station.start();
        }

        for (ScannerThread station : stations) {
            System.out.println("Is station alive? " + station.isAlive());
            station.join();
        }

        System.out.println("Total scripts scanned: " +
                ScannerThread.getTotalScanned());

        System.out.println("\n=== USING RUNNABLE INTERFACE ===");

        Thread[] runnableStations = {
            new Thread(new RunnableScanner(1, 5)),
            new Thread(new RunnableScanner(2, 6)),
            new Thread(new RunnableScanner(3, 4)),
            new Thread(new RunnableScanner(4, 5))
        };

        for (Thread station : runnableStations) {
            station.start();
        }

        for (Thread station : runnableStations) {
            station.join();
        }

        System.out.println("All Runnable scanner stations completed.");
    }
}