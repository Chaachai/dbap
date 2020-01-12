/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import bean.Profile;
import bean.User;
import helper.ProfileFXHelper;
import helper.UsersFXHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ProfileFacade;
import service.UserFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class UsersController implements Initializable {

    @FXML
    private Label close;
    @FXML
    private ImageView toggle_off;
    @FXML
    private ImageView toggle_on;

    @FXML
    private TextField search;
    @FXML
    private TextField hiddenField;

    @FXML
    private TableView usersTable = new TableView();
    @FXML
    private ComboBox<Profile> combo_profiles;

    ProfileFacade profileFacade = new ProfileFacade();
    UserFacade userFacade = new UserFacade();
    private UsersFXHelper usersFXHelper;

    int damn = 0;
    int userIndex = 0;

    private void initCombobox() {
        combo_profiles.setItems(FXCollections.observableArrayList(profileFacade.getAllProfiles()));
    }

    private void initHelper() {
        usersFXHelper = new UsersFXHelper(usersTable, userFacade.getAllUsers());
        usersTable.setStyle("-fx-selection-bar: #d60202; "
                + "-fx-selection-bar-non-focused: salmon; "
        );
    }

    public void mouseClickedTable() {
        User user = usersFXHelper.getSelected();
        if (user != null) {
            damn = 1;
            userIndex = usersTable.getSelectionModel().getSelectedIndex();
            hiddenField.setText(user.getUsername());
            combo_profiles.setDisable(false);
            combo_profiles.getSelectionModel().select(user.getProfile());
        }

    }

    @FXML
    private void filterUsers() {
        usersFXHelper.setList(userFacade.findUsers(search.getText().toUpperCase()));
    }

    @FXML
    private void combo_profiles_change(ActionEvent event) {
        if (damn == 0) {
            int res = userFacade.assignProfile(hiddenField.getText(), combo_profiles.getValue().getName());
            if (res != 1) {
                JOptionPane.showMessageDialog(null, "An error has occured, please try again !", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                initHelper();
                usersTable.getSelectionModel().select(userIndex);
//                JOptionPane.showMessageDialog(null, "Profile assigned successfully");
            }

        } else {
            damn = 0;
        }

    }

    @FXML
    public void closeApp() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void minimizeApp(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
    }

    @FXML
    public void toggle_clicked() {
        if (toggle_on.isVisible() && !toggle_off.isVisible()) {
            userFacade.setResourceLimit("FALSE");
            toggle_on.setVisible(false);
            toggle_off.setVisible(true);
        } else if (!toggle_on.isVisible() && toggle_off.isVisible()) {
            userFacade.setResourceLimit("TRUE");
            toggle_on.setVisible(true);
            toggle_off.setVisible(false);
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
        initCombobox();
        boolean res = userFacade.getResourceLimit();
        if (res) {
            toggle_on.setVisible(true);
            toggle_off.setVisible(false);
        } else {
            toggle_on.setVisible(false);
            toggle_off.setVisible(true);
        }

    }

}
