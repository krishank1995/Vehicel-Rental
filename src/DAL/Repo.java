package DAL;

import java.util.List;
import java.util.Optional;

public interface Repo<E> {
    Optional<E> get(String primaryKey);
    void add(E entity);
}
