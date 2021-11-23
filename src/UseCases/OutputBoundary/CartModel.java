package UseCases.OutputBoundary;

import Entities.Interfaces.ICart;

public interface CartModel {
    void displayCart(ICart cart);

    void updateCart(ICart cart);
}
