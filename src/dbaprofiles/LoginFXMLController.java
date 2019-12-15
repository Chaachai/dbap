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

//    SalarieFacade sf = new SalarieFacade();
    Config config = new Config();

    @FXML
    private Button loginButton;

    @FXML
    private Label close;
    @FXML
    private Button minimize;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private TextField login;
    @FXML
    private PasswordField password;

    @FXML
    public void connect(ActionEvent actionEvent) throws IOException {
        Connection con = config.connect(login.getText(), password.getText());
        if (con != null) {
            //JOptionPane.showMessageDialog(null, "SUCCESS", "YESSSS", JOptionPane.INFORMATION_MESSAGE);
            DBAProfiles.forward(actionEvent, "Profiles.fxml", this.getClass());
        } else {
            JOptionPane.showMessageDialog(null, "L'identifiant ou le mot de passe est incorrect !", "Echec de la connexion", JOptionPane.ERROR_MESSAGE);
        }
//        switch (res) {
//            case 1:
//                System.out.println("welcome");
//                Salarie sal = sf.getSalarieByLogin(login.getText());
//                Session.createAtrribute(sal, "connectedUser");
//                switch (sal.getRole().getId()) {
//                    case 1:
//                        System.out.println("vous etes le directeur");
//                        ViewLauncher.forward(actionEvent, "DirecteurAccueilFXML.fxml", this.getClass());
//                        break;
//                    case 2:
//                        System.out.println("vous etes un responnsable");
//                        //ViewLauncher.forward(actionEvent, "EmployeDemandeFXML.fxml", this.getClass());
//                        break;
//                    case 3:
//                        System.out.println("vous etes un employe");
//                        ViewLauncher.forward(actionEvent, "EmployeAccueilFXML.fxml", this.getClass());
//                        break;
//                    default:
//                        break;
//                }
//                break;
//            case -1:
//                JOptionPane.showMessageDialog(null, "L'identifiant ou le mot de passe est incorrect !", "Echec de la connexion", JOptionPane.ERROR_MESSAGE);
//                break;
//            default:
//                JOptionPane.showMessageDialog(null, "Something bad happened, please try again later !", "Error", JOptionPane.ERROR_MESSAGE);
//                break;
//        }
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
