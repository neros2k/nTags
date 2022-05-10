package n2k_.ntags.core;
import n2k_.ntags.SQLite;
import n2k_.ntags.base.IRepository;
import n2k_.ntags.base.object.State;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
public final class TagsRepository implements IRepository<State, String> {
    private final SQLite SQLITE_DATABASE;
    public TagsRepository(@NotNull TagInteractor INTERACTOR) {
        this.SQLITE_DATABASE = new SQLite(INTERACTOR.getPlugin(), "tags_database.db", "hidden_tags");
    }
    @Override
    public void init() {
        this.SQLITE_DATABASE.init();
    }
    @Override
    public void setValue(@NotNull State VALUE) {
        this.SQLITE_DATABASE.saveValue(VALUE.getName(), VALUE.isHide());
    }
    @Override @NotNull @Contract(pure = true)
    public State getValue(@NotNull String NAME) {
        return new State(NAME, this.SQLITE_DATABASE.findValue(NAME));
    }
}
