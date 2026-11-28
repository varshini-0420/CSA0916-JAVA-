import java.io.*;

public class SafeSalesReport {

    public static void main(String[] args) {

        String[] files = {
            "store1.txt",
            "store2.txt",
            "store3.txt"
        };

        int totalSales = 0;

        for (String file : files) {

            try (BufferedReader br =
                    new BufferedReader(new FileReader(file))) {

                System.out.println("Reading: " + file);

                String line;

                while ((line = br.readLine()) != null) {
                    totalSales += Integer.parseInt(line);
                }

            } catch (IOException | NumberFormatException e) {
                System.out.println("Error in " + file + ": " + e.getMessage());
            }
        }

        System.out.println("----------------------");
        System.out.println("Total Sales: " + totalSales);
        System.out.println("Report Generated Successfully");
    }
}