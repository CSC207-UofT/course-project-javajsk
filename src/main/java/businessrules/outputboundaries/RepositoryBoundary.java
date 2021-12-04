package businessrules.outputboundaries;

/**
 * The output boundary for repository entities
 */
public interface RepositoryBoundary {
    /**
     * Method for getting information when a repository cannot be created
     * @param message error message
     * @return a response object
     */
    ResponseObject creationFailed(String message);

    /**
     * Method for getting information when an invalid query is made
     * @param message error message
     * @return a response object
     */
    ResponseObject queryNotFound(String message);

    /**
     * Method for getting information when repository cannot be modified
     * @param message error message
     * @return a response object
     */
    ResponseObject modificationFailed(String message);

    /**
     * Method for getting information when there's invalid input
     * @param message error message
     * @return a response object
     */
    ResponseObject invalidInput(String message);
}
