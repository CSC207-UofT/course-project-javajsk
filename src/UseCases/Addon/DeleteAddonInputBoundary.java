package UseCases.Addon;

import Entities.RegularAddon;

public interface DeleteAddonInputBoundary {
    boolean deleteAddon(String userToken, String ID);

}
