package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class update_salary{
    public static void main(String[] args){
        try(CSVReader reader = new CSVReader(new FileReader("src/output.csv"))){
            String[] line ;
            reader.readNext() ;
            List<String[]> upDatedList = new ArrayList<>();

            while((line = reader.readNext()) != null){
                if(line[2].equals("IT")){
                    int salary = Integer.parseInt(line[3]) ;
                    salary = salary + (10 * salary /100 ) ;
                    line[3] = String.valueOf(salary) ;
                }
                upDatedList.add(line) ; // add modified or unmodified row
            }
            try(CSVWriter writer = new CSVWriter(new FileWriter("src/updated_output.csv"))){
                writer.writeAll(upDatedList) ;
                System.out.println("updated sucessfully to a new file");
            };
        } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}