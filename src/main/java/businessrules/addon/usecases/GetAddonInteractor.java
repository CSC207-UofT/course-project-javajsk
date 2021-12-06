package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.GetAddon;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Addon;

public class GetAddonInteractor implements GetAddon {
    Repository<Addon> addonRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;

    public GetAddonInteractor(Repository<Addon> addonRepository,
                              RepositoryBoundary repositoryBoundary,
                              ObjectBoundary<Addon> addonObjectBoundary) {
        this.addonRepository = addonRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.addonObjectBoundary = addonObjectBoundary;
    }

    @Override
    public ResponseObject getAddon(String id) {
        Addon addon = addonRepository.read(id);
        if (addon == null){
            repositoryBoundary.queryNotFound("No addon with this id.");
        }

        return addonObjectBoundary.showObject(addon);
    }
}
