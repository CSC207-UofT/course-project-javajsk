package UseCases;
import Entities.Interfaces.ISingleton;
import Entities.RegularFood;

import java.util.List;

public class FoodInteractor {

    public void readFood(RegularFood food){
        System.out.println(food.getDescription());
        System.out.println(food.getPrice());
        System.out.println(food.getName());
        System.out.println(food.getComponents());
        System.out.println(food.getDescription());
    }

    public boolean deleteFood(RegularFood food){
        //TODO: Need to implement this after we figure out databases
        return false;
    }
    public RegularFood createFood(String name, String description, float price,
                                  List<ISingleton> components){
        return new RegularFood(name, description, price, components);
    }
    public void updateFood(RegularFood food, String name, String description, float price,
                                  List<ISingleton> components){
            food.setName(name);
            food.setDescription(description);
            food.setPrice(price);
            food.setComponents(components);

    }
}
