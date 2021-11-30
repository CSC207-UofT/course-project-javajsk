package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.IAddon;
import UseCases.DataAccessInterfaces.AddonRepository;

public class AddonDB implements AddonRepository {
    @Override
    public IAddon getAddon(String id) {
        return null;
    }

    @Override
    public boolean save(IAddon addon) {
        return false;
    }

    @Override
    public boolean createAddon(IAddon addon) {
        return false;
    }

    @Override
    public boolean deleteAddon(String id) {
        return false;
    }
}
