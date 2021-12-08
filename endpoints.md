# Endpoints 
This program contains a RESTApi which can be used to interact with the application.
This rest API can be connected to by any front-end system which has the ability to make
html-based `post`/`put`/`get` requests.

This endpoints file contains information on what endpoints are accessible in the current state of the program
and what information needs to be passed through in order for successful operation.

Please ensure that the correct port is selected and that CORS policy is enabled in the
spring boot backend to allow for connection.

In general, modification commands required a token to be specified in order to prove access rights.This token 
can be obtained by sending requests to the respective
authentication endpoints. [Vendor](###Vendor) and [Customer](###Customer)

Each entity will must be passed in as a JSON object (per specification in .toString methods or loadFromJSON methods).

### Addons
 - `POST` `/CreateAddon/{vendorToken}` Create an addon. The request body must contain an Addon object.
 - `GET` `/GetShopAddons/{shopId}` Get all addons corresponding to the given shop, (shopId = `{shopId}`).
 - `GET` `/GetAddon/{addonId}` Get information about a specific addon. 
 - `GET` `/GetAddonTypes/` Get all the different types of addons and their corresponding information.
 - `PUT` `/ModifyAddon/{vendorToken}/{addonId}` Modify a given addon. The request body must contain an Addon object.

### Cart
 - `PUT` `/AddToCart/{customerToken}/{foodId}/{shopId}` Add a food to the users cart. The request body must contain a list of selection objects.
 - `PUT` `/EmptyCart/{customerToken}` Empties the given user's cart.
 - `PUT` `/RemoveFromCart/{customerToken}` Removes the selection from the user's cart. The request body must contain a list of selections (corresponding to the desired removal).
 - `GET` `/ViewCart/{customerToken}` View the current cart.

### Customer
 - `PUT` `/CustomerLogin/{username}/{password}` Login and get a customerToken as a customer.
 - `PUT` `/CustomerSignup/{username}/{password}/{confirmed_password}` Sign up as a customer.
 - `PUT` `/ModifyCustomer/{customerToken}/{username}/{password}/{confirmed_password}` Modify customer authentication information.
 - `GET` `/ViewCustomer/{customerId}` Get information about a certain customer.

### Food
 - `PUT` `/AddSingleton/{vendorToken}/{foodId}` Add a singleton as a component of a food entity. Request body requires a singleton entity.
 - `POST` `/CreateFood/{vendorToken}` Create a food. Request body requires a food entity.
 - `GET` `/GetShopFoods/{shopId}` Get all the foods created by the given shop.
 - `PUT` `ModifyFood/{vendorToken}/{foodId}` Modify a given food by ID, request body must contain a food object.

### Menu
 - `PUT` `/AddAddonToMenu/{vendorToken}` Add an addon to the vendor's shop. Request body must be an addon owned by the shop.
 - `PUT` `/AddFoodToMenu/{vendorToken}` Add a food to the vendor's shop. Request body must be a food owned by the shop.
 - `GET` `/GetAvailableAddons/{shopId}` Get the addons which are available from a shop.
 - `GET` `/GetAvailableFoods/{shopId}` Get the foods which are available from a shop.
 - `PUT` `/RemoveAddonFromMenu/{vendorToken}` Removes the addon from the menu. Request body must be the addon on the menu.
 - `PUT` `/RemoveFoodFromMenu/{vendorToken}` Removes the food from the menu. Request body must be the food on the menu.
 - `GET` `/ViewMenu/{shopId}` Gets the shop's entire menu.

### Order
 - `PUT` `/CancelOrder/{customerToken}/{orderId}` Cancels a given order by the id.
 - `PUT` `/CompleteOrder/{vendorToken}/{orderId}` Sets the given order's status to complete.
 - `GET` `/GetNextOrder/{vendorToken}` Gets the next order in the vendor's order queue.
 - `GET` `/GetShopOrders/{vendorToken}` Gets all the orders from a given shop.
 - `GET` `/GetUsersPastOrders/{customerToken}` Gets the list of orders previously placed by the user.
 - `POST` `/PlaceOrder/{customerToken}` Places an order. (Uses the user's current cart to place an order).
 - `POST` `/SetOrderInProgress/{vendorToken}/{orderId}` Changes the state of the given order to in-progress.

### Selections
 - `POST` `/ModifyDefaultSelection/{customerToken}/{singletonId}` Modifies the default selection object for a given singleton. Request body must contain a singleton object
 - `POST` `/ModifySelectionInCart/{customerToken}/{foodId}` Modifies the customer's selection in the cart. Request body must contain original selection for a food and new selection for the food.

### Shop
 - `PUT` `/ChangeShopStatus/{vendorToken}/{newStatus}` Update the open/closed status of the vendor's shop.
 - `PUT` `/ModifyShop/{vendorToken}` Modify the shop's information. Request body must contain a shop entity.
 - `GET` `/GetShop/{shopId}` Get a shop's information.
 - `GET` `/ViewAllShops/` View all the shops currently on the database.

### Singleton
 - `POST` `/CreateSingleton/{vendorToken}` Create a singleton. Request body must contain a singleton entity.
 - `GET` `/GetShopSingletons/{shopId}` Get all singletons owned by a given shop.
 - `PUT` `/ModifySingleton/{vendorToken}/{singletonId}` Modify a singleton owned by the vendor. Request body must contain a singleton object.

### Vendor
 - `PUT` `/VendorLogin/{username}/{password}` Gets a vendorToken from vendor credentials. 
 - `PUT` `/VendorSignUp/{username}/{password}/{confirmed_password}/{shop_name}/{location}` Creates a new vendor and shop associated with that vendor.
 - `PUT` `/ModifyVendor/{vendorToken}/{username}/{password}/{confirmed_password}` Modify vendor login credentials
 - `GET` `/ViewVendor/{vendorId}` Get vendor information.