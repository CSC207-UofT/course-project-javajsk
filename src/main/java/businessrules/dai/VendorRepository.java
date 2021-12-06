package businessrules.dai;

import entities.Vendor;

/**
 * Interface for vendor repositories to implement
 * <p>
 * Extends both the user repository and a repository of type Vendor
 */
public interface VendorRepository extends UserRepository, Repository<Vendor> {
}
