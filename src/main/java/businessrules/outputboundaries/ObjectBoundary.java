package businessrules.outputboundaries;

import java.util.List;

public interface ObjectBoundary <T>{
    ResponseObject showObject(T obj);

    ResponseObject showObjectList(List<T> listToDisp);

    ResponseObject invalidObject(String message);
}
