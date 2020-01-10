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
public class EditProfileController3 implements Initializable {

    @FXML
    private Label close;
    @FXML
    private Label error;
    @FXML
    private TextField SESSIONS_PER_USER_field;
    @FXML
    private TextField LOGICAL_READS_PER_SESSION_field;
    @FXML
    private TextField LOGICAL_READS_PER_CALL_field;
    @FXML
    private TextField PRIVATE_SGA_field;
    @FXML
    private TextField COMPOSITE_LIMIT_field;

    @FXML
    private ComboBox<String> SESSIONS_PER_USER;
    @FXML
    private ComboBox<String> LOGICAL_READS_PER_SESSION;
    @FXML
    private ComboBox<String> LOGICAL_READS_PER_CALL;
    @FXML
    private ComboBox<String> PRIVATE_SGA;
    @FXML
    private ComboBox<String> COMPOSITE_LIMIT;

    private void initCombobox_SESSIONS_PER_USER() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1");
        vals.add("2");
        vals.add("10");
        SESSIONS_PER_USER.setItems(vals);
    }

    private void initCombobox_LOGICAL_READS_PER_SESSION() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1000");
        vals.add("5000");
        vals.add("10000");
        LOGICAL_READS_PER_SESSION.setItems(vals);
    }

    private void initCombobox_LOGICAL_READS_PER_CALL() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1000");
        vals.add("5000");
        vals.add("10000");
        LOGICAL_READS_PER_CALL.setItems(vals);
    }

    private void initCombobox_PRIVATE_SGA() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("4");
        vals.add("16");
        vals.add("256");
        PRIVATE_SGA.setItems(vals);
    }

    private void initCombobox_COMPOSITE_LIMIT() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1000000");
        vals.add("5000000");
        vals.add("10000000");
        COMPOSITE_LIMIT.setItems(vals);
    }

    @FXML
    private void SESSIONS_PER_USER_change(ActionEvent event) {
        SESSIONS_PER_USER_field.setText(SESSIONS_PER_USER.getValue());
    }

    @FXML
    private void LOGICAL_READS_PER_SESSION_change(ActionEvent event) {
        LOGICAL_READS_PER_SESSION_field.setText(LOGICAL_READS_PER_SESSION.getValue());
    }

    @FXML
    private void LOGICAL_READS_PER_CALL_change(ActionEvent event) {
        LOGICAL_READS_PER_CALL_field.setText(LOGICAL_READS_PER_CALL.getValue());
    }

    @FXML
    private void PRIVATE_SGA_change(ActionEvent event) {
        PRIVATE_SGA_field.setText(PRIVATE_SGA.getValue());
    }

    @FXML
    private void COMPOSITE_LIMIT_change(ActionEvent event) {
        COMPOSITE_LIMIT_field.setText(COMPOSITE_LIMIT.getValue());
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
        DBAProfiles.forward(actionEvent, "EditProfile_2.fxml", this.getClass());
    }

    @FXML
    private void toNext(ActionEvent actionEvent) throws IOException {
        if (checkInputs(SESSIONS_PER_USER_field.getText())
                && checkInputs(LOGICAL_READS_PER_CALL_field.getText())
                && checkInputs(LOGICAL_READS_PER_SESSION_field.getText())
                && checkInputs(COMPOSITE_LIMIT_field.getText())
                && checkInputs(PRIVATE_SGA_field.getText())) {

            Profile p = (Profile) Session.getAttribut("selectedProfile");
            Resource r = p.getResource();

            r.setSessions_per_user(SESSIONS_PER_USER_field.getText());
            r.setLogical_reads_per_call(LOGICAL_READS_PER_CALL_field.getText());
            r.setLogical_reads_per_session(LOGICAL_READS_PER_SESSION_field.getText());
            r.setComposite_limit(COMPOSITE_LIMIT_field.getText());
            r.setPrivate_sga(PRIVATE_SGA_field.getText());

            p.setResource(r);
            Session.updateAttribute(p, "selectedProfile");

            DBAProfiles.forward(actionEvent, "EditProfile_4.fxml", this.getClass());
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
        initCombobox_SESSIONS_PER_USER();
        initCombobox_LOGICAL_READS_PER_CALL();
        initCombobox_LOGICAL_READS_PER_SESSION();
        initCombobox_COMPOSITE_LIMIT();
        initCombobox_PRIVATE_SGA();

        Profile p = (Profile) Session.getAttribut("selectedProfile");
        if (p.getResource() != null) {
            if (p.getResource().getSessions_per_user() != null) {
                SESSIONS_PER_USER_field.setText(p.getResource().getSessions_per_user());
            }
            if (p.getResource().getLogical_reads_per_call() != null) {
                LOGICAL_READS_PER_CALL_field.setText(p.getResource().getLogical_reads_per_call());
            }
            if (p.getResource().getLogical_reads_per_session() != null) {
                LOGICAL_READS_PER_SESSION_field.setText(p.getResource().getLogical_reads_per_session());
            }
            if (p.getResource().getComposite_limit() != null) {
                COMPOSITE_LIMIT_field.setText(p.getResource().getComposite_limit());
            }
            if (p.getResource().getPrivate_sga() != null) {
                PRIVATE_SGA_field.setText(p.getResource().getPrivate_sga());
            }
        }
    }

}
