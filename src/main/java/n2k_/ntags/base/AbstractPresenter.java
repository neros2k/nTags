package n2k_.ntags.base;
public abstract class AbstractPresenter {
    private final InteractorInterface INTERACTOR;
    public AbstractPresenter(InteractorInterface INTERACTOR) {
        this.INTERACTOR = INTERACTOR;
    }
    abstract public void init();
    public InteractorInterface getInteractor() {
        return this.INTERACTOR;
    }
}
