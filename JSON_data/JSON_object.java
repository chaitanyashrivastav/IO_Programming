package org.example;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Car{
    String carModel ;
    String brand ;

    Car(String carModel , String brand){
        this.carModel = carModel ;
        this.brand = brand ;
    }
}
public class JSON_object{
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;
        mapper.setVisibility(PropertyAccessor.FIELD , JsonAutoDetect.Visibility.ANY) ;
        List<Car> values = new ArrayList<>();

        values.add(new Car("2022" , "Honda"));
        values.add(new Car("2017" , "Ferrari"));

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/cars.json") , values);
    }
}