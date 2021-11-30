package Adapters.Controllers;
import Adapters.JSONParser;
import UseCases.Addon.CreateAddonInputBoundary;
import UseCases.Addon.CreateAddonUseCase;
import UseCases.Addon.DeleteAddonInputBoundary;
import UseCases.DataAccessInterfaces.AddonRepository;
import UseCases.Addon.DeleteAddonUseCase;
import org.json.JSONObject;

public class AddonController {
    CreateAddonInputBoundary createAddonInputBoundary;
    DeleteAddonInputBoundary deleteAddonInputBoundary;
    JSONParser parser;

    /** Constructor of controller
     *
     * @param createAddonInputBoundary is the input boundary for addon
     * @param deleteAddonInputBoundary is the input boundary for addon
     */
    AddonController(CreateAddonInputBoundary createAddonInputBoundary, DeleteAddonInputBoundary deleteAddonInputBoundary, JSONParser parser){
        this.createAddonInputBoundary = createAddonInputBoundary;
        this.deleteAddonInputBoundary = deleteAddonInputBoundary;
        this.parser = parser;
    }

    /**
     *
     * @param raw_text data to be parsed
     * @return true if addon is created
     */
    public boolean runCreateAddon(String raw_text) {
        JSONObject object = this.parser.parse(raw_text);
        String token = object.getString("VendorToken");
        String description = object.getString("description");
        String name = object.getString("name");
        float price = object.getFloat("price");
        JSONObject types = object.getJSONObject("types");
        //TODO: Need to parse types
        boolean availability = object.getBoolean("availability");
        String Id = object.getString("Id");
        return createAddonInputBoundary.createAddon(token, name, price, types, availability, Id);
    }
        /**
         * @param raw_text data to be parsed
         * @return true if addon is deleted
          */

     public boolean runDeleteAddon(String raw_text){
         JSONObject object = this.parser.parse(raw_text);
         String token = object.getString("VendorToken");
         String Id = object.getString("Id");
         return deleteAddonInputBoundary.deleteAddon(token, Id);
        }




    }
}
