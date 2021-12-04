package businessrules.outputboundaries;

public interface CustomerBoundary {
    ResponseObject displayToken(String token);

    ResponseObject error(String message);

    ResponseObject unauthorizedAccess(String message);
}
