package businessrules.dai;

import entities.Customer;

/**
 * Interface for customer repositories to implement
 *
 * Extends both the user repository and a repository of type Customer
 */
public interface CustomerRepository extends UserRepository, Repository<Customer>{
}
