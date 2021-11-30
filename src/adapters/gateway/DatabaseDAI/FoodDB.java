package adapters.gateway.DatabaseDAI;

import entities.Interfaces.Food;
import usecases.DataAccessInterfaces.FoodRepository;

public class FoodDB implements FoodRepository {
    @Override
    public Food getFood(String id) {
        return null;
    }

    @Override
    public boolean deleteFood(String id) {
        return false;
    }

    @Override
    public boolean save(Food food) {
        return false;
    }

    @Override
    public boolean createFood(Food food) {
        return false;
    }
}
