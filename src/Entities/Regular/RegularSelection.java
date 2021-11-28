package Entities.Regular;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISelection;

import java.util.HashMap;
import java.util.List;

public class RegularSelection implements ISelection {
    HashMap<IAddon, Integer> singleSingleton;
    String ID;

    public RegularSelection(HashMap<IAddon, Integer> selection){
        this.singleSingleton = selection;
    }

    public float getPrice(){
        float sum = 0;
        for(IAddon addon : singleSingleton.keySet()){
            sum += addon.getPrice() * singleSingleton.get(addon);
        }
        return sum;
    }

    @Override
    public int size() {
        return this.singleSingleton.size();
    }

    public Integer get(IAddon addon){
        return singleSingleton.get(addon);
    }

    @Override
    public List<IAddon> getUsedAddons() {
        return (List<IAddon>) singleSingleton.keySet();

    public String getID() {

    }

}
