package Adapters.Controllers;
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
    AddonController(CreateAddonInputBoundary createAddonInputBoundary, DeleteAddonInputBoundary deleteAddonInputBoundary, JSONPARser parser){
        this.createAddonInputBoundary = createAddonInputBoundary;
        this.deleteAddonInputBoundary = deleteAddonInputBoundary;
        this.parser = parser;
    }
    public boolean runCreateAddon(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        token = object.getString("VendorToken");
        description = object.getString("description");
        name = object.getString("name");
        price = object.getFloat("price");
        type = object.getInt("types");
        availability = object.getBoolean("availability");
        Id = object.getString("Id");
        return createAddonInputBoundary.createAddon(token, name, description, price, type, availability, Id);

     public boolean runDeleteAddon(String raw_text){
         JSONObject object = this.parser.parse(raw_text);
         token = object.getString("VendorToken");
         Id = object.getString("Id");
         return deleteAddonInputBoundary.deleteAddon(token, Id);
        }




    }
}
