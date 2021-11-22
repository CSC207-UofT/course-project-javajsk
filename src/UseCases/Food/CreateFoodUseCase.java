package UseCases.Food;

import Entities.Interfaces.IFood;
import Entities.Interfaces.ISingleton;
import Entities.RegularFood;

import java.util.List;

public class CreateFoodUseCase implements CreateFoodInputBoundary {

    @Override
    public IFood createFood(String id, String name, String desc,
                            float price, List<ISingleton> singletons){
        return new RegularFood(id, name, desc, price, singletons);
    }
}
