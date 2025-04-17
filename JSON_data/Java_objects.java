package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class Student {
    String name ;
    int age ;
    String[] subjects ;
}

public class Java_objects {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;
        mapper.setVisibility( PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY ) ;//will access all the private fields
        // without any getter and setter methods
        List<Student> students = mapper.readValue(new File("src/students.json"), new TypeReference<List<Student>>() {
        }) ;

        for(Student s : students){
            System.out.println("Name: " + s.name);
            System.out.println("Age: " + s.age);
            System.out.println("Subjects: " + Arrays.toString(s.subjects));
            System.out.println("-----------");
        }
    }
}