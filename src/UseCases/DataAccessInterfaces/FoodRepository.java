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
         *  A method that returns the desired RegularFood object from
         *  the higher level data storage.
         *
         * @param id The associated id of the RegularFood object.
         * @return Return the RegularFood object with the associated id.
         */
        public IFood getFood(String id);

        /**
         *  A method that changes information of the desired RegularFood object in
         *  the higher level data storage.
         *
         * @param id The associated id of the RegularFood object.
         * @param new_food A new RegularFood object that has the desired new information
         *
         * @return Return whether the changes to the RegularFood object have been made.
         */
        public Boolean setFood(String id, IFood new_food);

        /**
         *  A method that deletes a desired RegularFood object in
         *  the higher level data storage.
         *
         * @param id The associated id of the RegularFood object.
         *
         * @return Return whether the deletion has been made.
         */
        public Boolean deleteFood (String id);

        boolean save(IFood food);
}
