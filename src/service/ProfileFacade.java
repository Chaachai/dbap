/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Profile;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Youssef
 */
public class ProfileFacade {
    
    Config c = new Config();
    
    public List<Profile> getAllProfiles() {
        try {
            List<Profile> list = new ArrayList();
            ResultSet rs = c.loadData("SYSTEM","SYSTEM" ,"select distinct profile from dba_profiles");

            while (rs.next()) {
                Profile profile = new Profile();
//                Salarie sal = salarieFacade.getSalarieById(rs.getLong(3));
//                profile.setId(rs.getString(1));
                profile.setName(rs.getString(1));
//                dp.setResponsable(sal);
                list.add(profile);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProfileFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
