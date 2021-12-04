package businessrules.outputboundaries;

public interface RepositoryBoundary {
    ResponseObject creationFailed(String message);

    ResponseObject queryNotFound(String message);

    ResponseObject modificationFailed(String message);

    ResponseObject invalidInput(String message);
}
