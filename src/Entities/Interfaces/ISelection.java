package Entities.Interfaces;

import java.util.List;

public interface ISelection {

    /**
     * A method for getting the size of a selection
     * @return the number of different addons a selection has
     */
    int getSize();

    /**
     * A method that gets the number of an addon in a selection
     * @param addon the addon we want the amount for
     * @return the number of the addon in the selection
     */
    Integer getQuantityOfAddon(IAddon addon);

    /**
     * A method for getting the addons used in a selection
     * @return a list of the addons used
     */
    List<IAddon> getUsedAddons();

}
