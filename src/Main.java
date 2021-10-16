import Interfaces.OrderBook;
import Interfaces.Orderable;

import java.util.Objects;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        UserAccount user = new UserAccount();
        VendorAccount user1 = new VendorAccount();
        FoodTruck truck1 = new FoodTruck();
        FiFoBook book = new FiFoBook(null, null);
        truck1.ID = "truck1";
        DMS dms1 = new DMS();
        dms1.shops.put("1", truck1);
        user.ID = "name";
        user.password = "password";
        user1.ID = "name1";
        user1.password = "password1";
        Scanner reader = new Scanner(System.in);
        System.out.println("Press 1 if you are a Customer, Press 0 if you are a Vendor");
        int prompt1 = reader.nextInt();
        if (prompt1 == 1) {
            System.out.println("Enter User ID");
            String name = reader.next();
            System.out.println("Enter User Password");
            String pass = reader.next();
            if (user.ID.equals(name) && user.password.equals(pass)) {
                int prompt2;
                do {
                    System.out.println("Press 1 to place an order");
                    prompt2 = reader.nextInt();
                } while (prompt2 != 1);
                RegularOrder order = new RegularOrder();
                dms1.addOrder("1", order);
                System.out.println("Order has been placed");
            }


        } else if (prompt1 == 0) {
            System.out.println("Enter Vendor ID");
            String name1 = reader.next();
            System.out.println("Enter Vendor Password");
            String pass1 = reader.next();
            if (user1.ID.equals(name1) && user1.password.equals(pass1)) {
                int prompt3;
                do {
                    System.out.println("Press 1 to view current orders");
                    prompt3 = reader.nextInt();
                } while (prompt3 != 1);
                Orderable order = dms1.shops.get("1").getNextOrder();
                order.setOrderStatus(Orderable.COMPLETED);
                System.out.println("Order completed");
            }

        }
    }
}

