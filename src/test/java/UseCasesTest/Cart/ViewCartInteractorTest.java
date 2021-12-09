package UseCasesTest.Cart;

import UseCasesTest.TestBoundaries.RAMCartObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.cart.usecases.AddToCartInteractor;
import businessrules.cart.usecases.ViewCartInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;


import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class ViewCartInteractorTest {
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
    void viewCart() {
        Customer customer = new AddToCartInteractorTest().setUp();
        Cart cart = new Cart();
        customer.setCurrentCart(cart);
        assertTrue(customer.getCurrentCart().isEmpty());
        cartObjectBoundary = new RAMCartObjectBoundary();
        Singleton[] components = new Singleton[0];
        Food food = new Food("id1", "food", "regularfood", 12, components, "shop1");
        foodRepository = new RAMFoodRepository(food);
        repositoryBoundary = new RAMRepositoryBoundary();
        customerRepository = new RAMCustomerRepository(customer);
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
        addToCartInteractor.addToCart(customer.getId(), "shop1", food.getId(), null);
        ViewCartInteractor viewCartInteractor = new ViewCartInteractor(cartObjectBoundary, customerRepository, repositoryBoundary);
       ResponseObject responseObject= viewCartInteractor.viewCart(customer.getId());
       assertEquals(responseObject.getContents(), customer.getCurrentCart());
       assertFalse(customer.getCurrentCart().isEmpty());
    }
}