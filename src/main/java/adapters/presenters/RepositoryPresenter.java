package adapters.presenters;

import businessrules.outputboundaries.RepositoryBoundary;
import businessrules.outputboundaries.ResponseObject;

/**
 * Presenter for repository
 */
public class RepositoryPresenter implements RepositoryBoundary {
    /**
     * A method that returns a responseObject containing error message when object creation failed
     *
     * @param message error message
     * @return responseObject with information to display
     */
    @Override
    public ResponseObject creationFailed(String message) {
        return new ResponseObject(406, message, null);
    }

    /**
     * A method that returns a responseObject containing error message when query is not found
     *
     * @param message error message
     * @return responseObject with information to display
     */
    @Override
    public ResponseObject queryNotFound(String message) {
        return new ResponseObject(404, message, null);
        // 404 is http code status for not found
    }

    /**
     * A method that returns a responseObject containing error message when modification fails
     *
     * @param message error message
     * @return responseObject with information to display
     */
    @Override
    public ResponseObject modificationFailed(String message) {
        return new ResponseObject(406, message, null);
    }

    /**
     * A method that returns a responseObject containing error message when input is invalid
     *
     * @param message error message
     * @return responseObject with information to display
     */
    @Override
    public ResponseObject invalidInput(String message) {
        return new ResponseObject(404, message, null);
    }
}
