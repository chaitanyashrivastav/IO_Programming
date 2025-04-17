package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Cnt_rows{
    public static void main(String[] args) {
        try(CSVReader reader = new CSVReader(new FileReader("src/sample_data.csv"))){
            String[] line ;
            reader.readNext() ; //skip the first line(header)

            int cnt = 0 ;
            while((line = reader.readNext()) != null){
                cnt++ ;
            }
            System.out.println("Total rows: "+cnt);
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}