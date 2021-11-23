package UseCases.Cart;

import Entities.Interfaces.ICart;
import Entities.Interfaces.ICustomer;
import Entities.Interfaces.IFood;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.DataAccessInterfaces.FoodRepository;
import UseCases.OutputBoundary.CartModel;
import UseCases.OutputBoundary.ErrorPopup;

public class RemoveFromCartUseCase implements RemoveFromCartInputBoundary{
    CustomerRepository customerRepository;
    CartRepository cartRepository;
    FoodRepository foodRepository;
    ErrorPopup errorDisplayer;
    CartModel cartModel;

    @Override
    public boolean removeFromCart( String cartId, String foodid, int index, String token) {
        ICustomer customer = customerRepository.getUserFromToken(token);
        if(customer != null){
            IFood food = foodRepository.getFood(foodid);
            if (food != null) {
                ICart cart = cartRepository.getCart(cartId);
                if (customer.hasCart(cart)) {
                    boolean success = cart.removeItem(food, index);
                    if(success){
                        cartRepository.save(cart);
                        cartModel.updateCart(cart);
                        return true;
                    }
                }else {
                    errorDisplayer.displayError("Invalid cart selected.");
                }
            }else{
                errorDisplayer.displayError("Unable to find such food.");
            }
        }else{
            errorDisplayer.displayError("User must be logged in.");
        }
        return false;
    }
}
