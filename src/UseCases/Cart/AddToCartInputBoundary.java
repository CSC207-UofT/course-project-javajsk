package UseCases.Cart;

import Entities.Interfaces.*;

public interface AddToCartInputBoundary {
    ICart addToCart(String cartId, String foodId, List<ISelection> orderInfo, IShop shop, String token);
}
