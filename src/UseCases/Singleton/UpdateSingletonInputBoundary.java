package UseCases.Singleton;

import Entities.Regular.RegularSingleton;

public interface UpdateSingletonInputBoundary {
    boolean updateSingleton(String userToken, String singletonID, RegularSingleton singleton);
}
