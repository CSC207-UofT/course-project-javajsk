package UseCasesTest.Cart;

import UseCasesTest.TestBoundaries.RAMAddonObjectBoundary;
import UseCasesTest.TestBoundaries.RAMCartObjectBoundary;
import UseCasesTest.TestBoundaries.RAMRepositoryBoundary;
import UseCasesTest.daitesters.*;
import businessrules.addon.usecases.CreateAddonInteractor;
import businessrules.cart.usecases.AddToCartInteractor;
import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.VendorBoundary;
import entities.*;
import org.junit.jupiter.api.Test;

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
        Food food = new Food("id1", "food", "regularfood", 12, null, "shop1");
        foodRepository = new RAMFoodRepository(food);
        repositoryBoundary = new RAMRepositoryBoundary();
        AddToCartInteractor addToCartInteractor = new AddToCartInteractor(foodRepository, cartObjectBoundary, customerRepository, repositoryBoundary);
         Customer customer = setUp();
         Cart cart = new Cart();
         customer.setCurrentCart(cart);
         addToCartInteractor.addToCart(customer.getId(), "shop1", food.getId(), null);
         assertTrue(customer.getCurrentCart().getContents().containsKey(food));


    }
}