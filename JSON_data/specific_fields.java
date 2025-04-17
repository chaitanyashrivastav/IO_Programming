package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

class employee{
    String name ;
    String email ;
    String department ;
}

public class specific_fields{
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;
        mapper.setVisibility(PropertyAccessor.FIELD , JsonAutoDetect.Visibility.ANY) ;

        List<employee> employeeList = mapper.readValue(new File("src/employees.json"), new TypeReference<List<employee>>() {});

        for(employee e : employeeList){
            System.out.println("name: "+e.name);
            System.out.println("name: "+e.email);
            System.out.println("------------------");
        }

    }
}