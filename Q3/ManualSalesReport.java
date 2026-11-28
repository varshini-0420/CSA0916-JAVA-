import java.io.*;

public class ManualSalesReport {

    public static void main(String[] args) {

        String[] files = {
            "store1.txt",
            "store2.txt",
            "store3.txt"
        };

        int totalSales = 0;

        for (String file : files) {

            BufferedReader br = null;

            try {
                System.out.println("Reading: " + file);

                br = new BufferedReader(new FileReader(file));

                String line;

                while ((line = br.readLine()) != null) {
                    totalSales += Integer.parseInt(line);
                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("Error in " + file + ": " + e.getMessage());

            } finally {
                try {
                    if (br != null) {
                        br.close();
                        System.out.println("Closed: " + file);
                    }
                } catch (IOException e) {
                    System.out.println("Closing error: " + e.getMessage());
                }
            }
        }

        System.out.println("----------------------");
        System.out.println("Total Sales: " + totalSales);
        System.out.println("Report Generated Successfully");
    }
}