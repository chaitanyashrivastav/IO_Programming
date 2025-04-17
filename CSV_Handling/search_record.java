package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class search_record{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in) ;
        String name = sc.nextLine() ;
        try(CSVReader reader = new CSVReader(new FileReader("src/output.csv"))){
            String[] line ;
            reader.readNext() ;

            while((line = reader.readNext()) != null){
                if (line[1].toLowerCase().equals(name)){
                    System.out.println("found!!!");
                    System.out.println("ID: " + line[0] + " Name: "+line[1]+" Department: "+line[2]+" Salary: "+line[3]);
                    return  ;
                }
            }
            System.out.println("nope/ its not there");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }
}