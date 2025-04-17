package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Read_CSV{
    public static void main(String[] args) {
        try(CSVReader reader = new CSVReader(new FileReader("src/sample_data.csv"))){
            String[] line  ;
            while((line = reader.readNext()) != null){
                System.out.println("ID:"+line[0] + " Name:"+line[1]);
            }
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}