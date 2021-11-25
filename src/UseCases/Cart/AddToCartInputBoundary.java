package UseCases.Cart;

import Entities.Interfaces.*;

import java.util.HashMap;
import java.util.List;

public interface AddToCartInputBoundary {
    ICart addToCart(String cartId, String foodId, List<ISelection> orderInfo, IShop shop, String token);
}
