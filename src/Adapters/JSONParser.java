package Adapters;

import org.json.JSONObject;

public class JSONParser {
    JSONObject parse(String raw_text){
        return new JSONObject(raw_text);
    }
}
