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
    CustomerRepository customerRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Cart> cartObjectBoundary;

    /**
     * Method that modifies the selections in a cart and
     * returns a JSONObject representing the modified cart
     *
     * @param userToken         token representing the user that owns the cart
     * @param foodId            the food that has the selections
     * @param originalSelection The original selection
     * @param selections        The selection that will replace the original selection
     * @return                  JSONObject representing the current cart (after updating)
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
