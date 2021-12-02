package Adapters.controllers;

import businessrules.user.inputboundaries.CreateUserInputBoundary;
import businessrules.user.inputboundaries.DeleteUserInputBoundary;
import businessrules.user.inputboundaries.ReadUserInputBoundary;
import businessrules.user.inputboundaries.UpdateUserInputBoundary;
import org.json.JSONObject;

public class UserController {

    CreateUserInputBoundary createUserInputBoundary;
    DeleteUserInputBoundary deleteUserInputBoundary;
    UpdateUserInputBoundary updateUserInputBoundary;
    ReadUserInputBoundary readUserInputBoundary;

    public UserController(CreateUserInputBoundary createUserInputBoundary,
                          DeleteUserInputBoundary deleteUserInputBoundary,
                          UpdateUserInputBoundary updateUserInputBoundary,
                          ReadUserInputBoundary readUserInputBoundary){
        this.createUserInputBoundary = createUserInputBoundary;
        this.deleteUserInputBoundary = deleteUserInputBoundary;
        this.readUserInputBoundary = readUserInputBoundary;
        this.updateUserInputBoundary = updateUserInputBoundary;
    }

    public void runUpdateUser(String input){
        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("userId") &&
                update_data.has("userObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String userId = update_data.getString("userID");
        JSONObject userObject = update_data.getJSONObject("userObject");


        this.updateUserInputBoundary.updateUser(vendorToken, userId,userObject);
    }

    public void runCreateUser(String input){
        JSONObject create_data = new JSONObject(input);
        if(!create_data.has("userID") && create_data.has("userObject")){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_user = create_data.getJSONObject("userObject");


        this.createUserInputBoundary.createUser(vendorToken,new_user);
    }


    public void runDeleteUser(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!delete_data.has("userID") && delete_data.has("vendorToken")){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String userId = delete_data.getString("userId");


        this.deleteUserInputBoundary.deleteUser(vendorToken,userId);
    }

    public void runReadUser(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("userId")){

            //TODO:Call presenter with error message
        }
        String userId = read_data.getString("userId");


        this.readUserInputBoundary.readUser(userId);
    }
}
