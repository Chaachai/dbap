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
public class ProfileFacade {

    Config c = new Config();
    ResourceFacade rf = new ResourceFacade();
//    String login = (String) Session.getAttribut("login");
//    String password = (String) Session.getAttribut("password");

    public List<Profile> getAllProfiles() {
        try {
            List<Profile> list = new ArrayList();
            ResultSet rs = c.loadData("select distinct profile from dba_profiles");
            if (rs == null) {
//                String error = "Sorry, it seems that you do not have the necessary privilege to use this application !";
//                Session.updateAttribute(error, "errorPrivilege");
                return null;
            }
            while (rs.next()) {
                Resource resource = rf.getRecourcesByProfile(rs.getString(1));
                Profile profile = new Profile();
                profile.setName(rs.getString(1));
                profile.setResource(resource);
                list.add(profile);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProfileFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Profile> findProfiles(String name) {
        try {
            List<Profile> list = new ArrayList();

            ResultSet rs = c.loadData(
                    "select distinct profile "
                    + "from dba_profiles "
                    + "where profile like '%" + name + "%' "
            );

            while (rs.next()) {
                Resource resource = rf.getRecourcesByProfile(rs.getString(1));
                Profile profile = new Profile();
                profile.setName(rs.getString(1));
                profile.setResource(resource);
                list.add(profile);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProfileFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Profile getProfileByName(String pName) {
        Profile p = new Profile(pName);
        Resource resource = rf.getRecourcesByProfile(pName);
        if (resource != null) {
            p.setResource(resource);
            return p;
        }
        return null;
    }

    public int createProfile(Profile profile) {
        String query;
        query = "CREATE PROFILE " + profile.getName() + " LIMIT \n";

        query += "SESSIONS_PER_USER " + profile.getResource().getSessions_per_user() + " \n";
        query += "CPU_PER_SESSION " + profile.getResource().getCpu_per_session() + " \n";
        query += "CPU_PER_CALL " + profile.getResource().getCpu_per_call() + " \n";
        query += "CONNECT_TIME " + profile.getResource().getConnect_time() + " \n";
        query += "LOGICAL_READS_PER_SESSION " + profile.getResource().getLogical_reads_per_session() + " \n";
        query += "LOGICAL_READS_PER_CALL " + profile.getResource().getLogical_reads_per_call() + " \n";
        query += "PRIVATE_SGA " + profile.getResource().getPrivate_sga() + " \n";
        query += "COMPOSITE_LIMIT " + profile.getResource().getComposite_limit() + " \n";

        query += "FAILED_LOGIN_ATTEMPTS " + profile.getResource().getFailed_login_attempts() + " \n";
        query += "PASSWORD_LIFE_TIME " + profile.getResource().getPassword_life_time() + " \n";
        query += "PASSWORD_REUSE_TIME " + profile.getResource().getPassword_reuse_time() + " \n";
        query += "PASSWORD_REUSE_MAX " + profile.getResource().getPassword_reuse_max() + " \n";
        query += "PASSWORD_VERIFY_FUNCTION " + profile.getResource().getPassword_verify_function() + " \n";
        query += "PASSWORD_LOCK_TIME " + profile.getResource().getPassword_lock_time() + " \n";
        query += "PASSWORD_GRACE_TIME " + profile.getResource().getPassword_grace_time() + " \n";
        query += "IDLE_TIME " + profile.getResource().getIdle_time() + " ";

        //   System.out.println(profile.toString());
        return c.execQuery(query);
    }

    public int editProfile(Profile profile) {
        String query;
        query = "ALTER PROFILE " + profile.getName() + " LIMIT \n";

        query += "SESSIONS_PER_USER " + profile.getResource().getSessions_per_user() + " \n";
        query += "CPU_PER_SESSION " + profile.getResource().getCpu_per_session() + " \n";
        query += "CPU_PER_CALL " + profile.getResource().getCpu_per_call() + " \n";
        query += "CONNECT_TIME " + profile.getResource().getConnect_time() + " \n";
        query += "LOGICAL_READS_PER_SESSION " + profile.getResource().getLogical_reads_per_session() + " \n";
        query += "LOGICAL_READS_PER_CALL " + profile.getResource().getLogical_reads_per_call() + " \n";
        query += "PRIVATE_SGA " + profile.getResource().getPrivate_sga() + " \n";
        query += "COMPOSITE_LIMIT " + profile.getResource().getComposite_limit() + " \n";

        query += "FAILED_LOGIN_ATTEMPTS " + profile.getResource().getFailed_login_attempts() + " \n";
        query += "PASSWORD_LIFE_TIME " + profile.getResource().getPassword_life_time() + " \n";
        query += "PASSWORD_REUSE_TIME " + profile.getResource().getPassword_reuse_time() + " \n";
        query += "PASSWORD_REUSE_MAX " + profile.getResource().getPassword_reuse_max() + " \n";
        query += "PASSWORD_VERIFY_FUNCTION " + profile.getResource().getPassword_verify_function() + " \n";
        query += "PASSWORD_LOCK_TIME " + profile.getResource().getPassword_lock_time() + " \n";
        query += "PASSWORD_GRACE_TIME " + profile.getResource().getPassword_grace_time() + " \n";
        query += "IDLE_TIME " + profile.getResource().getIdle_time() + " ";

        //   System.out.println(profile.toString());
        return c.execQuery(query);
    }

    public int dropProfile(String pName) {
        String query = "DROP PROFILE " + pName + " CASCADE ";
        return c.execQuery(query);
    }

}
