package businessrules.outputboundaries;

/**
 * Output boundary for repository entities
 */
public interface RepositoryBoundary {
    /**
     * A method that returns a responseObject containing error message when object creation failed
     * @param message error message
     * @return responseObject with information to display
     */
    ResponseObject creationFailed(String message);

    /**
     * A method that returns a responseObject containing error message when query is not found
     * @param message error message
     * @return responseObject with information to display
     */
    ResponseObject queryNotFound(String message);

    /**
     * A method that returns a responseObject containing error message when modification fails
     * @param message error message
     * @return responseObject with information to display
     */
    ResponseObject modificationFailed(String message);

    /**
     * A method that returns a responseObject containing error message when input is invalid
     * @param message error message
     * @return responseObject with information to display
     */
    ResponseObject invalidInput(String message);
}
