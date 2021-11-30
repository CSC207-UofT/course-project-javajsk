package Entities.Interfaces;

import Entities.Regular.RegularSingleton;

import java.util.List;

public interface ISingleton {
    /**
     * Method for getting the id of a singleton
     * @return the id in a string
     */
    String getId();
    /**
     * Method for setting the id of a singleton
     * @param id the id of the singleton
     */
    void setId(String id);

    /**
     * A method for getting the name of a singleton
     * @return the name of the singleton
     */
    String getName();
    /**
     * A method for getting the description of a singleton
     * @return the description of the singleton
     */
    String getDescription();

    /**
     * A method for getting the default selection of a singleton
     * @return the selection of the singleton
     */
    ISelection getDefaultSelection();

    /**
     * A method for getting the allowed addons of a singleton
     * @return the list of allowed addons
     */
    List<IAddon> getAllowedAddonTypes();

    /**
     * Method for setting the singleton name
     * @param name the new name
     */
    void setName(String name);
    /**
     * Method for setting the singleton description
     * @param description the new description
     */
    void setDescription(String description);

    /**
     * Method for setting the allowed addons type for a singleton
     * @param allowedAddonTypes the list of allowed addon types
     */
    void setAllowedAddonTypes(List<IAddon> allowedAddonTypes);

}
