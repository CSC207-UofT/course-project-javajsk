package UseCasesTest.Cart;

import UseCasesTest.TestBoundaries.RAMCartObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.cart.usecases.AddToCartInteractor;
import businessrules.cart.usecases.EmptyCartInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EmptyCartInteractorTest {
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
    void emptyCart() {
        cartObjectBoundary = new RAMCartObjectBoundary();
        Addon addon = new Addon("id1", "addon", 2, null, true, "shop1");
        HashMap<Addon, Integer> selec = new HashMap<>();
        selec.put(addon,10);
        Selection selection = new Selection(selec);
        Singleton[] components = new Singleton[0];
        Singleton singleton = new Singleton("singleton1", 11, "name", "description", null, selection , true, "shop1");
        Food food = new Food("id1", "food", "regularfood", 12, components, "shop1");
        foodRepository = new RAMFoodRepository(food);
        repositoryBoundary = new RAMRepositoryBoundary();
        Customer customer = new AddToCartInteractorTest().setUp();
        customerRepository = new RAMCustomerRepository(customer);
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
        EmptyCartInteractor emptyCartInteractor = new EmptyCartInteractor(customerRepository, repositoryBoundary, cartObjectBoundary);
        Cart cart = new Cart();
        customer.setCurrentCart(cart);
        assertTrue(customer.getCurrentCart().isEmpty());
        addToCartInteractor.addToCart(customer.getId(), "shop1", food.getId(), null);
        assertTrue(cart.getContents().containsKey(food));
        emptyCartInteractor.emptyCart(customer.getId());
        assertTrue(cart.isEmpty());


    }
}