package EntitiesUnitTest;

import entities.Menu;
import entities.OrderBook;
import entities.Shop;
import entities.Vendor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendorTest {
    Menu menu;
    OrderBook orderBook;
    Shop shop;
    Vendor vendor;

    @BeforeEach
    void setUp(){
        menu = new Menu();
        orderBook = new OrderBook();
        shop = new Shop("00001", "JavaJShop", "Bay Street", true, menu, orderBook);
        vendor = new Vendor("12345", "Username", "Password", shop);
    }

    @Test
    void getShop() {
        Shop getshop = vendor.getShop();
        assertEquals(shop, getshop);
    }

    @Test
    void setShop() {
        Menu new_menu = new Menu();
        OrderBook new_orderBook = new OrderBook();
        Shop new_shop = new Shop("00003", "BurgerTruck", "Yonge Street",
                true, new_menu, new_orderBook);
        vendor.setShop(new_shop);
        assertEquals(new_shop, vendor.getShop());
    }

    @Test
    void deleteShop() {
        vendor.deleteShop("00001");
        assertNull(vendor.getShop());
    }
}