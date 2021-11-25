package UseCases.Cart;

import Entities.Interfaces.ICart;
import Entities.Interfaces.ICustomer;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;
import UseCases.OutputBoundary.CartModel;
import UseCases.OutputBoundary.ErrorPopup;

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
    public boolean createCart(String userToken, String cartType) {

        CartFactory cf = new CartFactory();
        ICustomer customer = (ICustomer) customerRepository.getUserFromToken(userToken);
        if(customer != null) {
            ICart cart =cf.get(cartType);
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
