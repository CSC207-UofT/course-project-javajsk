package UseCasesTest.Cart;

import UseCasesTest.TestBoundaries.RAMCartObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.cart.usecases.AddToCartInteractor;
import businessrules.cart.usecases.EmptyCartInteractor;
import businessrules.cart.usecases.RemoveFromCartInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class RemoveFromCartInteractorTest {
    RAMCartObjectBoundary cartObjectBoundary;
    RAMVendorRepository vendorRepository;
    RAMShopRepository shopRepository;
    RAMAddonRepository addonRepository;
    CreateAddonInteractor createAddonInteractor;
    VendorBoundary vendorBoundary;
    RepositoryBoundary repositoryBoundary;
    ObjectBoundary<Addon> addonObjectBoundary;
    RAMFoodRepository foodRepository;
    RAMCustomerRepository customerRepository;

    @Test
    void removeFromCart() {
        cartObjectBoundary = new RAMCartObjectBoundary();
        Addon addon = new Addon("id1", "addon", 2, null, true, "shop1");
        HashMap<Addon, Integer> selec = new HashMap<>();
        selec.put(addon,10);
        Selection selection = new Selection(selec);
        Singleton[] components = new Singleton[0];
        //Singleton singleton = new Singleton("singleton1", 11, "name", "description", null, selection , true, "shop1");
        Food food = new Food("id1", "food", "regularfood", 12, components, "shop1");
        Food food1 = new Food("id2", "food2", "another food", 10, components, "shop1");
        foodRepository = new RAMFoodRepository(food);
        foodRepository.create(food1);
        repositoryBoundary = new RAMRepositoryBoundary();
        Customer customer = new AddToCartInteractorTest().setUp();
        customerRepository = new RAMCustomerRepository(customer);
        Selection[] selections = new Selection[0];
        Cart cart = new Cart();
        customer.setCurrentCart(cart);
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
        RemoveFromCartInteractor removeFromCartInteractor = new RemoveFromCartInteractor(customerRepository, repositoryBoundary, foodRepository, cartObjectBoundary);
        addToCartInteractor.addToCart(customer.getId(),food.getShopId(), food.getId(), null);
        addToCartInteractor.addToCart(customer.getId(), food1.getShopId(), food1.getId(), null);
        assertTrue(cart.getContents().containsKey(food));
        assertTrue(cart.getContents().containsKey(food1));
        ResponseObject responseObject = removeFromCartInteractor.removeFromCart(customer.getId(), food1, null);
        //assertSame(customer.getCurrentCart().getContents().keySet(), responseObject.getContents());
        //assertEquals(responseObject.getContents(), food);
        assertTrue(customer.getCurrentCart().getContents().containsKey(food));
        assertFalse(customer.getCurrentCart().getContents().containsKey(food1));



    }
}