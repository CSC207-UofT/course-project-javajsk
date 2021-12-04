package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.RemoveFromCart;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Cart;
import EntitiesUnitTest.Customer;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Selection;

public class RemoveFromCartInteractor implements RemoveFromCart {
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;
    Repository<Food> foodRepository;
    ObjectBoundary<Cart> cartObjectBoundary;

    @Override
    public ResponseObject removeFromCart(String userToken, Food food, Selection[] selections) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);

        if(customer == null){
            return repositoryBoundary.queryNotFound("Unable to find such customer");
        }

        customer.getCurrentCart().removeItem(food, selections);

        if(!customerRepository.update(customer.getId(), customer)){
            return repositoryBoundary.modificationFailed("Failed to update customer's cart.");
        }
        return cartObjectBoundary.showObject(customer.getCurrentCart());
    }
}
