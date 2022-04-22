package neros2k.ntags.base;
public interface RepositoryInterface<T, P> {
    void init();
    void setValue(T VALUE);
    T getValue(P NAME);
}
