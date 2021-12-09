package businessrules.addon.usecases;

import businessrules.addon.inputboundaries.CreateAddon;
import businessrules.dai.Repository;
import businessrules.dai.VendorRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.Addon;
import entities.Vendor;

/**
 * The type Create addon interactor.
 */
public class CreateAddonInteractor implements CreateAddon {
    /**
     * The Addon repository.
     */
    Repository<Addon> addonRepository;
    /**
     * The Vendor repository.
     */
    VendorRepository vendorRepository;
    /**
     * The Vendor boundary.
     */
    VendorBoundary vendorBoundary;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Addon object boundary.
     */
    ObjectBoundary<Addon> addonObjectBoundary;

    /**
     * Instantiates a new Create addon interactor.
     *
     * @param addonRepository     the addon repository
     * @param vendorRepository    the vendor repository
     * @param vendorBoundary      the vendor boundary
     * @param repositoryBoundary  the repository boundary
     * @param addonObjectBoundary the addon object boundary
     */
    public CreateAddonInteractor(Repository<Addon> addonRepository, VendorRepository vendorRepository,
                                 VendorBoundary vendorBoundary, RepositoryBoundary repositoryBoundary,
                                 ObjectBoundary<Addon> addonObjectBoundary) {
        this.addonRepository = addonRepository;
        this.vendorRepository = vendorRepository;
        this.vendorBoundary = vendorBoundary;
        this.repositoryBoundary = repositoryBoundary;
        this.addonObjectBoundary = addonObjectBoundary;
    }

    /**
     * A method that creates an Addon entity and returns a response object containing
     * message/content for user interface
     *
     * @param vendorToken token of current vendor
     * @param addon       information to create the addon with
     * @return response object containing the addon object or error message
     */
    @Override
    public ResponseObject createAddon(String vendorToken, Addon addon) {
        Vendor vendor = (Vendor) vendorRepository.getUserFromToken(vendorToken);
        if (vendor == null) {
            return vendorBoundary.vendorNotFound();
        }

        Addon existingAddon = addonRepository.findOneByFieldName("name", addon.getName());
        if (existingAddon != null) {
            if (existingAddon.getShopId().equals(vendor.getShop().getId())) {
                return addonObjectBoundary.invalidObject("Addon with this name already exists in your shop.");
            }
        }

        if (!addon.getShopId().equals(vendor.getShop().getId())) {
            return addonObjectBoundary.invalidObject("You do not own this addon.");
        }

        String addonId = addonRepository.create(addon);

        if (addonId == null) {
            return repositoryBoundary.creationFailed("Unable to create addon in repository.");
        }

        addon.setId(addonId);
        return addonObjectBoundary.showObject(addon);
    }
}
