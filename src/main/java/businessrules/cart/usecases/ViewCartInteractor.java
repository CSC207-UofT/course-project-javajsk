package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.ViewCart;
import businessrules.dai.CustomerRepository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Customer;

/**
 * View cart use case
 */
public class ViewCartInteractor implements ViewCart {
    /**
     * The Cart object boundary.
     */
    ObjectBoundary<Cart> cartObjectBoundary;
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;

    /**
     * Instantiates a new View cart interactor.
     *
     * @param cartObjectBoundary the cart object boundary
     * @param customerRepository the customer repository
     * @param repositoryBoundary the repository boundary
     */
    public ViewCartInteractor(ObjectBoundary<Cart> cartObjectBoundary, CustomerRepository customerRepository,
                              RepositoryBoundary repositoryBoundary) {
        this.cartObjectBoundary = cartObjectBoundary;
        this.customerRepository = customerRepository;
        this.repositoryBoundary = repositoryBoundary;
    }

    /**
     * Method that returns information to display the cart of the customer
     * with the given token
     * @param userToken token of customer that is currently logged in
     * @return response object containing customer's cart or error message to display
     */
    @Override
    public ResponseObject viewCart(String userToken) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if(customer == null){
            return repositoryBoundary.queryNotFound("Unable to find such customer.");
        }

        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
