package UseCases.Singleton;

import Entities.Regular.RegularSingleton;
import Entities.Interfaces.ISingleton;

public interface UpdateSingletonInputBoundary {
    boolean updateSingleton(String userToken, String singletonID, RegularSingleton singleton);
}
