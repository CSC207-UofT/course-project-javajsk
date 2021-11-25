package UseCases.Cart;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ICart;
import Entities.Interfaces.ICustomer;
import Entities.Interfaces.ISelection;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.OutputBoundary.CartModel;
import UseCases.OutputBoundary.ErrorPopup;

import java.util.HashMap;
import java.util.List;

public class AddToCartUseCase implements AddToCartInputBoundary{
    CustomerRepository customerRepository;
    CartRepository cartRepository;
    FoodRepository foodRepository;
    ErrorPopup errorDisplayer;
    CartModel cartModel;


    @Override
    public ICart addToCart(String cartId, String foodId, ISelection orderInfo, String token) {
        ICustomer customer = (ICustomer) customerRepository.getUserFromToken(token);
        ICart cart = cartRepository.getCart(cartId);
        if(customer != null) {
            if(customer.hasCart(cart)) {
                boolean result = cart.addItem(foodRepository.getFood(foodId), orderInfo);
                if (result) {
                    boolean saveSuccess = cartRepository.save(cart);
                    if (saveSuccess) {
                        cartModel.updateCart(cart);
                        return cart;
                    }
                }
            }else{
                errorDisplayer.displayError("This cart is in your account.");
            }
        }else{
            errorDisplayer.displayError("User must be logged in.");
        }
        return null;
    }
}
