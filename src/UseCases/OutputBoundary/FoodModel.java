package UseCases.OutputBoundary;

import Entities.Interfaces.IFood;

public interface FoodModel {
    void displayFood(IFood food);

    void updateFood(IFood food);
}
