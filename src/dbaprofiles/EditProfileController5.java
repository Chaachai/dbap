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
public class EditProfileController5 implements Initializable {

    @FXML
    private Label close;
    @FXML
    private Label error;
    @FXML
    private TextField PASSWORD_REUSE_MAX_field;
    @FXML
    private TextField PASSWORD_REUSE_TIME_field;

    @FXML
    private ComboBox<String> PASSWORD_REUSE_MAX;
    @FXML
    private ComboBox<String> PASSWORD_REUSE_TIME;

    private void initCombobox_PASSWORD_REUSE_MAX() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1");
        vals.add("2");
        vals.add("10");
        PASSWORD_REUSE_MAX.setItems(vals);
    }

    private void initCombobox_PASSWORD_REUSE_TIME() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("30");
        vals.add("60");
        vals.add("120");
        PASSWORD_REUSE_TIME.setItems(vals);
    }

    @FXML
    private void PASSWORD_REUSE_MAX_change(ActionEvent event) {
        PASSWORD_REUSE_MAX_field.setText(PASSWORD_REUSE_MAX.getValue());
        inputConstraint();
    }

    @FXML
    private void PASSWORD_REUSE_TIME_change(ActionEvent event) {
        PASSWORD_REUSE_TIME_field.setText(PASSWORD_REUSE_TIME.getValue());
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
        DBAProfiles.forward(actionEvent, "EditProfile_4.fxml", this.getClass());
    }

    @FXML
    private void toNext(ActionEvent actionEvent) throws IOException {
        if (checkInputs(PASSWORD_REUSE_MAX_field.getText())
                && checkInputs(PASSWORD_REUSE_TIME_field.getText())) {

            Profile p = (Profile) Session.getAttribut("selectedProfile");
            Resource r = p.getResource();

            r.setPassword_reuse_max(PASSWORD_REUSE_MAX_field.getText());
            r.setPassword_reuse_time(PASSWORD_REUSE_TIME_field.getText());

            p.setResource(r);
            Session.updateAttribute(p, "selectedProfile");

            DBAProfiles.forward(actionEvent, "EditProfile_6.fxml", this.getClass());
        } else {
            error.setVisible(true);
        }
    }

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Session.delete("selectedProfile");
        DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
    }

    @FXML
    private void inputConstraint() {
        if (!PASSWORD_REUSE_MAX_field.getText().equalsIgnoreCase("DEFAULT")
                && !PASSWORD_REUSE_MAX_field.getText().equalsIgnoreCase("UNLIMITED")) {
            PASSWORD_REUSE_TIME_field.setText("UNLIMITED");
            PASSWORD_REUSE_TIME_field.setDisable(true);
            PASSWORD_REUSE_TIME.setDisable(true);
        } else {
            PASSWORD_REUSE_TIME_field.setDisable(false);
            PASSWORD_REUSE_TIME.setDisable(false);
        }
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCombobox_PASSWORD_REUSE_MAX();
        initCombobox_PASSWORD_REUSE_TIME();

        Profile p = (Profile) Session.getAttribut("selectedProfile");
        if (p.getResource() != null) {
            if (p.getResource().getPassword_reuse_max() != null) {
                PASSWORD_REUSE_MAX_field.setText(p.getResource().getPassword_reuse_max());
            }
            if (p.getResource().getPassword_reuse_time() != null) {
                PASSWORD_REUSE_TIME_field.setText(p.getResource().getPassword_reuse_time());
            }
        }
    }

}
