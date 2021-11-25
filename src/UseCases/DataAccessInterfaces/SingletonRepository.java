package UseCases.DataAccessInterfaces;

import Entities.Interfaces.ISingleton;

public interface SingletonRepository {

    @SuppressWarnings("UnusedReturnValue")
    ISingleton createSingleton(ISingleton singleton);

    /**
     * Get the Singleton that matches the inputted id
     *
     * @param id  The id representing the desired Singleton
     * @return    The Singleton matching the inputted id
     */
    ISingleton getSingleton(String id);


    /**
     * Delete the Singleton that matches the inputted id
     *
     * @param id  The id representing the desired Singleton
     * @return    True if successful, False otherwise
     */
    boolean deleteSingleton(String id);

    boolean save(ISingleton singleton);
}
