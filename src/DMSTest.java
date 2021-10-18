import Interfaces.Orderable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class DMSTest {

    @Test
    void getShopName() {
        FoodTruck truck1 = new FoodTruck();
        HashMap<String, Orderable> orders = new HashMap<>();
        Queue<String> orderQueue = new LinkedList<String>();
        FiFoBook book = new FiFoBook(orders, orderQueue);
        truck1.orderBook = book;
        truck1.shopName = "truck1";
        DMS dms1 = new DMS();
        dms1.shops.put("1", truck1);
        assert(dms1.getShopName("1")== "truck1");
    }
}