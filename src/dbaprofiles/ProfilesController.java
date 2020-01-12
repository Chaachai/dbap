/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import bean.Profile;
import helper.ProfileFXHelper;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
public class ProfilesController implements Initializable {

    @FXML
    private Label COMPOSITE_LIMIT;
    @FXML
    private Label SESSIONS_PER_USER;
    @FXML
    private Label CPU_PER_SESSION;
    @FXML
    private Label CPU_PER_CALL;
    @FXML
    private Label LOGICAL_READS_PER_SESSION;
    @FXML
    private Label LOGICAL_READS_PER_CALL;
    @FXML
    private Label IDLE_TIME;
    @FXML
    private Label CONNECT_TIME;
    @FXML
    private Label PRIVATE_SGA;
    @FXML
    private Label FAILED_LOGIN_ATTEMPTS;
    @FXML
    private Label PASSWORD_LIFE_TIME;
    @FXML
    private Label PASSWORD_REUSE_TIME;
    @FXML
    private Label PASSWORD_REUSE_MAX;
    @FXML
    private Label PASSWORD_VERIFY_FUNCTION;
    @FXML
    private Label PASSWORD_LOCK_TIME;
    @FXML
    private Label PASSWORD_GRACE_TIME;

    @FXML
    private Label close;
    @FXML
    private Label username;

    @FXML
    private TextField hiddenField;
    @FXML
    private TextField search;
    @FXML
    private Button logout;

    @FXML
    private TableView profileTable = new TableView();

    ProfileFacade profileFacade = new ProfileFacade();
    UserFacade userFacade = new UserFacade();
    private ProfileFXHelper profileFXHelper;

    @FXML
    private void initHelper() {
        if (profileFacade.getAllProfiles() != null) {
            profileFXHelper = new ProfileFXHelper(profileTable, profileFacade.getAllProfiles());
            profileTable.setStyle("-fx-selection-bar: #d60202; "
                    + "-fx-selection-bar-non-focused: salmon; "
            );
        } else {
           
            System.out.println("You Do not have the permission ");

        }

    }

    @FXML
    private void toEditProfile(ActionEvent actionEvent) throws IOException {
        if (hiddenField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Select a profile to edit !", "No profile was selected", JOptionPane.INFORMATION_MESSAGE);
        } else {
            DBAProfiles.forward(actionEvent, "EditProfile_1.fxml", this.getClass());
        }
    }

    @FXML
    private void toUsers(ActionEvent actionEvent) throws IOException {
        DBAProfiles.forward(actionEvent, "Users.fxml", this.getClass());
    }

    @FXML
    private void toCreateProfile(ActionEvent actionEvent) throws IOException {
        DBAProfiles.forward(actionEvent, "CreateProfile_1.fxml", this.getClass());
    }

    @FXML
    private void deleteProfile() {
        if (hiddenField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Select a profile to delete !", "No profile was selected", JOptionPane.INFORMATION_MESSAGE);
        } else if (!hiddenField.getText().equalsIgnoreCase("default")) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("WARNING !!");
            alert.setContentText("You are about to delete the profile named '"
                    + hiddenField.getText().toUpperCase() + "'. \nAre you sure about that ?");
            alert.setTitle("WARNING !!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                int res = profileFacade.dropProfile(hiddenField.getText());
                if (res == 1) {
                    profileFXHelper.remove(profileFXHelper.getSelected());
                    Session.delete("selectedProfile");
                    clear();
                } else {
                    JOptionPane.showMessageDialog(null, "An error has occured, please try again !", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You cannot drop the DEFAULT profile !", "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void mouseClickedTable() {
        Profile profile = profileFXHelper.getSelected();
        if (profile != null) {
            Session.updateAttribute(profile, "selectedProfile");
            hiddenField.setText(profile.getName());

            COMPOSITE_LIMIT.setText(profile.getResource().getComposite_limit());
            SESSIONS_PER_USER.setText(profile.getResource().getSessions_per_user());
            CPU_PER_SESSION.setText(profile.getResource().getCpu_per_session());
            CPU_PER_CALL.setText(profile.getResource().getCpu_per_call());
            LOGICAL_READS_PER_SESSION.setText(profile.getResource().getLogical_reads_per_session());
            LOGICAL_READS_PER_CALL.setText(profile.getResource().getLogical_reads_per_call());
            IDLE_TIME.setText(profile.getResource().getIdle_time());
            CONNECT_TIME.setText(profile.getResource().getConnect_time());
            PRIVATE_SGA.setText(profile.getResource().getPrivate_sga());
            FAILED_LOGIN_ATTEMPTS.setText(profile.getResource().getFailed_login_attempts());
            PASSWORD_LIFE_TIME.setText(profile.getResource().getPassword_life_time());
            PASSWORD_REUSE_TIME.setText(profile.getResource().getPassword_reuse_time());
            PASSWORD_REUSE_MAX.setText(profile.getResource().getPassword_reuse_max());
            PASSWORD_VERIFY_FUNCTION.setText(profile.getResource().getPassword_verify_function());
            PASSWORD_LOCK_TIME.setText(profile.getResource().getPassword_lock_time());
            PASSWORD_GRACE_TIME.setText(profile.getResource().getPassword_grace_time());
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
    private void refresh(ActionEvent actionEvent) throws IOException {
        DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
    }

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Session.clear();
        DBAProfiles.forward(actionEvent, "LoginFXML.fxml", this.getClass());
    }

    @FXML
    private void filterProfiles() {
        profileFXHelper.setList(profileFacade.findProfiles(search.getText().toUpperCase()));
    }

    public void clear() {
        hiddenField.setText("");
        COMPOSITE_LIMIT.setText("---");
        SESSIONS_PER_USER.setText("---");
        CPU_PER_SESSION.setText("---");
        CPU_PER_CALL.setText("---");
        LOGICAL_READS_PER_SESSION.setText("---");
        LOGICAL_READS_PER_CALL.setText("---");
        IDLE_TIME.setText("---");
        CONNECT_TIME.setText("---");
        PRIVATE_SGA.setText("---");
        FAILED_LOGIN_ATTEMPTS.setText("---");
        PASSWORD_LIFE_TIME.setText("---");
        PASSWORD_REUSE_TIME.setText("---");
        PASSWORD_REUSE_MAX.setText("---");
        PASSWORD_VERIFY_FUNCTION.setText("---");
        PASSWORD_LOCK_TIME.setText("---");
        PASSWORD_GRACE_TIME.setText("---");

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initHelper();
        username.setText(userFacade.getUsername());
    }

}
