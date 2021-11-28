package UseCases.Cart;

import Entities.Interfaces.*;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.OutputBoundary.CartModel;
import UseCases.OutputBoundary.ErrorPopup;

import java.util.List;


public class AddToCartUseCase implements AddToCartInputBoundary{
    CustomerRepository customerRepository;
    CartRepository cartRepository;
    FoodRepository foodRepository;
    ErrorPopup errorDisplayer;
    CartModel cartModel;


    @Override
    public ICart addToCart(String cartId, String foodId, List<ISelection> orderInfo, IShop shop, String token) {
        ICustomer customer = (ICustomer) customerRepository.getUserFromToken(token);
        ICart cart = cartRepository.getCart(cartId);
        if(customer != null) {
            if(customer.getCart().equals(cart)) {
                IFood food = foodRepository.getFood(foodId);
                if(food.isValidAddons(orderInfo) && shop.isValidAddons(orderInfo)) {
                    boolean result = cart.addItem(food, orderInfo);
                    if (result) {
                        boolean saveSuccess = cartRepository.save(cart);
                        if (saveSuccess) {
                            cartModel.updateCart(cart);
                            return cart;
                        }
                    }
                }else{
                    errorDisplayer.displayError("Invalid selection for addons.");
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
