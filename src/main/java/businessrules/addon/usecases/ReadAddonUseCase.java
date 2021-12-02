package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.ReadAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.loaders.AddonLoader;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;
import entities.Addon;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadAddonUseCase implements ReadAddonInputBoundary {
    AddonRepository addonRepository;
    AddonModel addonModel;
    ErrorModel errorHandler;

    @Override
    public JSONObject readAddon(String id) {

        JSONObject data = addonRepository.readAddon(id);

        Addon addon;
        try {
            addon = AddonLoader.loadAddon(data);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return null;
        }
        addonModel.displayAddon(addon.jsonify());
        return addon.jsonify();
    }
}
