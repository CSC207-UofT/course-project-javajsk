package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.EmptyCart;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Customer;

/**
 * Empty cart use case
 */
public class EmptyCartInteractor implements EmptyCart {
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Cart object boundary.
     */
    ObjectBoundary<Cart> cartObjectBoundary;

    /**
     * Instantiates a new Empty cart interactor.
     *
     * @param customerRepository the customer repository
     * @param repositoryBoundary the repository boundary
     * @param cartObjectBoundary the cart object boundary
     */
    public EmptyCartInteractor(CustomerRepository customerRepository, RepositoryBoundary repositoryBoundary,
                               ObjectBoundary<Cart> cartObjectBoundary) {
        this.customerRepository = customerRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.cartObjectBoundary = cartObjectBoundary;
    }

    /**
     * Method empties/clears the cart of the customer with given token
     * and returns object containing information to display
     * @param userToken token of customer currently logged in
     * @return response object containing empty cart or error message to display
     */
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
