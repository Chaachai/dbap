package helper;

import bean.Profile;
import java.util.List;

import javafx.scene.control.TableView;

public class ProfileFXHelper extends AbstractFxHelper<Profile> {

    private static AbstractFxHelperItem[] titres;

    static {
        titres = new AbstractFxHelperItem[]{
            new AbstractFxHelperItem("PROFILE NAME", "name")
        };
    }

    public ProfileFXHelper(TableView<Profile> table, List<Profile> list) {
        super(titres, table, list);
    }

    public ProfileFXHelper(TableView<Profile> table) {
        super(titres, table);
    }

}
