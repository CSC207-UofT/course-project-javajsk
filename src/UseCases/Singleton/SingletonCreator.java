package UseCases.Singleton;

import Entities.Interfaces.ISingleton;
import Entities.Interfaces.IAddon;
import Entities.RegularSingleton;
import java.util.ArrayList;

public class SingletonCreator implements SingletonCreatorInputBoundary {
    public ISingleton CreateSingleton() {
        return new RegularSingleton("","", "", 0, new ArrayList<IAddon>());
    }
}
