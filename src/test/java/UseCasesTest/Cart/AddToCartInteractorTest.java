package UseCasesTest.Cart;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMCartObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.cart.usecases.AddToCartInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class AddToCartInteractorTest {
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
    Customer setUp(){
        Customer customer = new Customer("customer1", "username", "password");
        customerRepository = new RAMCustomerRepository(customer);
        return customer;

    }
    @Test
    void addToCart() {
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
        Customer customer = setUp();
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
         Cart cart = new Cart();
         customer.setCurrentCart(cart);
         assertTrue(customer.getCurrentCart().isEmpty());
         addToCartInteractor.addToCart(customer.getId(), "shop1", food.getId(), null);
         assertTrue(customer.getCurrentCart().getContents().containsKey(food));
         ResponseObject responseObject = new ResponseObject(0, "No such customer found.", "");
        assertSame(responseObject.getMessage(), addToCartInteractor.addToCart("faker", "shop1", food.getId(), null).getMessage());




    }
}