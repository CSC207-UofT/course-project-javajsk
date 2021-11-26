package UseCases.DataAccessInterfaces;

import Entities.Interfaces.IFood;

/**
 * The FoodAccessInterface Interface
 *
 * This is an interface that allows for the attainment, changing, and deletion
 * of a RegularFood objects in the higher level data storage.
 */
public interface FoodRepository {
        /**
         *  A method that returns the desired food object from
         *  the higher level data storage.
         *
         * @param id The associated id of the food object.
         * @return Return the RegularFood object with the associated id.
         */
        IFood getFood(String id);

        /**
         *  A method that deletes a desired food object in
         *  the higher level data storage.
         *
         * @param id The associated id of the food object.
         *
         * @return Return whether the deletion has been made.
         */
        boolean deleteFood(String id);


        /**
         * A method that saves the desired food object to the high level data storage
         * @param food food object to be saved
         * @return whether food object was succesfully saved
         */
        boolean save(IFood food);

        boolean createFood(IFood food);
}
