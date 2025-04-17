package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class keys_values{
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;

        HashMap<String, Object> values_map = mapper.readValue(new File("src/mapping_persons.json"), new TypeReference<HashMap<String , Object>>() {
        }) ;  //will read the file in the form of hashmap<String , object>
        // where key is stored as string and values as Object type

        //now the json datafile is stored in a hashmap
        //So , we iterate over the hashmap now

        for (Map.Entry<String , Object> i : values_map.entrySet()) {
            System.out.println("Keys: " + i.getKey() + " | Values:"+i.getValue());
            System.out.println("------------------");
        }

    }
}