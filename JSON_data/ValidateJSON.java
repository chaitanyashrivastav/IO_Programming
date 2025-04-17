package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

class Student_{
    public String name ;
    public String email ;
}

public class ValidateJSON {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            // parse and map JSON to the Car class
            Student_ user = mapper.readValue(new File("src/data1.json"), Student_.class);
            System.out.println("Valid JSON structure ");
        } catch (Exception e) {
            System.out.println("Invalid JSON structure ");
            System.out.println("Reason: " + e.getMessage());
        }
    }
}
