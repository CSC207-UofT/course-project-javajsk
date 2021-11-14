package UseCases;

import Entities.Interfaces.IAddon;
import Entities.RegularSingleton;

import java.util.List;

public class SingletonInteractor {

    public boolean deleteSingleton() {
        //TODO: implement after database stuff is figured out
        return false;
    }

    public void readSingleton(RegularSingleton singleton) {
        //TODO: Definitely a nicer way to display ingfo

        System.out.println(singleton.getDescription());
        System.out.println(singleton.getPrice());
        System.out.println(singleton.getName());
        System.out.println(singleton.getAdd_ons());


    }
    public RegularSingleton createSingleton(String name, String description, float price,
            List<IAddon>add_ons){
        return new RegularSingleton(name, description ,price, add_ons);
    }
    public void updateSingleton(RegularSingleton singleton,String name, String description, float price,
                                List<IAddon>add_ons ){
        singleton.setDescription(description);
        singleton.setName(name);
        singleton.setAllowedAddonTypes(add_ons);
        singleton.setPrice(price);
    }
}
