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
import util.Session;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class CreateProfileController4 implements Initializable {

    @FXML
    private Label close;
    @FXML
    private Label error;
    @FXML
    private TextField PASSWORD_LIFE_TIME_field;
    @FXML
    private TextField PASSWORD_GRACE_TIME_field;

    @FXML
    private ComboBox<String> PASSWORD_LIFE_TIME;
    @FXML
    private ComboBox<String> PASSWORD_GRACE_TIME;

    private void initCombobox_PASSWORD_LIFE_TIME() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("30");
        vals.add("60");
        vals.add("120");
        PASSWORD_LIFE_TIME.setItems(vals);
    }

    private void initCombobox_PASSWORD_GRACE_TIME() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("30");
        vals.add("60");
        vals.add("120");
        PASSWORD_GRACE_TIME.setItems(vals);
    }

    @FXML
    private void PASSWORD_LIFE_TIME_change(ActionEvent event) {
        PASSWORD_LIFE_TIME_field.setText(PASSWORD_LIFE_TIME.getValue());
    }

    @FXML
    private void PASSWORD_GRACE_TIME_change(ActionEvent event) {
        PASSWORD_GRACE_TIME_field.setText(PASSWORD_GRACE_TIME.getValue());
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
        DBAProfiles.forward(actionEvent, "CreateProfile_3.fxml", this.getClass());
    }

    @FXML
    private void toNext(ActionEvent actionEvent) throws IOException {
        if (checkInputs(PASSWORD_LIFE_TIME_field.getText())
                && checkInputs(PASSWORD_GRACE_TIME_field.getText())) {

            Profile p = (Profile) Session.getAttribut("profile");
            Resource r = p.getResource();
            
            r.setPassword_life_time(PASSWORD_LIFE_TIME_field.getText());
            r.setPassword_grace_time(PASSWORD_GRACE_TIME_field.getText());
            
            p.setResource(r);
            Session.updateAttribute(p, "profile");
            
            DBAProfiles.forward(actionEvent, "CreateProfile_5.fxml", this.getClass());
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
        initCombobox_PASSWORD_LIFE_TIME();
        initCombobox_PASSWORD_GRACE_TIME();

        Profile p = (Profile) Session.getAttribut("profile");
        if (p.getResource() != null) {
            if (p.getResource().getPassword_life_time() != null) {
                PASSWORD_LIFE_TIME_field.setText(p.getResource().getPassword_life_time());
            }
            if (p.getResource().getPassword_grace_time() != null) {
                PASSWORD_GRACE_TIME_field.setText(p.getResource().getPassword_grace_time());
            }
        }
    }

}
