package Adapters.Gateway.DatabaseDAI;

import Entities.Interfaces.ISingleton;
import UseCases.DataAccessInterfaces.SingletonRepository;

public class SingletonDB implements SingletonRepository {
    @Override
    public ISingleton createSingleton(ISingleton singleton) {
        return null;
    }

    @Override
    public ISingleton getSingleton(String id) {
        return null;
    }

    @Override
    public boolean deleteSingleton(String id) {
        return false;
    }

    @Override
    public boolean save(ISingleton singleton) {
        return false;
    }
}
