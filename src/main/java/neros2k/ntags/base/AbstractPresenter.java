package neros2k.ntags.base;
public abstract class AbstractPresenter<T extends InteractorInterface<?>> {
    private final T INTERACTOR;
    public AbstractPresenter(T INTERACTOR) {
        this.INTERACTOR = INTERACTOR;
    }
    abstract public void init();
    public T getInteractor() {
        return this.INTERACTOR;
    }
}
