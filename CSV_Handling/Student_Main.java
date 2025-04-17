package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Student{
    int ID ;
    String name ;
    int age ;
    int marks ;

    Student(int ID , String name, int age , int marks){
        this.ID = ID ;
        this.name = name ;
        this.age = age ;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return ID + " "+name + " "+ age+" " + marks ;
    }
}
public class Student_Main{
    public static void main(String[] args) {
        try(CSVReader reader = new CSVReader(new FileReader("src/sample_data.csv"))){
            List<String[]> students = reader.readAll() ;
            students.remove(0);

            List<Student> ans = new ArrayList<>();

            for(String[] record : students){
                int ID = Integer.parseInt(record[0]) ;
                String name =  record[1] ;
                int age = Integer.parseInt(record[2]) ;
                int marks = Integer.parseInt(record[3]) ;

                ans.add(new Student(ID ,name ,age ,marks)) ;
            }

            for(Student s : ans){
                System.out.println(s);
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }
}