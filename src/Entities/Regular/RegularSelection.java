package Entities.Regular;

import Entities.Interfaces.IAddon;

import java.util.HashMap;
import java.util.List;

public class RegularSelection {
    List<HashMap<IAddon, Integer>> singleFood;

    public RegularSelection(List<HashMap<IAddon, Integer>> selection){
        this.singleFood = selection;
    }

    public float getPrice(){
        float sum = 0;
        for(HashMap<IAddon, Integer> singletonData : this.singleFood){
            for(IAddon addon : singletonData.keySet()){
                sum += addon.getPrice() * singletonData.get(addon);
            }
        }
        return sum;
    }

    public HashMap<IAddon, Integer> get(int index){
        return singleFood.get(index);
    }
}
