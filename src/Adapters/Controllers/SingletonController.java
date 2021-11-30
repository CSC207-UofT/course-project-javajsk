package Adapters.Controllers;
import Adapters.JSONParser;
import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISelection;
import Entities.Interfaces.ISingleton;
import UseCases.DataAccessInterfaces.SingletonRepository;
import org.json.JSONObject;
import UseCases.Singleton.CreateSingletonInputBoundary;
import UseCases.Singleton.DeleteSingletonInputBoundary;
import UseCases.Singleton.UpdateSingletonInputBoundary;
import Entities.Regular.RegularSingleton;

import java.util.List;

public class SingletonController {
    CreateSingletonInputBoundary createSingletonInputBoundary;
    DeleteSingletonInputBoundary deleteSingletonInputBoundary;
    UpdateSingletonInputBoundary updateSingletonInputBoundary;
    SingletonRepository singletonRepository;
    JSONParser parser;

    /**
     *
     * @param createSingletonInputBoundary input boundary for creating Singletons
     * @param deleteSingletonInputBoundary input boundary for deleting Singletons
     * @param updateSingletonInputBoundary input boundary for updating Singletons
     * @param parser of JSON file
     */
    SingletonController(CreateSingletonInputBoundary createSingletonInputBoundary, DeleteSingletonInputBoundary deleteSingletonInputBoundary,
                        UpdateSingletonInputBoundary updateSingletonInputBoundary, JSONParser parser){
        this.createSingletonInputBoundary = createSingletonInputBoundary;
        this.deleteSingletonInputBoundary = deleteSingletonInputBoundary;
        this.updateSingletonInputBoundary = updateSingletonInputBoundary;
        this.parser = parser;

    }

    /**
     *
     * @param raw_text of data to be parsed
     * @return true if Singleton is created
     */
    public boolean runCreateSingleton(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        String userToken = object.getString("userToken");
        String Id = object.getString("Id");
        String name = object.getString("name");
        String description = object.getString("description");
        float price = object.getFloat("price");
        JSONObject add_ons = object.getJSONObject("add_ons");
        //TODO: Parse add_ons to a List of Addons
        ISelection defaultSel = (ISelection) object.get("defaultSel");
        return createSingletonInputBoundary.createSingleton(userToken, Id, name, description, price, add_ons, defaultSel);
    }

    /**
     *
     * @param raw_text of data to be parsed
     * @return true if singleton is deleted
     */
    public void runDeleteSingleton(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        String userToken = object.getString("userToken");
        String singletonID = object.getString("singletonID");
        deleteSingletonInputBoundary.deleteSingleton(userToken, singletonID);

    }

    /**
     *
     * @param raw_text of data to be parsed
     * @return true if singleton is updated
     */
    public boolean runUpdateSingleton(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        String userToken = object.getString("userToken");
        String singletonID = object.getString("singletonID");
        RegularSingleton singleton = (RegularSingleton) singletonRepository.getSingleton(singletonID);
        return updateSingletonInputBoundary.updateSingleton(userToken, singletonID, singleton);

    }
}
