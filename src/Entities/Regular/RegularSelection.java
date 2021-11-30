package Entities.Regular;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISelection;

import java.util.HashMap;
import java.util.List;

public class RegularSelection implements ISelection {
    HashMap<IAddon, Integer> singleSingleton;

    /**
     * Creates a RegularSelection object
     * @param selection the addons a customer has chosen for a singleton
     */
    public RegularSelection(HashMap<IAddon, Integer> selection){
        this.singleSingleton = selection;
    }

    /**
     * A method for getting the size of a selection
     * @return the number of different addons a selection has
     */
    public int getSize() {
        return this.singleSingleton.size();
    }

    /**
     * A method that gets the number of an addon in a selection
     * @param addon the addon we want the amount for
     * @return the number of the addon in the selection
     */
    public Integer get(IAddon addon){
        return singleSingleton.get(addon);
    }

    /**
     * A method for getting the addons used in a selection
     * @return a list of the addons used
     */
    public List<IAddon> getUsedAddons() {
        return  singleSingleton.keySet().stream().toList();
    }

}
