package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.DeleteAddonInputBoundary;
import businessrules.dai.AddonRepository;
import businessrules.outputboundary.AddonModel;
import businessrules.outputboundary.ErrorModel;

public class DeleteAddonUseCase implements DeleteAddonInputBoundary {
    AddonRepository addonRepository;
    AddonModel addonModel;
    ErrorModel errorHandler;

    @Override
    public boolean deleteAddon(String id) {
        if(addonRepository.deleteAddon(id)){
            addonModel.deleteAddon(id);
            return true;
        }
        return false;
    }
}
