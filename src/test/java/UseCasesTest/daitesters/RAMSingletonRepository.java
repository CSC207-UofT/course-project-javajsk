package UseCasesTest.daitesters;

import businessrules.dai.Repository;
import com.fasterxml.jackson.databind.cfg.ConstructorDetector;
import entities.Addon;
import entities.Singleton;

import java.util.ArrayList;
import java.util.List;

public class RAMSingletonRepository implements Repository<Singleton> {
    List<Singleton> singletonList;

    public RAMSingletonRepository(Singleton singleton){
        List<Singleton> singletonList = new ArrayList<>();
        singletonList.add(singleton);
        this.singletonList = singletonList;


    }
    @Override
    public Singleton read(String id) {
        for (Singleton singleton: singletonList){
            if(singleton.getId().equals(id)){
                return singleton;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Singleton item) {
        for (Singleton singleton:singletonList){
            if (singleton.getId().equals(id)){
                singletonList.add(item);
                singletonList.remove(singleton);
                return true;
            }
        }
        return false;
    }

    @Override
    public String create(Singleton item) {
        for (Singleton singleton: singletonList){
            if (item.getId().equals(singleton.getId()))
                return "Item already exists";
        }
        singletonList.add(item);
        return "Item created";
    }

    @Override
    public List<Singleton> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Singleton findOneByFieldName(String fieldName, String needle) {
        for(Singleton singleton: singletonList){
            if(singleton.getName().equals(needle)){
                return singleton;
            }
        }
        return null;
    }
}
