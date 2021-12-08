package businessrules.cart.usecases;

import businessrules.cart.inputboundaries.AddToCart;
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
 * Add to cart use case
 */
public class AddToCartInteractor implements AddToCart {
    /**
     * The Food repository.
     */
    Repository<Food> foodRepository;
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
     * Instantiates a new Add to cart interactor.
     *
     * @param foodRepository     the food repository
     * @param cartObjectBoundary the cart object boundary
     * @param customerRepository the customer repository
     * @param repositoryBoundary the repository boundary
     */
    public AddToCartInteractor(Repository<Food> foodRepository, ObjectBoundary<Cart> cartObjectBoundary,
                               CustomerRepository customerRepository, RepositoryBoundary repositoryBoundary) {
        this.foodRepository = foodRepository;
        this.cartObjectBoundary = cartObjectBoundary;
        this.customerRepository = customerRepository;
        this.repositoryBoundary = repositoryBoundary;
    }

    /**
     * Method that adds given food and selection to cart with given shop id
     *
     * Requires customer to be logged in (valid customer token)
     * @param userToken token of customer currently logged in
     * @param shopId shop id
     * @param foodId food id
     * @param selection customer's selection (for customization)
     * @return response object containing cart or error message to display
     */
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
