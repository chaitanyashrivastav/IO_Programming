package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class validate_email extends employee{
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9+.-]+@[a-zA-Z0-9.-]+$") ;
        ObjectMapper mapper = new ObjectMapper() ;
        mapper.setVisibility(PropertyAccessor.FIELD , JsonAutoDetect.Visibility.ANY);
        List<employee> students_list = mapper.readValue(new File("src/employees.json"), new TypeReference<List<employee>>() {});

        for(employee s: students_list){
            if (pattern.matcher(s.email).matches()){
                System.out.println("email validated");
                System.out.println(Arrays.toString(new String[]{s.name, s.email, s.department}));
            }else{
                System.out.println("invalid email!");
            }

            System.out.println("-------------------");
        }
    }
}