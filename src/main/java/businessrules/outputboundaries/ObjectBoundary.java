package businessrules.outputboundaries;

import java.util.List;

/**
 * The output boundary for objects of different types
 */
public interface ObjectBoundary <T>{
    /**
     * Method for showing an object
     * @param obj the entity to be shown
     * @return the response object
     */
    ResponseObject showObject(T obj);

    /**
     * Method for showing a list of objects
     * @param listToDisp the list of entities to be shown
     * @return the response object
     */
    ResponseObject showObjectList(List<T> listToDisp);

    /**
     * Method for getting information when there's an invalid object
     * @param message error message
     * @return a response object
     */
    ResponseObject invalidObject(String message);
}
