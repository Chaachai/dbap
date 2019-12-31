/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import bean.Profile;
import bean.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.ProfileFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class CreateProfileController7 implements Initializable {

    ProfileFacade profileFacade = new ProfileFacade();

    @FXML
    private Label close;
    @FXML
    private Label error;
    @FXML
    private TextField FAILED_LOGIN_ATTEMPTS_field;
    @FXML
    private TextField PASSWORD_LOCK_TIME_field;

    @FXML
    private ComboBox<String> FAILED_LOGIN_ATTEMPTS;
    @FXML
    private ComboBox<String> PASSWORD_LOCK_TIME;

    private void initCombobox_FAILED_LOGIN_ATTEMPTS() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("3");
        vals.add("6");
        vals.add("10");
        FAILED_LOGIN_ATTEMPTS.setItems(vals);
    }

    private void initCombobox_PASSWORD_LOCK_TIME() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("5");
        vals.add("10");
        vals.add("20");
        PASSWORD_LOCK_TIME.setItems(vals);
    }

    @FXML
    private void FAILED_LOGIN_ATTEMPTS_change(ActionEvent event) {
        FAILED_LOGIN_ATTEMPTS_field.setText(FAILED_LOGIN_ATTEMPTS.getValue());
    }

    @FXML
    private void PASSWORD_LOCK_TIME_change(ActionEvent event) {
        PASSWORD_LOCK_TIME_field.setText(PASSWORD_LOCK_TIME.getValue());
    }

    private boolean checkInputs(String val) {
        if (val.equals("DEFAULT") || val.equals("UNLIMITED") || isInteger(val)) {
            return true;
        }
        return false;
    }

    private boolean isInteger(String val) {
        try {
            int n = Integer.parseInt(val);
            return true;
        } catch (NumberFormatException ex) {
            return false;
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
    private void toPrevious(ActionEvent actionEvent) throws IOException {
        DBAProfiles.forward(actionEvent, "CreateProfile_6.fxml", this.getClass());
    }

    @FXML
    private void createProfile(ActionEvent actionEvent) throws IOException {
        if (checkInputs(FAILED_LOGIN_ATTEMPTS_field.getText())
                && checkInputs(PASSWORD_LOCK_TIME_field.getText())) {

            Profile p = (Profile) Session.getAttribut("profile");
            Resource r = p.getResource();

            r.setFailed_login_attempts(FAILED_LOGIN_ATTEMPTS_field.getText());
            r.setPassword_lock_time(PASSWORD_LOCK_TIME_field.getText());

            p.setResource(r);

            int res = profileFacade.createProfile(p);

            if (res == 1) {
                DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
                Session.delete("profile");
            } else {
                JOptionPane.showMessageDialog(null, "An error has occured, please try again !", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            error.setVisible(true);
        }
    }

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Session.delete("profile");
        DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCombobox_FAILED_LOGIN_ATTEMPTS();
        initCombobox_PASSWORD_LOCK_TIME();

        Profile p = (Profile) Session.getAttribut("profile");
        if (p.getResource() != null) {
            if (p.getResource().getFailed_login_attempts() != null) {
                FAILED_LOGIN_ATTEMPTS_field.setText(p.getResource().getFailed_login_attempts());
            }
            if (p.getResource().getPassword_lock_time() != null) {
                PASSWORD_LOCK_TIME_field.setText(p.getResource().getPassword_lock_time());
            }
        }
    }

}
