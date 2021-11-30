package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.CreateAddonInputBoundary;
import entities.Addon;
import org.json.JSONObject;

public class CreateAddonUseCase implements CreateAddonInputBoundary {

    AddonRepository addonRepository;

    @Override
    public boolean createAddon(JSONObject data) {
        // Possibly use a factory
        String id = data.getString("id");
        String name = data.getString("name");
        float price = data.getFloat("price");
        boolean isAvailable = data.getBoolean("isAvailable");

        Addon addon = new Addon()
        return false;
    }
}
