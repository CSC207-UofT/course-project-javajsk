package UseCases.FoodTruck;

public interface ProcessOrderInputBoundary {

    Boolean processOrder(String userToken, String shopID);
}
