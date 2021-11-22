package UseCases.Addon;

import Entities.Interfaces.IAddon;

public interface CreateAddonInputBoundary {
    /**
     * Creates an Addon
     *
     * @return Addon
     */
    IAddon createAddon(String name, String id, String description, float price);

}
