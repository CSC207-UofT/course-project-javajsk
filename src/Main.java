import Interfaces.OrderBook;
import Interfaces.Orderable;
import Interfaces.Sellable;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        UserAccount user = new UserAccount();
        VendorAccount user1 = new VendorAccount();
        FoodTruck truck1 = new FoodTruck();
        HashMap<String, Orderable> orders = new HashMap<>();
        Queue<String> orderQueue = new LinkedList<String>();
        FiFoBook book = new FiFoBook(orders, orderQueue, 0);
        truck1.orderBook = book;
        truck1.shopName = "truck1";
        DMS dms1 = new DMS();
        dms1.shops.put("1", truck1);
        user.ID = "User";
        user.Password = "user_password";
        user1.ID = "Vendor";
        user1.Password = "vendor_password";
        MainItem burger = new MainItem("1", "Burger", 5, "A burger", true,1);
        DrinkItem Soda = new DrinkItem("2", "Soda", 2, "A can of soda", true,1);
        SideItem fries = new SideItem("3", "Fries", 1, "Fries", true,1);
        boolean running = true;
        while(running) {
            Scanner reader = new Scanner(System.in);
            boolean log_in_failed = false;

            System.out.println("Press C if you are a Customer, Press V if you are a Vendor");
            String prompt1 = reader.next();
            if (prompt1.equals("C")) {
                System.out.println("Enter User ID");
                String name = reader.next();
                System.out.println("Enter User Password");
                String pass = reader.next();
                boolean invalid = false;
                if (user.ID.equals(name) && user.Password.equals(pass)) {
                    int prompt2;

                    System.out.println("The menu has burgers(B), soda(S), and fries(F). Enter your choice");
                    String order_req = reader.next();

                    ArrayList<Sellable> order_items= new ArrayList<Sellable>();
                    if(order_req.equals("B")){
                        order_items.add(burger);
                    }
                    else if(order_req.equals("S")){
                        order_items.add(Soda);
                    }
                    else if(order_req.equals("F")){
                        order_items.add(fries);
                    }
                    else{
                        System.out.println("Invalid Choice");
                        invalid = true;
                    }
                    if(!invalid) {
                        RegularOrder order = new RegularOrder(order_items, 1);
                        dms1.addOrder("1", order);
                        System.out.println("Order has been placed");
                        System.out.println("Order Price: " + order.getTotalPrice());
                    }
                }
                else{
                    System.out.println("Invalid Log-in credentials. Try again. ");
                    log_in_failed = true;
                }


            } else if (prompt1.equals("V")) {
                System.out.println("Enter Vendor ID");
                String name1 = reader.next();
                System.out.println("Enter Vendor Password");
                String pass1 = reader.next();
                if (user1.ID.equals(name1) && user1.Password.equals(pass1)) {
                    int prompt3;
                    do {
                        System.out.println("Press 1 to view order queue");
                        prompt3 = reader.nextInt();
                    } while (prompt3 != 1);
                    Orderable order = dms1.shops.get("1").getNextOrder();
                    if(order != null) {
                        System.out.println("There are orders in the queue. Would you like to see?");
                        String prompt4 = reader.next();
                        if(prompt4.equals("Y")){
                            System.out.println("Order item: "+(order.getOrderItems()).get(0).getName());
                            String process = "N";
                            while(!process.equals("Y")){
                                System.out.println("Would you like to process order?");
                                process = reader.next();


                            }
                            order.setOrderStatus(Orderable.COMPLETED);
                            System.out.println("Order completed");
                        }



                    }
                    else{
                        System.out.println("No more orders to process!");
                    }
                }
                else{
                    System.out.println("Invalid Log-in credentials. Try again. ");
                    log_in_failed = true;
                }

            }
            if(log_in_failed){
                System.out.println("Enter credentials again");
            }
            else {
                System.out.println("Signed out. Would you like to log in again?");
                String prompt2 = reader.next();
                if(prompt2.equals("N")){
                    running = false;
                }

            }
        }
    }
}

