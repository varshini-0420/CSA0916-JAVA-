import java.io.*;
import java.nio.file.*;

public class FileReadTest {

    public static void main(String[] args) throws IOException {

        int[] sizes = {1, 10, 50};

        for (int size : sizes) {

            String fileName = "file_" + size + "MB.txt";
            long totalBytes = size * 1024L * 1024L;

            // Generate test file
            try (OutputStream out = new BufferedOutputStream(
                    new FileOutputStream(fileName))) {

                byte[] data = new byte[1024];
                long written = 0;

                while (written < totalBytes) {
                    int count = (int) Math.min(data.length,
                            totalBytes - written);
                    out.write(data, 0, count);
                    written += count;
                }
            }

            // Unbuffered reading
            long start1 = System.nanoTime();

            try (FileInputStream in = new FileInputStream(fileName)) {
                while (in.read() != -1) {
                    // Read one byte at a time
                }
            }

            long end1 = System.nanoTime();

            // Buffered reading
            long start2 = System.nanoTime();

            try (BufferedInputStream in =
                    new BufferedInputStream(new FileInputStream(fileName))) {

                byte[] buffer = new byte[8192];

                while (in.read(buffer) != -1) {
                    // Read using buffer
                }
            }

            long end2 = System.nanoTime();

            System.out.println("File Size: " + size + " MB");
            System.out.println("Unbuffered Time: "
                    + (end1 - start1) / 1_000_000.0 + " ms");
            System.out.println("Buffered Time: "
                    + (end2 - start2) / 1_000_000.0 + " ms");
            System.out.println("---------------------------");
        }
    }
}