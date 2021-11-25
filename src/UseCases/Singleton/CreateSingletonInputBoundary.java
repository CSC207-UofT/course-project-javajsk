package UseCases.Singleton;

import Entities.Interfaces.IAddon;

import java.util.List;

public interface CreateSingletonInputBoundary {
    boolean createSingleton(String userToken, String id, String name,
                            String description, float price, List<IAddon> add_ons);
}
