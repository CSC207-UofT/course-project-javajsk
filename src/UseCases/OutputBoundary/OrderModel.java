package UseCases.OutputBoundary;

import Entities.Interfaces.IOrder;

public interface OrderModel {
    void disPlay(IOrder order);
    void updateOrder(IOrder order);
}
