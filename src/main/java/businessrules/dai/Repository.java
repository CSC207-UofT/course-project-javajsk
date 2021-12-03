package businessrules.dai;

import java.util.List;

public interface Repository <T> {
    T read(String id);

    boolean update(String id, T item);

    String create(T item);

    List<T> readMultiple(String parameter, String needle);

    T findOneByFieldName(String fieldName, String needle);
}
