package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ViewCart;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Customer;

public class ViewCartInteractor implements ViewCart {
    ObjectBoundary<Cart> cartObjectBoundary;
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;

    public ViewCartInteractor(ObjectBoundary<Cart> cartObjectBoundary, CustomerRepository customerRepository,
                              RepositoryBoundary repositoryBoundary) {
        this.cartObjectBoundary = cartObjectBoundary;
        this.customerRepository = customerRepository;
        this.repositoryBoundary = repositoryBoundary;
    }

    @Override
    public ResponseObject viewCart(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if(customer == null){
            return repositoryBoundary.queryNotFound("Unable to find such cusomter.");
        }

        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
