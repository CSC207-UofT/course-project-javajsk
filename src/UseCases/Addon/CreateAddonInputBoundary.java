package UseCases.Addon;

import java.util.ArrayList;

public interface CreateAddonInputBoundary{
    /**
     * Creates an Addon
     *
     * @return Addon
     */
    Boolean createAddon(String vendorToken, String name, String description,
                       float price, ArrayList<Integer> types, boolean availability, String ID);

}
