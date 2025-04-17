package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class MergeCSV{
    public static void main(String[] args) throws FileNotFoundException {
        Map<String , String[]> merged_map = new HashMap<>() ;

        try(CSVReader reader1 = new CSVReader(new FileReader("src/students1.csv"));
            CSVReader reader2 = new CSVReader(new FileReader("src/students2.csv"));
            CSVWriter writer = new CSVWriter(new FileWriter("merge_output.csv"))
        ) {
            String[] header1 = reader1.readNext() ; // ID , name,age
            String[] line1 ;

            while((line1 = reader1.readNext()) != null){
                merged_map.put(line1[0] , line1) ; //line1[0] is empID ,name and all that
            }

            writer.writeNext(new String[]{"ID" , "Name" , "Age" , "Marks" , "Grade"}) ;

            String[] header2 = reader2.readNext() ;
            String[] line2 ;

            while((line2 = reader2.readNext()) != null){
                String ID = line2[0] ;
                String marks = line2[1] ;
                String grade = line2[2] ;

                if(merged_map.containsKey(ID)){
                    String[] already_kept_data = merged_map.get(ID) ;
                    String[] newData = new String[]{ID , already_kept_data[1] , already_kept_data[2] , marks , grade} ;

                    writer.writeNext(newData);
                }
            }

            System.out.println("Merged file written to merge_output.csv" );
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}