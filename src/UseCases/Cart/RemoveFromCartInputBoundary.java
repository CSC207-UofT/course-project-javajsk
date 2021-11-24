package UseCases.Cart;

import Entities.Interfaces.ICart;
import Entities.Interfaces.IFood;

public interface RemoveFromCartInputBoundary {
    boolean removeFromCart(String cartId, String foodId, int index, String token);
}
