package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;

class json_xml{
    public static void main(String[] args) throws IOException {
        //mapper for JSon
        ObjectMapper mapper = new ObjectMapper() ;

        //mapper for XML
        XmlMapper xmlMapper = new XmlMapper() ;

        JsonNode json_node = mapper.readTree(new File("src/data1.json")) ;

        //json object will be converted to xml string
        String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(json_node);

        //json_node date added to xml file
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/data1.xml"), json_node);

        System.out.println("data converted to xml: "+xml);
    }

}