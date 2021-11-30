package UseCases.Addon;

import java.util.ArrayList;

/**
 * Input boundary for  CreateAddonUseCase indicates parameters needed to create new addon
 */
public interface CreateAddonInputBoundary{
    /**
     * Creates a new addon and returns whether it was successfully done
     * @param vendorToken token of current vendor logged in
     * @param name name of addon
     * @param price price of addon
     * @param types list of types that addon is
     * @param availability whether addon is available
     * @param id id of addon
     * @return whether addon was successfully created
     */
    Boolean createAddon(String vendorToken, String name,
                       float price, ArrayList<Integer> types, boolean availability, String id);

}
