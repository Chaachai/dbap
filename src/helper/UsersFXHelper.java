package helper;

import bean.User;
import java.util.List;

import javafx.scene.control.TableView;

public class UsersFXHelper extends AbstractFxHelper<User> {

    private static AbstractFxHelperItem[] titres;

    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("ID", "id"),
            new AbstractFxHelperItem("USERNAME", "username"),
            new AbstractFxHelperItem("PROFILE", "profile")
        };
    }

    public UsersFXHelper(TableView<User> table, List<User> list) {
        super(titres, table, list);
    }

    public UsersFXHelper(TableView<User> table) {
        super(titres, table);
    }

}
