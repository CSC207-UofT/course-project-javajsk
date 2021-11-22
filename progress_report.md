# Progress Report

## Problem Summary

The idea of this project is to create a system that allows students of UofT to pre-order items from the food trucks 
commonly found on St. George st. The app consists of many different object-oriented principles and contains objects that are designed
to model FoodTrucks, User, Vendors (i.e. the owners of the foodTrucks) and Menu items.
Currently, we are not including _"Cloud"_ functionality and limiting the program to a local, networkless scope.

The skeleton program allows users to use a CLI-based interface to see what food is available and place an order. The vendor will then be able
to process the order and change the status. This also means that the vendor will be able to see the list of all orders that have been placed.

## Questions we are struggling with
Currently, when designing our program, we are finding it difficult to ensure that our program
follows the rules and principles of clean architecture.

In addition, we found that the SOLID principles can make it quite difficult to construct
architecture for the program. 

Here are the main questions that we are currently facing:
 - Does our program follow the SOLID principles.
 - Is our code breaking any of the clean architecture principles.
 - How much of our architecture will we have to change when this app is changed to a cloud based applicaiton.
   - Will we need to refractor or redesign classes due to loading menu items and user accounts?
   - What file formats are we going to use to ensure security and data redundancy.
   - How are we going to prevent concurrent operations from causing issues?
 - Will we need to consider payment systems in the application?
 - What kind of frameworks are we going to be using for the user interfaces?

## What we think we have succeeded at
Our skeleton program seems to be quite clean and easy to modify, this means that our interfaces and objects are much 
more plug and play, allowing easy refractoring.

We also feel like the features we currently have cover a wide variety of domains.

In addition, the code covers a vast set of edge cases and has validation and verification.

## Group Participation Summary

 - Akash
   - Suggested formatting and function ideas for parts of the interfaces ([DataSystem](src/Interfaces/DataSystem.java), [DMS](src/DMS.java) and [Menu](src/Interfaces/Menu.java)).
   - Contributed to the CLI interface for the skeleton program 
   - Designed parts of the architecture and ensuring SOLID Principles.
 - Avinash
   - Wrote the login system for the CLI interface
   - Designed the [Account](src/Account.java), [UserAccount](src/UserAccount.java), and [VendorAccount](src/VendorAccount.java) classes.
   - Wrote parts of the Specification
 - Jeffery
   - Designed significant portions of the app's architecture.
   - Contributed to the CRC Model 
   - Contributed to [RegularOrder](src/RegularOrder.java), [RegularMenu](src/RegularMenu.java), and [Shop](src/Interfaces/Shop.java).
 - Jeremy
   - Contributed to [FiFo](src/FiFoBook.java), [RegularMenu](src/RegularMenu.java) and [Orderable](src/Interfaces/Orderable.java).
   - Contributed to the [Progress Report](progress_report.md)
   - Helped write the specification.
 - Kathy
   - Wrote the specification
   - Contributed [Orderable](src/Interfaces/Orderable.java),[Sellable](src/Interfaces/Sellable.java) and [Account](/src/Account.java)
   - Contributed to the CRC Model
 - Stephanie
   - Contributed to the specification
   - Wrote [Shop](src/Interfaces/Shop.java), [FoodTruck](src/FoodTruck.java), and [OrderBook](src/Interfaces/OrderBook.java)
   - Contributed to the CRC Model
 - Vijay
   - Contributed to the [Orderalble](src/Interfaces/Orderable.java), [FifoBook](src/FiFoBook.java), and [MainItem](src/MainItem.java) classes
   - Contributed to the [Progress Report](progress_report.md).
   - Helped design parts of the architecture.