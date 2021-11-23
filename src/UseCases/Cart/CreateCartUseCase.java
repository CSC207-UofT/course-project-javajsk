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
    public boolean createCart(String userToken) {
        ICustomer customer = customerRepository.getUserFromToken(userToken);
        if(customer != null) {
            ICart cart = cartRepository.createCart(customer.getId());
            customer.addCart(cart);
            cartModel.displayCart(cart);
            return customerRepository.save(customer);
        }

        errorDisplayer.displayError("User must be logged in.");
        return false;
    }
}
