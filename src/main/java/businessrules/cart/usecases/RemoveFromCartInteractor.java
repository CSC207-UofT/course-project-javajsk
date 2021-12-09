package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.RemoveFromCart;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import entities.Cart;
import entities.Customer;
import entities.Food;
import entities.Selection;

/**
 * Remove from cart use case
 */
public class RemoveFromCartInteractor implements RemoveFromCart {
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository;
    /**
     * The Cart object boundary.
     */
    ObjectBoundary<Cart> cartObjectBoundary;

    /**
     * Instantiates a new Remove from cart interactor.
     *
     * @param customerRepository the customer repository
     * @param repositoryBoundary the repository boundary
     * @param foodRepository     the food repository
     * @param cartObjectBoundary the cart object boundary
     */
    public RemoveFromCartInteractor(CustomerRepository customerRepository, RepositoryBoundary repositoryBoundary,
                                    Repository<Food> foodRepository, ObjectBoundary<Cart> cartObjectBoundary) {
        this.customerRepository = customerRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.foodRepository = foodRepository;
        this.cartObjectBoundary = cartObjectBoundary;
    }

    /**
     * Method removes given selection for given food from the customer's cart that
     * corresponds to the given token
     *
     * (if customer has multiple instances of same food in their cart, the one with
     * the given selection is removed - if there are multiple with this selection, the first
     * instance is removed)
     *
     * @param userToken  token of customer currently logged in
     * @param food       food to remove from cart
     * @param selections selection corresponding to food to remove from cart
     * @return response object containing updated cart or error message to display
     */
    @Override
    public ResponseObject removeFromCart(String userToken, Food food, Selection[] selections) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if (customer == null) {
            return repositoryBoundary.queryNotFound("Unable to find such customer");
        }

        customer.getCurrentCart().removeItem(food, selections);

        if (!customerRepository.update(customer.getId(), customer)) {
            return repositoryBoundary.modificationFailed("Failed to update customer's cart.");
        }
        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
