package adapters;

import org.json.JSONObject;

import java.util.List;

/**
 * Database Gateway
 */
public interface DBGateway {
    /**
     * Method returns JSONObject representing data with given id from database under given table
     * @param table database table
     * @param id id of object
     * @return JSON object
     */
    JSONObject read(String table, String id);

    /**
     * Method updates object in given table with given id with new data
     * @param table table of database to update
     * @param id id of object in table to update
     * @param newDat new data
     * @return whether data was successfully updated
     */
    boolean update(String table, String id, JSONObject newDat);

    /**
     * Method creates new object to save on given table with given data
     * @param table table on database
     * @param data data to add
     * @return String representation of object created
     */
    String create(String table, JSONObject data);

    /**
     * Method for reading multiple entries of an object from the database
     * @param parameter the parameter to look up in the database
     * @param needle the value of the parameter to find in the database
     * @return a list of entity objects that match the requirements
     */
    List<JSONObject> readMultiple(String table,String parameter, String needle);

    /**
     * Method for reading one entry of an object from the database
     * @param table table on database
     * @param parameter the parameter to look up in the database
     * @param needle the value of the parameter to find in the database
     * @return JSON object that matches the requirements
     */
    JSONObject readOne(String table, String parameter, String needle);


    /**
     * Method returns JSON object of given collection from database
     * @param collection collection to get from database
     * @return JSON object
     */
    JSONObject getCollection(String collection);
}
