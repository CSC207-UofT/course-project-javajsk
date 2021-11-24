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
     * Replace the Singleton with the specified id with a new Singleton
     *
     * @param id         The id representing the desired Singleton to be changed
     * @param singleton  The Singleton that will replace the Singleton with ID id
     * @return           True if successful, False otherwise
     */
    boolean setSingleton(String id, ISingleton singleton);

    /**
     * Delete the Singleton that matches the inputted id
     *
     * @param id  The id representing the desired Singleton
     * @return    True if successful, False otherwise
     */
    boolean deleteSingleton(String id);

    boolean save(ISingleton singleton);
}
