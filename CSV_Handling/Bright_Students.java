package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Bright_Students{
    public static void main(String[] args){
        try(CSVReader reader = new CSVReader(new FileReader("src/sample_data.csv"))){
            String[] line ;
            reader.readNext() ;

            while((line = reader.readNext()) != null){
                if (Integer.parseInt(line[3]) > 80){
                    System.out.println("ID: " + line[0] + " Name: "+line[1]+" Marks: "+line[3]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}