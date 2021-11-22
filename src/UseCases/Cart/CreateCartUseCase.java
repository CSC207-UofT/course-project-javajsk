package UseCases.Cart;

import Entities.Interfaces.ICart;
import Entities.Interfaces.ICustomer;
import UseCases.DataAccessInterfaces.CartRepository;
import UseCases.DataAccessInterfaces.CustomerRepository;

public class CreateCartUseCase implements CreateCartInputBoundary{
    CustomerRepository customerRepository;
    CartRepository cartRepository;

     public CreateCartUseCase(CustomerRepository cr, CartRepository CartRep){
         this.customerRepository = cr;
         this.cartRepository = CartRep;
     }

    @Override
    public boolean createCart(String userToken) {
        ICustomer customer = customerRepository.getUserFromToken(userToken);
        if(customer != null) {
            ICart cart = cartRepository.createCart(customer.getId());
            customer.addCart(cart);
            //return customerRepository.setCustomer(customer.getId(), customer);
            return customerRepository.save(customer);
        }
        return false;
    }
}
