package UseCases.Addon;

import Entities.Interfaces.IAddon;

public interface CreateAddonInputBoundary{
    /**
     * Creates an Addon
     *
     * @return Addon
     */
    IAddon createAddon(String vendorToken, String id, String name, String description, float price);

}
