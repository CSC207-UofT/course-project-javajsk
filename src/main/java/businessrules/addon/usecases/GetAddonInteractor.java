package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.GetAddon;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

/**
 * Get Addon use case
 */
public class GetAddonInteractor implements GetAddon {
    /**
     * The Addon repository.
     */
    Repository<Addon> addonRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Addon object boundary.
     */
    ObjectBoundary<Addon> addonObjectBoundary;

    /**
     * Instantiates a new Get addon interactor.
     *
     * @param addonRepository     the addon repository
     * @param repositoryBoundary  the repository boundary
     * @param addonObjectBoundary the addon object boundary
     */
    public GetAddonInteractor(Repository<Addon> addonRepository,
                              RepositoryBoundary repositoryBoundary,
                              ObjectBoundary<Addon> addonObjectBoundary) {
        this.addonRepository = addonRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.addonObjectBoundary = addonObjectBoundary;
    }

    /**
     * Method that returns addon with given id, if it exists in the form of a response object
     * @param id id of addon
     * @return response object containing addon object or error message
     */
    @Override
    public ResponseObject getAddon(String id) {
        Addon addon = addonRepository.read(id);
        if (addon == null){
            repositoryBoundary.queryNotFound("No addon with this id.");
        }

        return addonObjectBoundary.showObject(addon);
    }
}
