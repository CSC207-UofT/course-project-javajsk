package UseCases.OutputBoundary;

import Entities.Interfaces.ISingleton;

public interface SingletonModel {
    void displaySingleton(ISingleton singleton);

    void updateSingleton(ISingleton singleton);
}
