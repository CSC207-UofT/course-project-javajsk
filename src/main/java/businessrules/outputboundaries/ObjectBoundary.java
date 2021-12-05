package businessrules.outputboundaries;

import java.util.List;

public interface ObjectBoundary <T>{
    /**
 * General Output boundary for all entity objects
 * @param <T> generic data type to account for different entities
 */
public interface ObjectBoundary <T>{
    /**
     * A method that returns a responseObject containing data to display a single object
     * @param obj object to display
     * @return response object with display information
     */
    ResponseObject showObject(T obj);

    /**
     * A method that returns a responseObject containing data to display a list of objects
     * @param listToDisp objects to display
     * @return response object with display information
     */
    ResponseObject showObjectList(List<T> listToDisp);

    /**
     * A method that returns a responseObject containing data to display error for invalid objects
     * @param message error message to display
     * @return response object with display information
     */
    ResponseObject invalidObject(String message);
}
