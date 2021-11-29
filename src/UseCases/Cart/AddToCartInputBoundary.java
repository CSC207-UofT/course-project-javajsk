package UseCases.Cart;

import Entities.Interfaces.*;

import java.util.List;

/**
 * Input boundary for AddToCartUseCase indicates the necessary parameters for use case
 */
public interface AddToCartInputBoundary {
    ICart addToCart(String cartId, String foodId, List<ISelection> orderInfo, IShop shop, String token);
}
