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
public class EditProfileController6 implements Initializable {

    @FXML
    private Label close;
    @FXML
    private Label error;
    @FXML
    private TextField PASSWORD_VERIFY_FUNCTION_field;

    @FXML
    private ComboBox<String> PASSWORD_VERIFY_FUNCTION;

    private void initCombobox_PASSWORD_VERIFY_FUNCTION() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("NULL");
        PASSWORD_VERIFY_FUNCTION.setItems(vals);
    }

    private boolean checkInputs(String val) {
        if (val.equals("DEFAULT") || val.equals("NULL")) {
            return true;
        }
        return false;
    }

    @FXML
    private void PASSWORD_VERIFY_FUNCTION_change(ActionEvent event) {
        PASSWORD_VERIFY_FUNCTION_field.setText(PASSWORD_VERIFY_FUNCTION.getValue());
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
        DBAProfiles.forward(actionEvent, "EditProfile_5.fxml", this.getClass());
    }

    @FXML
    private void toNext(ActionEvent actionEvent) throws IOException {
        if (checkInputs(PASSWORD_VERIFY_FUNCTION_field.getText())) {

            Profile p = (Profile) Session.getAttribut("selectedProfile");
            Resource r = p.getResource();

            r.setPassword_verify_function(PASSWORD_VERIFY_FUNCTION_field.getText());

            p.setResource(r);
            Session.updateAttribute(p, "selectedProfile");

            DBAProfiles.forward(actionEvent, "EditProfile_7.fxml", this.getClass());
        } else {
            error.setVisible(true);
        }
    }

    @FXML
    private void toHome(ActionEvent actionEvent) throws IOException {
        Session.delete("selectedProfile");
        DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initCombobox_PASSWORD_VERIFY_FUNCTION();
        Profile p = (Profile) Session.getAttribut("selectedProfile");
        if (p.getResource() != null) {
            if (p.getResource().getPassword_verify_function() != null) {
                PASSWORD_VERIFY_FUNCTION_field.setText(p.getResource().getPassword_verify_function());
            }
        }
    }

}
