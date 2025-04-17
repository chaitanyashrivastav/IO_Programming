package org.example;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true) //this will ignore all the subjects field in the student class
class Stu{
    public String name ;
    public int age ;
}

public class filter_age{
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;

        List<Stu> students = mapper.readValue(new File("src/students.json"), new TypeReference<List<Stu>>() {});

        for(Stu s : students){
            if (s.age > 25){
                System.out.println("Name: "+s.name+ " Age: " +s.age);
            }
        }
    }
}