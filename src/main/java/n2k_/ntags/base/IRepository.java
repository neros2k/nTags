package n2k_.ntags.base;
public interface IRepository<T, P> {
    void init();
    void setValue(T VALUE);
    T getValue(P NAME);
}
