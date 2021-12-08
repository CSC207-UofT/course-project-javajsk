package UseCasesTest.daitesters;

import businessrules.dai.Repository;
import entities.Addon;

import java.util.ArrayList;
import java.util.List;
public class RAMAddonRepository implements Repository<Addon> {
    List<Addon> addonlist;
    public RAMAddonRepository(Addon addon){
        List<Addon> addonlist = new ArrayList<>();
        addonlist.add(addon);
        this.addonlist = addonlist;
    }

    @Override
    public Addon read(String id) {
        for (Addon addon : addonlist){
            if (addon.getId().equals(id)){
                return addon;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Addon item){
            for (Addon addon: addonlist){
                if (addon.getId().equals(id)){
                    addonlist.add(item);
                    addonlist.remove(addon);
                    return true;
                }
            }
            return false;
        }

    @Override
    public String create(Addon item) {
        for (Addon addon: addonlist){
            if (item.getId().equals(addon.getId()))
                return "Item already exists";
        }
        addonlist.add(item);
        return "Item created";
    }

    @Override
    public List<Addon> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Addon findOneByFieldName(String fieldName, String needle) {
        for(Addon addon:addonlist){
            if(addon.getName().equals(needle)){
                return addon;
            }
        }
        return null;
    }
}


