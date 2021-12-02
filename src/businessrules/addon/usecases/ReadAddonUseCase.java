package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.ReadAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.loaders.AddonLoader;
import businessrules.outputboundary.AddonModel;
import entities.Addon;
import org.json.JSONException;
import org.json.JSONObject;

public class ReadAddonUseCase implements ReadAddonInputBoundary {
    AddonRepository addonRepository;
    AddonModel addonModel;
    AddonLoader addonLoader;

    public ReadAddonUseCase(AddonRepository aR, AddonModel aM, AddonLoader aL) {
        this.addonRepository = aR;
        this.addonModel = aM;
        this.addonLoader = aL;
    }

    @Override
    public JSONObject readAddon(String id) {

        JSONObject data = addonRepository.readAddon(id);

        Addon addon;
        try {
            addon = addonLoader.loadAddon(data);
        }catch (JSONException e){
            return addonModel.displayError(e.getMessage());
        }
        addonModel.displayAddon(addon.jsonify());
        return addon.jsonify();
    }
}
