package adapters;

import org.json.JSONObject;

public class JSONParser {
    JSONParser(){

    }

    public JSONObject parse(String raw_text){
        return new JSONObject(raw_text);
    }
}
