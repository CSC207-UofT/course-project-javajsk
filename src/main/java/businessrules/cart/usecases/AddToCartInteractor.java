package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.AddToCart;
import businessrules.dai.CustomerRepository;
import businessrules.dai.Repository;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import EntitiesUnitTest.Cart;
import EntitiesUnitTest.Customer;
import EntitiesUnitTest.Food;
import EntitiesUnitTest.Selection;

public class AddToCartInteractor implements AddToCart {
    Repository<Food> foodRepository;
    ObjectBoundary<Cart> cartObjectBoundary;
    CustomerRepository customerRepository;
    RepositoryBoundary repositoryBoundary;

    // This is required.
    @SuppressWarnings("DuplicatedCode")
    @Override
    public ResponseObject addToCart(String userToken, String shopId, String foodId, Selection[] selection) {
        Customer customer = (Customer) customerRepository.getUserFromToken(userToken);
        if(customer == null){
            return repositoryBoundary.queryNotFound("No such customer found.");
        }

        Food food = foodRepository.read(foodId);

        if (food == null) {
            return repositoryBoundary.queryNotFound("No such food found.");
        }

        if(!food.isValidSelections(selection)){
            return cartObjectBoundary.invalidObject("Selections are invalid for the given food.");
        }


        Cart cart = customer.getCurrentCart();
        boolean success = cart.addItem(food, selection);
        if(!success){
            return repositoryBoundary.invalidInput("Unable to add food to cart, perhaps cart needs to be reset first.");
        }

        if(!customerRepository.update(customer.getId(), customer)){
            return repositoryBoundary.modificationFailed("Failed to add item to customer's cart.");
        }
        return cartObjectBoundary.showObject(cart);
    }
}
