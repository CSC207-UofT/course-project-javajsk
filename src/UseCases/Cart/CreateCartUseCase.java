package UseCases.Cart;

import Entities.Interfaces.ICart;
import Entities.Interfaces.ICustomer;
import Entities.Regular.RegularCart;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.OutputBoundary.CartModel;
import UseCases.OutputBoundary.ErrorPopup;

import java.util.HashMap;

public class CreateCartUseCase implements CreateCartInputBoundary{
    CustomerRepository customerRepository;
    CartRepository cartRepository;
    ErrorPopup errorDisplayer;
    CartModel cartModel;

     public CreateCartUseCase(CustomerRepository cr, CartRepository CartRep, ErrorPopup er, CartModel cartM){
         this.customerRepository = cr;
         this.cartRepository = CartRep;
         this.errorDisplayer = er;
         this.cartModel = cartM;
     }

    @Override
    public boolean createCart(String userToken, String shopId) {
        ICustomer customer = (ICustomer) customerRepository.getUserFromToken(userToken);
        if(customer != null) {
            ICart cart = new RegularCart(shopId, new HashMap<>(), null);
            // Cart gets mutated to have the id
            boolean success = cartRepository.createCart(cart);
            if(success) {
                customer.setCart(cart);
                cartModel.displayCart(cart);
                return customerRepository.save(customer);
            }

        }

        errorDisplayer.displayError("User must be logged in.");
        return false;
    }
}
