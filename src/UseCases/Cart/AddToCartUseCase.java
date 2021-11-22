package UseCases.Cart;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.ISelection;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.FoodRepository;

import java.util.HashMap;
import java.util.List;

public class AddToCartUseCase implements AddToCartInputBoundary{
    CustomerRepository customerRepository;
    CartRepository cartRepository;
    FoodRepository foodRepository;


    @Override
    public ICart addToCart(String cartId, String foodId, ISelection orderInfo, String token) {
        // ADD USER stuff.
        ICart cart = cartRepository.getCart(cartId);
        boolean result = cart.addItem(foodRepository.getFood(foodId), orderInfo);
        if(result){
            boolean saveSuccess = cartRepository.save(cart);
            if(saveSuccess){
                return cart;
            }
        }
        return null;
    }
}
