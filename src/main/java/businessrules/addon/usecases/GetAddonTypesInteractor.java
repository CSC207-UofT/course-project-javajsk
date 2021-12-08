package businessrules.addon.usecases;

import adapters.dam.AddonDB;
import businessrules.addon.inputboundaries.GetAddonTypes;
import businessrules.outputboundaries.ResponseObject;

/**
 * Get addon types use cases
 */
public class GetAddonTypesInteractor implements GetAddonTypes {
    /**
     * The Addon repository.
     */
    AddonDB addonRepository;

    /**
     * Instantiates a new Get addon types interactor.
     */
    public GetAddonTypesInteractor(AddonDB addonRepository) {
        this.addonRepository = addonRepository;
    }

    /**
     * Method that returns a response object containing addon types
     * @return response object containing addon types
     */
    public ResponseObject getAddonTypes() {

        return new ResponseObject(200, "",addonRepository.getAddonTypes().toString());
    }
}
