package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.EmptyCart;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Cart;
import EntitiesUnitTest.Customer;

public class EmptyCartInteractor implements EmptyCart {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Cart> cartObjectBoundary;

    @Override
    public ResponseObject emptyCart(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if(customer == null){
            return repositoryBoundary.queryNotFound("No such customer found.");
        }

        customer.emptyCart();

        if(!customerRepository.update(customer.getId(), customer)){
            return repositoryBoundary.modificationFailed("Failed to update customer's cart.");
        }

        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
