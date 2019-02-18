package ru.job4j.generic;

/**
 * @author Mikhail Rozdin
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractStore<T extends Base>
        extends SimpleArray<T>
        implements Store<T> {


    public AbstractStore(int value) {
        super(value);
    }

    private int findPositionById(String id) {
        int result = -1;
        int cursor = -1;
        for (T t : this) {
            cursor++;
            if (t != null && t.getId().equals(id)) {
                result = cursor;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean replace(String id, T model) {
        return set(findPositionById(id), model) != null;
    }

    @Override
    public boolean delete(String id) {
        return remove(findPositionById(id)) != null;
    }

    @Override
   // @SuppressWarnings("unchecked")
    public T findById(String id) {
        int temp = findPositionById(id);
        return temp != -1 ? this.get(temp) : null;
    }
}
