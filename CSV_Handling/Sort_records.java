package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Employee {
    String name;
    String department;
    double salary;

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name+" " + department+" " + salary ;
    }
}

class Sort_records{
    public static void main(String[] args){
        try(CSVReader reader = new CSVReader(new FileReader("src/output.csv"))){

            List<Employee> employee_store = new ArrayList<>();

            List<String[]> peopleList = reader.readAll() ;
            peopleList.remove(0) ;  //removing the header


            for (String[] record : peopleList){
                String name = record[1] ;
                String Department = record[2] ;
                double salary = Double.parseDouble(record[3]) ;
                employee_store.add(new Employee(name ,Department,salary )) ;
            }

            employee_store.sort((a,b)-> Double.compare(b.salary,a.salary));

            for (int i = 0; i < Math.min(2,employee_store.size()); i++) {
                Employee e = employee_store.get(i) ;
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
    }
}