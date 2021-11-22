package UseCases.DataAcessInterfaces;

import Entities.Interfaces.ISingleton;

public interface SingletonAccessInterface {
    /**
     * Get the Singleton that matches the inputted id
     *
     * @param id  The id representing the desired Singleton
     * @return    The Singleton matching the inputted id
     */
    public ISingleton getSingleton(String id);

    /**
     * Replace the Singleton with the specified id with a new Singleton
     *
     * @param id         The id representing the desired Singleton to be changed
     * @param singleton  The Singleton that will replace the Singleton with ID id
     * @return           True if successful, False otherwise
     */
    public boolean setSingleton(String id, ISingleton singleton);

    /**
     * Delete the Singleton that matches the inputted id
     *
     * @param id  The id representing the desired Singleton
     * @return    True if successful, False otherwise
     */
    public boolean deleteSingleton(String id);
}
