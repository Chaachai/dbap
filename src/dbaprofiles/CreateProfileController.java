/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import bean.Profile;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Session;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class CreateProfileController implements Initializable {

    @FXML
    private Label close;
    @FXML
    private Label error;

    @FXML
    private TextField pName;

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
        DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
    }

    @FXML
    private void toNext(ActionEvent actionEvent) throws IOException {
        if (!pName.getText().trim().isEmpty()) {
            Profile p = (Profile) Session.getAttribut("profile");
            if(p != null){
                p.setName(pName.getText().trim());
            }else{
                p = new Profile(pName.getText().trim());
            }
            //Profile p = new Profile(pName.getText().trim());
            Session.updateAttribute(p, "profile");
       //     System.out.println("***** Profile name = " + p.getName());
            error.setVisible(false);
            DBAProfiles.forward(actionEvent, "CreateProfile_2.fxml", this.getClass());
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
        Profile p = (Profile) Session.getAttribut("profile");
        if(p != null)
            pName.setText(p.getName());
    }

}
