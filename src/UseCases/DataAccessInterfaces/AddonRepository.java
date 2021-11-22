package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IAddon;
/**
        * The Addon Data access Interface
        *
        * This is an abstract interface that allows for Addons to be
        * accessed from the database.
        */
interface AddonRepository{
    /**
     *
     * @param id of addon
     * @return addon
     */
    IAddon getAddon(String id);

    /**
     *
     * @param id of addon
     * @param addon to be placed in the database
     * @return true if addon is successfully placed in the database
     */

    boolean setAddon(String id, IAddon addon);

    /**
     *
     * @param id of addon
     * @return True if addon is successfully deleted
     */

    boolean deleteAddon(String id);


}
