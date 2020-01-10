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
public class EditProfileController2 implements Initializable {

    @FXML
    private Label close;
    @FXML
    private Label error;
    @FXML
    private TextField cpu_per_session_field;
    @FXML
    private TextField cpu_per_call_field;
    @FXML
    private TextField connect_time_field;
    @FXML
    private TextField idle_time_field;

    @FXML
    private ComboBox<String> cpu_per_session;

    @FXML
    private ComboBox<String> cpu_per_call;
    @FXML

    private ComboBox<String> connect_time;
    @FXML

    private ComboBox<String> idle_time;

    private void initComboboxCpuPerSession() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1000");
        vals.add("6000");
        vals.add("36000");
        cpu_per_session.setItems(vals);
//        cpu_per_session.setStyle("-fx-selection-bar: #d60202; "
//                + "-fx-background-color: #FFFFFF;"
//                + "-fx-border-color: #e00e0e;"
//                + "-fx-background-radius: 15;"
//                + "-fx-border-radius: 15;"
//        );
    }

    private void initComboboxCpuPerCall() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1000");
        vals.add("6000");
        vals.add("36000");
        cpu_per_call.setItems(vals);
        //  cpu_per_call.getSelectionModel().selectFirst();
    }

    private void initComboboxConnectTime() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("30");
        vals.add("60");
        vals.add("120");
        connect_time.setItems(vals);
        //   connect_time.getSelectionModel().selectFirst();
    }

    private void initComboboxIdleTime() {
        ObservableList<String> vals = FXCollections.observableArrayList();
        vals.add("DEFAULT");
        vals.add("UNLIMITED");
        vals.add("1");
        vals.add("15");
        vals.add("60");
        idle_time.setItems(vals);
        //   idle_time.getSelectionModel().selectFirst();
    }

    @FXML
    private void cpuPerSessionChange(ActionEvent event) {
        cpu_per_session_field.setText(cpu_per_session.getValue());
    }

    @FXML
    private void cpuPerCallChange(ActionEvent event) {
        cpu_per_call_field.setText(cpu_per_call.getValue());
    }

    @FXML
    private void connectTimeChange(ActionEvent event) {
        connect_time_field.setText(connect_time.getValue());
    }

    @FXML
    private void idleTimeChange(ActionEvent event) {
        idle_time_field.setText(idle_time.getValue());
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
        DBAProfiles.forward(actionEvent, "EditProfile_1.fxml", this.getClass());
    }

    @FXML
    private void toNext(ActionEvent actionEvent) throws IOException {
        if (checkInputs(cpu_per_session_field.getText())
                && checkInputs(cpu_per_call_field.getText())
                && checkInputs(connect_time_field.getText())
                && checkInputs(idle_time_field.getText())) {

            Profile p = (Profile) Session.getAttribut("selectedProfile");
            Resource r = p.getResource();
            r.setCpu_per_session(cpu_per_session_field.getText());
            r.setCpu_per_call(cpu_per_call_field.getText());
            r.setConnect_time(connect_time_field.getText());
            r.setIdle_time(idle_time_field.getText());

            p.setResource(r);
            Session.updateAttribute(p, "selectedProfile");

            DBAProfiles.forward(actionEvent, "EditProfile_3.fxml", this.getClass());
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
        initComboboxCpuPerSession();
        initComboboxCpuPerCall();
        initComboboxConnectTime();
        initComboboxIdleTime();

        Profile p = (Profile) Session.getAttribut("selectedProfile");
        if (p.getResource() != null) {
            if (p.getResource().getCpu_per_session() != null) {
                cpu_per_session_field.setText(p.getResource().getCpu_per_session());
            }
            if (p.getResource().getCpu_per_call() != null) {
                cpu_per_call_field.setText(p.getResource().getCpu_per_call());
            }
            if (p.getResource().getConnect_time() != null) {
                connect_time_field.setText(p.getResource().getConnect_time());
            }
            if (p.getResource().getIdle_time() != null) {
                idle_time_field.setText(p.getResource().getIdle_time());
            }
        }
        //    init_inputs();

    }

}
