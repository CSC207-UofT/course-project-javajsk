# Scenario Walkthrough

Suppose we have a situation with 2 food trucks _foodTruck, shop_ (one called bluetruck and one called shawarma), we have 2 users _userAccount_ (Jeremy and Akash) and 2 vendors _vendor_, one for each truck 
(Blue vendor: Jeff, Shawarma: Vijay). Suppose jeremy wants to order an item from shawarma food truck, he uses the DMS _system_ through the app and places an order to the foodTruck _vendor_. This in turn calls 
the _orderBook_ which then adds it to the list of orders. Vijay _vendor_ then sees this and is able to freely update the status of the order through the _vendor_ interface. 

At the same time, Akash is able to place orders to either the bluetruck or shawarma, ensuring that the _vendor_s are able to see when orders come in.

Once an order is done the Vijay or Jeff can set the order to done for the users to pick up.
