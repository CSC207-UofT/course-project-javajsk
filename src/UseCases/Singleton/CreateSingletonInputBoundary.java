package UseCases.Singleton;

import Entities.Interfaces.IAddon;
import Entities.Interfaces.ISelection;

import java.util.List;

public interface CreateSingletonInputBoundary {
    boolean createSingleton(String userToken, String id, String name,
                            String description, float price, List<IAddon> add_ons, ISelection defaultSel);
}
