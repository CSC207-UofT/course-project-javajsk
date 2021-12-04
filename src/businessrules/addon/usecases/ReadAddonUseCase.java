package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.ReadAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.loaders.AddonLoader;
import businessrules.outputboundary.AddonModel;
import entities.Addon;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Use case for reading addon data from a given id
 */
public class ReadAddonUseCase implements ReadAddonInputBoundary {
    AddonRepository addonRepository;
    AddonModel addonModel;
    AddonLoader addonLoader;

    /**
     * Constructs a ReadAddonUseCase instance
     * @param aR addonRepository
     * @param aM addonModel
     * @param aL addonLoader
     */
    public ReadAddonUseCase(AddonRepository aR, AddonModel aM, AddonLoader aL) {
        this.addonRepository = aR;
        this.addonModel = aM;
        this.addonLoader = aL;
    }

    /**
     * A method that gets the data for an addon from the given id
     * @param id id of an addon
     * @return JSONObject containing error messages or addon information
     */
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
