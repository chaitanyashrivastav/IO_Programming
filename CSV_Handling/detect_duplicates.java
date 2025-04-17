package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class detect_duplicates{
    public static void main(String[] args){
        Map<String ,String[]> entries = new HashMap<>() ;
        try(CSVReader reader = new CSVReader(new FileReader("src/sample_data.csv"))){
            String[] lines ;
            reader.readNext();

            while((lines = reader.readNext()) != null){
                String id = lines[0] ;
                String name = lines[1] ;
                String age = lines[2] ;
                String marks = lines[3] ;

                if(entries.containsKey(id)){
                    System.out.println("Original: "+Arrays.toString(entries.get(id)));
                    System.out.println("Duplicates: "+Arrays.toString(new String[]{name , age , marks}) ) ;
                    System.out.println("--------------------");
                }

                entries.put(id , new String[]{name , age , marks}) ;

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}