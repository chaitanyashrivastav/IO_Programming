package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVChunkReader {

    public static void processLargeCSV(String filePath) {
        int chunkSize = 100;
        int totalRecords = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            ArrayList<String> chunk = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                chunk.add(line);

                if (chunk.size() == chunkSize) {
                    // Process the chunk
                    processChunk(chunk);
                    totalRecords += chunk.size();
                    System.out.println("Processed " + totalRecords + " records so far.");

                    chunk.clear(); // Reset for next chunk
                }
            }

            // Process any remaining lines
            if (!chunk.isEmpty()) {
                processChunk(chunk);
                totalRecords += chunk.size();
                System.out.println("Processed " + totalRecords + " records in total.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processChunk(ArrayList<String> chunk) {
        // we just read the lines here
    }

    public static void main(String[] args) {
        String filePath = "src/large_file.csv";
        processLargeCSV(filePath);
    }
}
