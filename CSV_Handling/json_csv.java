package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import java.util.List;

class Student_{
    int id ;
    String name ;
    String grade ;

    public Student_() {
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + grade ;
    }
}

public class json_csv{
    public static void jsonTOcsv(String jsonFilePath , String csvFilePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

        List<Student_> students = mapper.readValue(new File(jsonFilePath), new TypeReference<List<Student_>>() {});
        try(CSVWriter writer = new CSVWriter(new FileWriter(csvFilePath))){
            writer.writeNext(new String[]{"ID" ,"name" ,"grade"});

            for(Student_ s : students){
                writer.writeNext(new String[]{
                        String.valueOf(s.id),
                        s.name,
                        s.grade
                });
            }

            System.out.println("JSON converted to CSV at: " + csvFilePath);
        }
    }

    public static void csvToJson(String csvFilePath, String jsonFilePath) throws IOException {
        List<Student_> students = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            String[] nextLine;
            reader.readNext(); //skips the header

            while ((nextLine = reader.readNext()) != null) {
                Student_ s = new Student_();
                s.id = Integer.parseInt(nextLine[0]);
                s.name = nextLine[1];
                s.grade = nextLine[2];
                students.add(s);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), students);

        System.out.println("CSV converted back to JSON at: " + jsonFilePath);
    }
    public static void main(String[] args) throws IOException {
        String jsonFilePath = "src/students_.json" ;
        String csvOutput = "src/students_csv.csv";
        String JSONOutput = "src/student_JSON.json" ;

        jsonTOcsv(jsonFilePath , csvOutput);
        csvToJson(csvOutput , JSONOutput);
    }
}