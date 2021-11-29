package Adapters.Controllers;
import Adapters.JSONParser;
import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISingleton;
import UseCases.DataAccessInterfaces.SingletonRepository;
import org.json.JSONObject;
import UseCases.Singleton.CreateSingletonInputBoundary;
import UseCases.Singleton.DeleteSingletonInputBoundary;
import UseCases.Singleton.UpdateSingletonInputBoundary;
import Entities.Regular.RegularSingleton;

public class SingletonController {
    CreateSingletonInputBoundary createSingletonInputBoundary;
    DeleteSingletonInputBoundary deleteSingletonInputBoundary;
    UpdateSingletonInputBoundary updateSingletonInputBoundary;
    JSONParser parser;
    SingletonController(CreateSingletonInputBoundary createSingletonInputBoundary, DeleteSingletonInputBoundary deleteSingletonInputBoundary,
                        UpdateSingletonInputBoundary updateSingletonInputBoundary, JSONParser parser){
        this.createSingletonInputBoundary = createSingletonInputBoundary;
        this.deleteSingletonInputBoundary = deleteSingletonInputBoundary;
        this.updateSingletonInputBoundary = updateSingletonInputBoundary;
        this.parser = parser;

    }
    public boolean runCreateSingleton(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        userToken = object.getString("userToken");
        Id = object.getString("Id");
        name = object.getString("name");
        description = object.getString("description");
        price = object.getFloat("price");
        add_ons = object.get("add_ons");
        defaultSel = object.get("defaultSel");
        return createSingletonInputBoundary.createSingleton(userToken, Id, name, description, price, add_ons, defaultSel);
    }
    public boolean runDeleteSingleton(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        userToken = object.getString("userToken");
        singletonID = object.getString("singletonID");
        return deleteSingletonInputBoundary.deleteSingleton(userToken, singletonID);

    }
    public boolean runUpdateSingleton(String raw_text){
        JSONObject object = this.parser.parse(raw_text);
        userToken = object.getString("userToken");
        singletonID = object.getString("singletonID");
        singleton = SingletonRepository.getSingleton(singletonID);
        return updateSingletonInputBoundary.updateSingleton(userToken, singletonID, singleton);

    }
}
