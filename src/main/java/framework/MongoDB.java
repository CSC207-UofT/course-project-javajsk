package framework;

import adapters.DBGateway;
import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import io.jsonwebtoken.io.IOException;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Mongo Database
 */
public class MongoDB implements DBGateway {
    /**
     * The Mongo client.
     */
    MongoClient mongoClient;
    /**
     * The Database.
     */
    MongoDatabase database;


    /**
     * Instantiates a new Mongo db.
     */
    public MongoDB() {
        Connect();
    }

    /**
     * Get mongo password string.
     *
     * @return Loads mongo password from properties file
     */
    public String getMongoPassword() {

        try (InputStream input = new FileInputStream("src/props.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            return (prop.getProperty("dbpassword"));


        } catch (IOException | java.io.IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Get mongo username string.
     *
     * @return Loads mongo username from properties file
     */
    public String getMongoUsername() {

        try (InputStream input = new FileInputStream("src/props.properties")) {

            Properties prop = new Properties();

            prop.load(input);

            return (prop.getProperty("dbusername"));


        } catch (IOException | java.io.IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * Method sets up Mongo Client to connect mongo database to program
     */
    private void Connect() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://Application:" + this.getMongoPassword() +
                "@cluster0." +
                "whkvw.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        this.mongoClient = mongoClient;
        this.database = mongoClient.getDatabase("UofTruck");
    }

    /**
     * Method returns JSONObject representing data with given id from database under given table
     *
     * @param table database table
     * @param id    id of object
     * @return JSON object
     */
    @Override
    public JSONObject read(String table, String id) {
        MongoCollection<Document> collection = database.getCollection(table);

        try {
            Document first = collection.find(new Document("_id", new ObjectId(id))).first();
            assert first != null;
            JSONObject jsonObject = new JSONObject(first.toJson());
            return jsonObjectCleaner(jsonObject);
        } catch (IllegalArgumentException obj) {
            System.out.println("Not a valid id.");
            return null;
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Method updates object in given table with given id with new data
     *
     * @param table  table of database to update
     * @param id     id of object in table to update
     * @param newDat new data
     * @return whether data was successfully updated
     */
    @Override
    public boolean update(String table, String id, JSONObject newDat) {
        try {
            newDat.remove("id");
            MongoCollection<Document> collection = database.getCollection(table);
            Document filter = new Document("_id", new ObjectId(id));
            Document newDoc = Document.parse(newDat.toString());
            System.out.println("filter: " + newDoc);
            collection.replaceOne(filter, newDoc);
            //TODO This exception clause needs to be modified.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Method creates new object to save on given table with given data
     *
     * @param table table on database
     * @param data  data to add
     * @return String representation of object created
     */
    @Override
    public String create(String table, JSONObject data) {
        try {
            MongoCollection<Document> collection = database.getCollection(table);
            data.remove("id");
            Document insertion = Document.parse(data.toString());
            collection.insertOne(insertion);
            System.out.println("ID from db:" + insertion.getObjectId("_id").toString());
            return insertion.getObjectId("_id").toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Method for reading multiple entries of an object from the database
     *
     * @param parameter the parameter to look up in the database
     * @param needle    the value of the parameter to find in the database
     * @return a list of entity objects that match the requirements
     */
    @Override
    public List<JSONObject> readMultiple(String table, String parameter, String needle) {
        try {
            MongoCollection<Document> collection = database.getCollection(table);
            Document filter = new Document(parameter, needle);
            FindIterable<Document> documents = collection.find(filter);
            List<JSONObject> returnValue = new ArrayList<>();
            for (Document document : documents) {
                returnValue.add(jsonObjectCleaner(new JSONObject(document.toJson())));
            }
            return returnValue;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Method returns JSON object of given collection from database
     *
     * @param collection collection to get from database
     * @return JSON object
     */
    public JSONObject getCollection(String collection) {
        JSONArray addon_types = new JSONArray();
        MongoCollection<Document> collection_doc = database.getCollection(collection);
        FindIterable<Document> iterDoc = collection_doc.find();

        for (Document document : iterDoc) {
            addon_types.put(jsonObjectCleaner(new JSONObject(document.toJson())));
        }

        JSONObject res = new JSONObject();
        res.put(collection, addon_types);
        return res;
    }

    /**
     * Method for reading one entry of an object from the database
     *
     * @param table     table on database
     * @param parameter the parameter to look up in the database
     * @param needle    the value of the parameter to find in the database
     * @return JSON object that matches the requirements
     */
    @Override
    public JSONObject readOne(String table, String parameter, String needle) {
        try {
            MongoCollection<Document> collection = database.getCollection(table);
            Document filter = new Document(parameter, needle);
            Document first = collection.find(filter).first();
            assert first != null;
            return jsonObjectCleaner(new JSONObject(first.toJson()));
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Json object cleaner json object.
     *
     * @param jsonObject the json object
     * @return the json object
     */
    public static JSONObject jsonObjectCleaner(JSONObject jsonObject) {
        try {
            String id = jsonObject.getJSONObject("_id").getString("$oid");
            jsonObject.remove("_id");
            jsonObject.put("id", id);
            return jsonObject;
        } catch (JSONException e) {
            System.out.println(jsonObject);
            System.out.println(e.getMessage());
            return null;
        }
    }
}