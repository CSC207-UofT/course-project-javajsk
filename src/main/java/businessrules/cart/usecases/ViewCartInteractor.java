package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ViewCart;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Cart;
import EntitiesUnitTest.Customer;

public class ViewCartInteractor implements ViewCart {
    ObjectBoundary<Cart> cartObjectBoundary;
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    @Override
    public ResponseObject viewCart(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if(customer == null){
            return repositoryBoundary.queryNotFound("Unable to find such cusomter.");
        }

        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
