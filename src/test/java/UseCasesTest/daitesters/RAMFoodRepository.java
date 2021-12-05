package UseCasesTest.daitesters;

import businessrules.dai.Repository;
import entities.Food;
import entities.Singleton;
import entities.Vendor;

import java.util.ArrayList;
import java.util.List;

public class RAMFoodRepository implements Repository<Food> {
    List<Food> foodList;
    public RAMFoodRepository(Food food){
        List<Food> foodList = new ArrayList<>();
        foodList.add(food);
        this.foodList = foodList;
    }
    @Override
    public Food read(String id) {
        for (Food food: foodList){
            if(food.getId().equals(id)){
                return food;
            }
        }
        return null;
    }

    @Override
    public boolean update(String id, Food item) {
        for (Food food :foodList){
            if (food.getId().equals(id)){
                foodList.add(item);
                foodList.remove(food);
                return true;
            }
        }
        return false;
    }

    @Override
    public String create(Food item) {
        for (Food food: foodList){
            if (item.getId().equals(food.getId()))
                return "Item already exists";
        }
        foodList.add(item);
        return "Item created";
    }

    @Override
    public List<Food> readMultiple(String parameter, String needle) {
        return null;
    }

    @Override
    public Food findOneByFieldName(String fieldName, String needle) {
        for(Food food: foodList){
            if(food.getName().equals(needle)){
                return food;
            }
        }
        return null;
    }
}
