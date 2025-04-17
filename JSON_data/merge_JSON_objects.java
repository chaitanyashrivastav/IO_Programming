package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

class merge_JSON_objects{
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;

        //read both JSON files as tree-nodes
        JsonNode json1= mapper.readTree(new File("src/data1.json")) ;
        JsonNode json2= mapper.readTree(new File("src/data2.json")) ;

        ObjectNode merged = json1.deepCopy() ;
        merged.setAll((ObjectNode)json2) ;
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/employees.json") , merged) ;

        System.out.println("files merged to a new json object named employees.json");
    }
}