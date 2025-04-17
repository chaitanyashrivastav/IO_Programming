package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Person{
    String name ;
    int age ;

    Person(String name, int age){
        this.name = name ;
        this.age = age ;
    }
}

class objectTOlist{
    public static void main(String[] args) throws IOException {
        List<Person> persons = new ArrayList<>() ;
        persons.add(new Person("joe" , 21)) ;
        persons.add(new Person("helen" , 19)) ;

        ObjectMapper mapper = new ObjectMapper() ;
        mapper.setVisibility(PropertyAccessor.FIELD , JsonAutoDetect.Visibility.ANY);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/persons.json") , persons) ;

        System.out.println("objects mapped to a new JSON file : persons.json" );
    }
}