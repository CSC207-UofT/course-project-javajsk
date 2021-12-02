package Adapters.controllers;

import businessrules.customer.inputboundaries.CreateCustomerInputBoundary;
import businessrules.customer.inputboundaries.DeleteCustomerInputBoundary;
import businessrules.customer.inputboundaries.ReadCustomerInputBoundary;
import businessrules.customer.inputboundaries.UpdateCustomerInputBoundary;
import org.json.JSONObject;

public class CustomerController {

    CreateCustomerInputBoundary createCustomerInputBoundary;
    DeleteCustomerInputBoundary deleteCustomerInputBoundary;
    UpdateCustomerInputBoundary updateCustomerInputBoundary;
    ReadCustomerInputBoundary readCustomerInputBoundary;

    public CustomerController(CreateCustomerInputBoundary createCustomerInputBoundary,
                          DeleteCustomerInputBoundary deleteCustomerInputBoundary,
                          UpdateCustomerInputBoundary updateCustomerInputBoundary,
                          ReadCustomerInputBoundary readCustomerInputBoundary){
        this.createCustomerInputBoundary = createCustomerInputBoundary;
        this.deleteCustomerInputBoundary = deleteCustomerInputBoundary;
        this.readCustomerInputBoundary = readCustomerInputBoundary;
        this.updateCustomerInputBoundary = updateCustomerInputBoundary;
    }

    public void runUpdateCustomer(String input){
        JSONObject update_data = new JSONObject(input);
        if(!(update_data.has("vendorToken") && update_data.has("customerId") &&
                update_data.has("customerObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = update_data.getString("vendorToken");
        String customerId = update_data.getString("customerID");
        JSONObject customerObject = update_data.getJSONObject("customerObject");


        this.updateCustomerInputBoundary.updateCustomer(vendorToken, customerId,customerObject);
    }

    public void runCreateCustomer(String input){
        JSONObject create_data = new JSONObject(input);
        if(!(create_data.has("customerID") && create_data.has("customerObject"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = create_data.getString("vendorToken");
        JSONObject new_customer = create_data.getJSONObject("customerObject");


        this.createCustomerInputBoundary.createCustomer(vendorToken,new_customer);
    }


    public void runDeleteCustomer(String input){
        JSONObject delete_data = new JSONObject(input);
        if(!(delete_data.has("customerID") && delete_data.has("vendorToken"))){

            //TODO:Call presenter with error message
        }
        String vendorToken = delete_data.getString("vendorToken");
        String customerId = delete_data.getString("customerId");


        this.deleteCustomerInputBoundary.deleteCustomer(vendorToken,customerId);
    }

    public void runReadCustomer(String input){
        JSONObject read_data = new JSONObject(input);
        if(!read_data.has("customerId")){

            //TODO:Call presenter with error message
        }
        String customerId = read_data.getString("customerId");


        this.readCustomerInputBoundary.readCustomer(customerId);
    }
}
