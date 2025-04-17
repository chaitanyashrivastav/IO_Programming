package org.example;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

class Write_CSV{
    public static void main(String[] args) {
        try(CSVWriter writer = new CSVWriter(new FileWriter("src/output.csv"))){
            String[] header = {"ID", "Name", "Department", "Salary"} ;
            String[] row1  = {"190","Joe","CSE","70000"} ;
            String[] row2  = {"178","Helen","Finance","80000"} ;
            String[] row3  = {"780","miller","Banking","79090"} ;

            writer.writeNext(header) ;
            writer.writeNext(row1) ;
            writer.writeNext(row2) ;
            writer.writeNext(row3)  ;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}