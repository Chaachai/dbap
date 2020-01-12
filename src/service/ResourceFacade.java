/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Profile;
import bean.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Session;

/**
 *
 * @author Youssef
 */
public class ResourceFacade {

    Config c = new Config();
    String login = (String) Session.getAttribut("login");
    String password = (String) Session.getAttribut("password");

    public Resource getRecourcesByProfile(String profile) {
        try {
            Resource resource = new Resource();
            List<String> limits = new ArrayList();
            ResultSet rs = c.loadData(
                    "SELECT limit "
                    + "from dba_profiles "
                    + "where profile = '" + profile + "' "
            );

            while (rs.next()) {
                limits.add(rs.getString(1));
            }
            resource.setComposite_limit(limits.get(0));
            resource.setSessions_per_user(limits.get(1));
            resource.setCpu_per_session(limits.get(2));
            resource.setCpu_per_call(limits.get(3));
            resource.setLogical_reads_per_session(limits.get(4));
            resource.setLogical_reads_per_call(limits.get(5));
            resource.setIdle_time(limits.get(6));
            resource.setConnect_time(limits.get(7));
            resource.setPrivate_sga(limits.get(8));
            resource.setFailed_login_attempts(limits.get(9));
            resource.setPassword_life_time(limits.get(10));
            resource.setPassword_reuse_time(limits.get(11));
            resource.setPassword_reuse_max(limits.get(12));
            resource.setPassword_verify_function(limits.get(13));
            resource.setPassword_lock_time(limits.get(14));
            resource.setPassword_grace_time(limits.get(15));

            rs.close();
            return resource;
        } catch (SQLException ex) {
            Logger.getLogger(ResourceFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }
}
