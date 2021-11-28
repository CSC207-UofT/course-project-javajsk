package UseCases.Cart;

import Entities.Interfaces.*;

public interface AddToCartInputBoundary {
    ICart addToCart(String cartId, String foodId, ISelection orderInfo, String token);
}
