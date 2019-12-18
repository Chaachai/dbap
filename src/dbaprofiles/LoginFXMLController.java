/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.Config;

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
    public void connect(ActionEvent actionEvent) throws IOException {
        if (testChamps()) {
            Connection con = config.connect(login.getText(), password.getText());
            if (con != null) {
                DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username/password !", "logon denied", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("NOOOOO");
            JOptionPane.showMessageDialog(null, "The username and the password are required  !", "logon denied", JOptionPane.ERROR_MESSAGE);
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
