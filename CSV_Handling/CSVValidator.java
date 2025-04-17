package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class CSVValidator {

    // Regex patterns
    private static final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern phonePattern = Pattern.compile("^\\d{10}$");

    public static void validateCSV(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            int lineNumber = 0;
            reader.readNext(); // skip headers

            while ((line = reader.readNext()) != null) {
                lineNumber++;
                String email = line[2];
                String phone = line[3];

                boolean valid = true;

                if (!emailPattern.matcher(email).matches()) {
                    System.out.println(" Line " + lineNumber + ": Invalid Email → " + email);
                    valid = false;
                }

                if (!phonePattern.matcher(phone).matches()) {
                    System.out.println(" Line " + lineNumber + ": Invalid Phone Number → " + phone);
                    valid = false;
                }

                if (valid) {
                    System.out.println(" Line " + lineNumber + ": Valid");
                }
            }

        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        validateCSV("src/data.csv");
    }
}
