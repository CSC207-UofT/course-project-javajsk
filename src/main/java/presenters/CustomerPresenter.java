package presenters;

import businessrules.outputboundaries.CustomerBoundary;
import businessrules.outputboundaries.ResponseObject;

/**
 * Presenter for Customer
 */
public class CustomerPresenter implements CustomerBoundary {


    @Override
    public ResponseObject displayToken(String token) {
        return null;
    }

    @Override
    public ResponseObject error(String message) {
        return null;
    }

    @Override
    public ResponseObject unauthorizedAccess(String message) {
        return null;
    }
}
