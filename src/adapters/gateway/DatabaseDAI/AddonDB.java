package adapters.gateway.DatabaseDAI;

import adapters.gateway.DataBaseConnector;
import entities.Interfaces.Addon;
import usecases.DataAccessInterfaces.AddonRepository;

public class AddonDB implements AddonRepository {
    DataBaseConnector database;

    @Override
    public Addon getAddon(String id) {
    }

    @Override
    public boolean save(Addon addon) {
        return false;
    }

    @Override
    public boolean createAddon(Addon addon) {
        return false;
    }

    @Override
    public boolean deleteAddon(String id) {
        return false;
    }
}
