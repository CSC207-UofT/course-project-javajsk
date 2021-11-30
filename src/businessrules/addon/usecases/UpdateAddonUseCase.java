package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.UpdateAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;
import entities.Addon;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateAddonUseCase implements UpdateAddonInputBoundary {
    AddonRepository addonRepository;
    ErrorModel errorHandler;
    AddonModel addonView;


    @Override
    public boolean updateAddon(String id, JSONObject object) {
        Addon addon;
        try {
            addon = AddonLoader.loadAddon(object);
        }catch (JSONException e){
            errorHandler.displayError(e.getMessage());
            return false;
        }

        boolean success = addonRepository.updateAddon(id, addon.jsonify());

        if(success){
            addonView.updateAddon(id, object);
            return true;
        }
        errorHandler.displayError("Unable to save modified addon in repository.");
        return false;
    }
}
