/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import bean.Profile;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.Config;
import service.ProfileFacade;
import util.Session;

/**
 * FXML Controller class
 *
 * @author CHAACHAI Youssef
 */
public class LoginFXMLController implements Initializable {

    Config config = new Config();

    @FXML
    private Label close;

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    @FXML
    private Pane pane;
    @FXML
    private Text errorMessage;
    
    ProfileFacade profileFacade = new ProfileFacade();


    @FXML
    public void connect(ActionEvent actionEvent) throws IOException {
        if (testChamps()) {
            Connection con = config.connect(login.getText(), password.getText(), "1521", "dbap");
            if (con != null) {
                Session.updateAttribute(login.getText(), "login");
                Session.updateAttribute(password.getText(), "password");
                if (testPrivilege()) {
                    DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
                }else{
                    Session.clear();
                    return;
                }
            } else {
                String error = (String) Session.getAttribut("error");
                errorMessage.setText(error);
                errorMessage.setVisible(true);
                pane.setVisible(true);
                // JOptionPane.showMessageDialog(null, error, "login denied", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "The username and the password are required  !", "login denied", JOptionPane.ERROR_MESSAGE);
        }

    }
    public boolean testPrivilege() {
        List<Profile> list = profileFacade.getAllProfiles();
        if (list == null) {
            errorMessage.setText("Sorry, it seems that you do not "
                    + "have the necessary privilege to use this application !");
            errorMessage.setVisible(true);
            pane.setVisible(true);
            return false;
        } else {
            return true;
        }
    }

    private boolean testChamps() {
        if (login.getText().equals("")) {
            return false;
        } else if (password.getText().equals("")) {
            return false;
        } else {
            return true;
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
    private void toLogin2(ActionEvent actionEvent) throws IOException {
        DBAProfiles.forward(actionEvent, "Login2FXML.fxml", this.getClass());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        errorMessage.setText("");
        errorMessage.setVisible(false);
        pane.setVisible(false);
    }

}
