package UseCases.Food;

import Entities.Interfaces.ISingleton;

import java.util.List;

/**
 * A use case for modifying/updating existing food data and objects
 */
public class ChangeFoodUseCase implements ChangeFoodInputBoundary {


    @Override
    public boolean setFood(String token, String shopId, String foodId, String name, String desc, float price, List<ISingleton> singletons) {
        return false;
    }
}
