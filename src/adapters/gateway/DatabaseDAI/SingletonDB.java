package adapters.gateway.DatabaseDAI;

import entities.Interfaces.Singleton;
import usecases.DataAccessInterfaces.SingletonRepository;

public class SingletonDB implements SingletonRepository {
    @Override
    public Singleton createSingleton(Singleton singleton) {
        return null;
    }

    @Override
    public Singleton getSingleton(String id) {
        return null;
    }

    @Override
    public boolean deleteSingleton(String id) {
        return false;
    }

    @Override
    public boolean save(Singleton singleton) {
        return false;
    }
}
