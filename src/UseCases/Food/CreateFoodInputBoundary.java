package UseCases.Food;

import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;

import java.util.List;

public interface CreateFoodInputBoundary {
    IFood createFood(String id, String name, String desc, float price, List<ISingleton> singletons);
}