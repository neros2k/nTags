package n2k_.ntags.base;
public abstract class APresenter {
    private final IInteractor INTERACTOR;
    public APresenter(IInteractor INTERACTOR) {
        this.INTERACTOR = INTERACTOR;
    }
    abstract public void init();
    public IInteractor getInteractor() {
        return this.INTERACTOR;
    }
}
