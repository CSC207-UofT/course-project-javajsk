package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IFood;
import UseCases.DataAccessInterfaces.FoodRepository;

public class FoodDB implements FoodRepository {
    @Override
    public IFood getFood(String id) {
        return null;
    }

    @Override
    public boolean deleteFood(String id) {
        return false;
    }

    @Override
    public boolean save(IFood food) {
        return false;
    }

    @Override
    public boolean createFood(IFood food) {
        return false;
    }
}
