package presenters;

import businessrules.outputboundaries.ObjectBoundary;
import businessrules.outputboundaries.ResponseObject;

import java.util.List;

/**
 * General presenter for all objects
 */
public class ObjectPresenter<T> implements ObjectBoundary<T> {
    /**
     * A method that returns a responseObject containing data to display a single object
     *
     * @param obj object to display
     * @return response object with display information
     */
    @Override
    public ResponseObject showObject(T obj) {
        return new ResponseObject(200, "", obj);
        // 200 http status code for OK
    }

    /**
     * A method that returns a responseObject containing data to display a list of objects
     *
     * @param listToDisp objects to display
     * @return response object with display information
     */
    @Override
    public ResponseObject showObjectList(List<T> listToDisp) {
        return new ResponseObject(200, "", listToDisp);
    }

    /**
     * A method that returns a responseObject containing data to display error for invalid objects
     *
     * @param message error message to display
     * @return response object with display information
     */
    @Override
    public ResponseObject invalidObject(String message) {
        return new ResponseObject(406, message, null);
        // 406  http status code for not acceptable
    }
}
