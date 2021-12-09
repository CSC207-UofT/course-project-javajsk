package businessrules.dai;

import java.util.List;

/**
 * Interface for all repositories to implement
 *
 * @param <T> the type of entity the repository will be storing
 */
public interface Repository<T> {
    /**
     * Method for reading an entry from the repository
     *
     * @param id the id of the entry
     * @return the corresponding entity
     */
    T read(String id);

    /**
     * Method for updating an entry in the repository
     *
     * @param id   the id of the entry to update
     * @param item the updated information
     * @return whether the update was successful
     */
    boolean update(String id, T item);

    /**
     * Method for adding a new entry to the repository
     *
     * @param item the entity to add
     * @return the id of the entry in the repository
     */
    String create(T item);

    /**
     * Method for reading multiple entries from the repository
     *
     * @param parameter the parameter to search by
     * @param needle    the value of the parameter to find
     * @return a list of entities that match the requirements
     */
    List<T> readMultiple(String parameter, String needle);

    /**
     * Method for finding one entry from the repository
     *
     * @param fieldName the field to search by
     * @param needle    the value of the field to find
     * @return the first entry that matches the requirements
     */
    T findOneByFieldName(String fieldName, String needle);
}
