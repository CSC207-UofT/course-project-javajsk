package businessrules.menu.inputboundaries;

public interface ClearMenuInputBoundary {
    /**
     * A method that creates a new menu object for the given vendor with the given data
     * @param vendorToken token of vendor that menu is for
     * @return whether menu was successfully created
     */
    boolean clearMenu(String vendorToken);
}
