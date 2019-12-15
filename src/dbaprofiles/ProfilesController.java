/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import helper.ProfileFXHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import service.ProfileFacade;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class ProfilesController implements Initializable {

    
    @FXML
    private Label CPU_PER_SESSION;
 
    
    
    
    @FXML
    private TableView profileTable = new TableView();
    
    ProfileFacade profileFacade = new ProfileFacade();
    
    private ProfileFXHelper profileFXHelper;
    
    private void initHelper() {
        profileFXHelper = new ProfileFXHelper(profileTable, profileFacade.getAllProfiles());
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
       initHelper();
    }

}
