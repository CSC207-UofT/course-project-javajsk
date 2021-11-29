package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IAddon;
/**
        * The Addon Data access Interface
        *
        * This is an abstract interface that allows for Addons to be
        * accessed from the database.
        */
public interface AddonRepository{
    /**
     *
     * @param id of addon
     * @return addon
     */
    IAddon getAddon(String id);


    //TODO: DOC
    boolean save(IAddon addon);

    boolean createAddon(IAddon addon);

    /**
     *
     * @param id of addon
     * @return True if addon is successfully deleted
     */
    IAddon createAddon(IAddon addon);

    boolean deleteAddon(String id);


}
