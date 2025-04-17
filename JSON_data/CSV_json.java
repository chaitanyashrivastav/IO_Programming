package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class CSV_json{
    public static void main(String[] args) throws IOException {
        try(CSVReader reader = new CSVReader(new FileReader("src/input.csv"))){
            String[] header = reader.readNext() ; //skips the header
            String[] line;

            List<Map<String , String>> json_list = new ArrayList<>() ; //list of hashmaps
            ObjectMapper mapper = new ObjectMapper() ;

            while((line = reader.readNext()) != null){
                Map<String , String> jsonMap = new LinkedHashMap<>() ;
                for (int i = 0; i < header.length; i++) {
                    jsonMap.put(header[i], line[i]) ;
                }
                json_list.add(jsonMap) ;
            }
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/output.json") , json_list);

//            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json_list)) ;

            System.out.println("all the data converted to json in a output.json file");

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}