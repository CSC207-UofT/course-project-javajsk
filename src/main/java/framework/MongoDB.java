package framework;

import adapters.dam.DBGateway;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.bson.io.BsonOutput;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MongoDB implements DBGateway {
    String username = "Application";
    String password = "F9PYZ6nevnvxGP6U";
    MongoClient mongoClient;
    MongoDatabase database;


    public MongoDB() {
        Connect();
    }

    private void Connect(){
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Application:"+ password +
                "@cluster0." +
                "whkvw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("UofTruck");
    }

    @Override
    public JSONObject read(String table, String id) {
        MongoCollection<Document> collection = database.getCollection(table);
        Document first = collection.find(new Document("_id",new ObjectId(id))).first();
        try{
            assert first != null;
            JSONObject jsonObject = new JSONObject(first.toJson());
            return jsonObjectCleaner(jsonObject);
        }catch (NullPointerException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(String table, String id, JSONObject newDat) {
        try {
            newDat.remove("id");
            MongoCollection<Document> collection = database.getCollection(table);
            Document filter = new Document("_id", new ObjectId(id));
            Document newDoc = Document.parse(newDat.toString());
            System.out.println("filter: "+ newDoc);
            collection.replaceOne(filter, newDoc);
            //TODO This exception clause needs to be modified.
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String create(String table, JSONObject data) {
        try{
            MongoCollection<Document> collection = database.getCollection(table);
            data.remove("id");
            Document insertion = Document.parse(data.toString());
            collection.insertOne(insertion);
            System.out.println("ID from db:"+insertion.getObjectId("_id").toString());
            return insertion.getObjectId("_id").toString();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<JSONObject> readMultiple(String table, String parameter, String needle) {
        try{
            MongoCollection<Document> collection = database.getCollection(table);
            Document filter = new Document(parameter, needle);
            FindIterable<Document> documents = collection.find(filter);
            List<JSONObject> returnValue = new ArrayList<>();
            for(Document document: documents){
                returnValue.add(jsonObjectCleaner(new JSONObject(document.toJson())));
            }
            return returnValue;
        }catch (Exception e) {
            return null;
        }

    }
    public JSONObject getCollection(String collection){
        System.out.println("Here");
        JSONArray addon_types = new JSONArray();
        MongoCollection<Document> collection_doc = database.getCollection(collection);
        FindIterable<Document> iterDoc = collection_doc.find();

        for(Document document: iterDoc){
            addon_types.put(jsonObjectCleaner(new JSONObject(document.toJson())));
        }




        JSONObject res = new JSONObject();
        res.put(collection,addon_types);
        return res;
    }

    @Override
    public JSONObject readOne(String table, String parameter, String needle) {
        try{
            MongoCollection<Document> collection = database.getCollection(table);
            Document filter = new Document(parameter, needle);
            Document first = collection.find(filter).first();
            assert first != null;
            return jsonObjectCleaner(new JSONObject(first.toJson()));
        } catch (NullPointerException e){
            return null;
        }
    }

    public static void main(String[] args){
        MongoDB mongoDB = new MongoDB();
        JSONObject jsonObject = mongoDB.read("Addon", "61abe7bb0a9c2c3d423a94c0");
        //TODO: Check if insertion n shit works.
    }

    public static JSONObject jsonObjectCleaner(JSONObject jsonObject){
        try {
            String id = jsonObject.getJSONObject("_id").getString("$oid");
            jsonObject.remove("_id");
            jsonObject.put("id", id);
            return jsonObject;
        }catch (JSONException e){
            System.out.println(jsonObject);
            System.out.println(e.getMessage());
            return null;
        }
    }


}