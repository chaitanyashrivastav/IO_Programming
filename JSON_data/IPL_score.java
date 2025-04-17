import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class IPL_score{
    public static void getFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper() ;
        List<Map<String , Object>> matches_list = mapper.readValue(new File(filename), new TypeReference<List<Map<String, Object>>>() {
        }) ;

        for(Map<String, Object> match : matches_list){
            match.put("team1", maskTeam((String)match.get("team1")));
            match.put("team2", maskTeam((String)match.get("team2")));
            match.put("winner", maskTeam((String)match.get("winner")));
            match.put("player_of_match" , "REDACTED") ;
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/censored_IPL.json") , matches_list);
        System.out.println("open censored_Ipl.json now");
    }

        public static void getCSVFile(String filename) throws Exception{
            try(CSVReader reader = new CSVReader(new FileReader(filename));
                CSVWriter writer = new CSVWriter(new FileWriter("src/censored_IPL.csv")) ;
            ){
                String[] header = reader.readNext() ; //first header is stored and skipped moving fwd
                String[] line ;
                List<String[]> data = new ArrayList<>() ;
                data.add(header) ;

                while((line = reader.readNext()) != null){ //101,Mumbai Indians,Chennai .......
                     line[1] = maskTeam(line[1]);
                     line[2] = maskTeam(line[2]);
                     line[5] = maskTeam(line[5]);
                     line[6] = line[6].replaceAll(line[6],"REDACTED");
                     data.add(line) ;
                }

                writer.writeAll(data);

                System.out.println("open censored_Ipl.csv now");
            }
    }
    private static String maskTeam(String value) {
        String[] words = value.split(" ",2);
        return words[0] + " ****" ;
    }

    public static void main(String[] args) throws Exception {
        String filename_json = "/Users/divyam/IdeaProjects/JSON_Assignment/src/IPL.json";
        String filename_csv = "/Users/divyam/IdeaProjects/JSON_Assignment/src/IPL_score.csv" ;
        getFile(filename_json);
        getCSVFile(filename_csv);
    }
}