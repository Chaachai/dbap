/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbaprofiles;

import bean.Profile;
import helper.ProfileFXHelper;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import service.ProfileFacade;

/**
 * FXML Controller class
 *
 * @author Youssef
 */
public class ProfilesController implements Initializable {

    
    @FXML
    private Label COMPOSITE_LIMIT;
    @FXML
    private Label SESSIONS_PER_USER;
    @FXML
    private Label CPU_PER_SESSION;
    @FXML
    private Label CPU_PER_CALL;
    @FXML
    private Label LOGICAL_READS_PER_SESSION;
    @FXML
    private Label LOGICAL_READS_PER_CALL;
    @FXML
    private Label IDLE_TIME;
    @FXML
    private Label CONNECT_TIME;
    @FXML
    private Label PRIVATE_SGA;
    @FXML
    private Label FAILED_LOGIN_ATTEMPTS;
    @FXML
    private Label PASSWORD_LIFE_TIME;
    @FXML
    private Label PASSWORD_REUSE_TIME;
    @FXML
    private Label PASSWORD_REUSE_MAX;
    @FXML
    private Label PASSWORD_VERIFY_FUNCTION;
    @FXML
    private Label PASSWORD_LOCK_TIME;
    @FXML
    private Label PASSWORD_GRACE_TIME;
 
    
    
    
    @FXML
    private TableView profileTable = new TableView();
    
    ProfileFacade profileFacade = new ProfileFacade();
    
    private ProfileFXHelper profileFXHelper;
    
    private void initHelper() {
        profileFXHelper = new ProfileFXHelper(profileTable, profileFacade.getAllProfiles());
    }
    
    public void mouseClickedTable() {
        Profile profile = profileFXHelper.getSelected();
        if (profile != null) {
            COMPOSITE_LIMIT.setText(profile.getResource().getComposite_limit());
            SESSIONS_PER_USER.setText(profile.getResource().getSessions_per_user());
            CPU_PER_SESSION.setText(profile.getResource().getCpu_per_session());
            CPU_PER_CALL.setText(profile.getResource().getCpu_per_call());
            LOGICAL_READS_PER_SESSION.setText(profile.getResource().getLogical_reads_per_session());
            LOGICAL_READS_PER_CALL.setText(profile.getResource().getLogical_reads_per_call());
            IDLE_TIME.setText(profile.getResource().getIdle_time());
            CONNECT_TIME.setText(profile.getResource().getConnect_time());
            PRIVATE_SGA.setText(profile.getResource().getPrivate_sga());
            FAILED_LOGIN_ATTEMPTS.setText(profile.getResource().getFailed_login_attempts());
            PASSWORD_LIFE_TIME.setText(profile.getResource().getPassword_life_time());
            PASSWORD_REUSE_TIME.setText(profile.getResource().getPassword_reuse_time());
            PASSWORD_REUSE_MAX.setText(profile.getResource().getPassword_reuse_max());
            PASSWORD_VERIFY_FUNCTION.setText(profile.getResource().getPassword_verify_function());
            PASSWORD_LOCK_TIME.setText(profile.getResource().getPassword_lock_time());
            PASSWORD_GRACE_TIME.setText(profile.getResource().getPassword_grace_time());
//            objet.setText(bm.getObjet());
//            sender.setText(bm.getSender().getNom() + " " + bm.getSender().getPrenom() + " <" + bm.getSender().getLogin() + ">");
//            reciever.setText(bm.getReciever().getNom() + " " + bm.getReciever().getPrenom() + " <" + bm.getReciever().getLogin() + ">");
//            date.setText(bm.getDateEnvoi() + "");
//            idMessage.setText(bm.getId() + "");
//            typeMessage.setText("1");
        }
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
