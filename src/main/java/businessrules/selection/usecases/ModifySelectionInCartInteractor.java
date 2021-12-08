package businessrules.selection.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.selection.inputboundaries.ModifySelectionInCart;
import entities.Cart;
import entities.Customer;
import entities.Food;
import entities.Selection;

/**
 * Use case for modifying the selection within a cart
 */
public class ModifySelectionInCartInteractor implements ModifySelectionInCart {
    /**
     * The Customer repository.
     */
    CustomerRepository customerRepository;
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository;
    /**
     * The Repository boundary.
     */
    RepositoryBoundary repositoryBoundary;
    /**
     * The Customer boundary.
     */
    CustomerBoundary customerBoundary;
    /**
     * The Cart object boundary.
     */
    ObjectBoundary<Cart> cartObjectBoundary;

    /**
     * Instantiates a new Modify selection in cart interactor.
     *
     * @param customerRepository the customer repository
     * @param foodRepository     the food repository
     * @param repositoryBoundary the repository boundary
     * @param customerBoundary   the customer boundary
     * @param cartObjectBoundary the cart object boundary
     */
    public ModifySelectionInCartInteractor(CustomerRepository customerRepository,
                                           Repository<Food> foodRepository, RepositoryBoundary repositoryBoundary,
                                           CustomerBoundary customerBoundary,
                                           ObjectBoundary<Cart> cartObjectBoundary) {
        this.customerRepository = customerRepository;
        this.foodRepository = foodRepository;
        this.repositoryBoundary = repositoryBoundary;
        this.customerBoundary = customerBoundary;
        this.cartObjectBoundary = cartObjectBoundary;
    }

    /**
     * Modifies the selections in a cart
     *
     * @param userToken         token representing the user that owns the cart
     * @param foodId            the food that has the selections
     * @param originalSelection The original selection
     * @param selections        The selection that will replace the original selection
     * @return a response object
     */
    @Override
    public ResponseObject modifySelection(String userToken, String foodId, Selection[] originalSelection,
                                          Selection[] selections) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if(customer == null){
            return repositoryBoundary.queryNotFound("No such customer found.");
        }
        Food food = foodRepository.read(foodId);
        if(food == null){
            return repositoryBoundary.queryNotFound("No such food found.");
        }

        if(!food.isValidSelections(selections)){
            return customerBoundary.error("Invalid selection provided. Please try again.");
        }
        customer.getCurrentCart().modifySelection(food, originalSelection, selections);
        if(!customerRepository.update(customer.getId(), customer)){
            return repositoryBoundary.modificationFailed("Failed to update customer's cart");
        }
        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
