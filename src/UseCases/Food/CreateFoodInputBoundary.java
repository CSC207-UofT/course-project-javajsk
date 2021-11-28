package UseCases.Food;

import Entities.Interfaces.ISingleton;

import java.util.List;

public interface CreateFoodInputBoundary {
    boolean createFood(String token, String id, String name, String desc, float price, List<ISingleton> singletons);
}