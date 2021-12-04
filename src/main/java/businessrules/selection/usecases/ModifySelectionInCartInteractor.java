package businessrules.selection.usecases;

import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.selection.inputboundaries.ModifySelectionInCart;
import EntitiesUnitTest.Cart;
import EntitiesUnitTest.Customer;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Selection;

public class ModifySelectionInCartInteractor implements ModifySelectionInCart {
    CustomerRepository customerRepository;
    Repository<Food> foodRepository;
    RepositoryBoundary repositoryBoundary;
    CustomerBoundary customerBoundary;
    ObjectBoundary<Cart> cartObjectBoundary;

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
