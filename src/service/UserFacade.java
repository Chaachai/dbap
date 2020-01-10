/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Profile;
import bean.Resource;
import bean.User;
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
public class UserFacade {

    Config c = new Config();
    ProfileFacade profileFacade = new ProfileFacade();
    String login = (String) Session.getAttribut("login");
    String password = (String) Session.getAttribut("password");

    public String getUsername() {
        try {
            String username = "";
            ResultSet rs = c.loadData("SELECT USER FROM DUAL");
            while (rs.next()) {
                username = rs.getString(1);
            }
            return username;
        } catch (SQLException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return "SYSTEM";
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> list = new ArrayList();

            ResultSet rs = c.loadData("SELECT USER_ID, USERNAME, PROFILE FROM DBA_USERS");

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                Profile profile = profileFacade.getProfileByName(rs.getString(3));
                user.setProfile(profile);
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProfileFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<User> findUsers(String name) {
        try {
            List<User> list = new ArrayList();

            ResultSet rs = c.loadData(
                    "SELECT USER_ID, USERNAME, PROFILE "
                    + "FROM DBA_USERS "
                    + "WHERE USERNAME like '%" + name + "%' "
            );

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                Profile profile = profileFacade.getProfileByName(rs.getString(3));
                user.setProfile(profile);
                list.add(user);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ProfileFacade.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int assignProfile(String username, String pName) {
        return c.execQuery("ALTER USER " + username + " PROFILE " + pName);
    }

    public boolean getResourceLimit() {
        try {
            String val = "";
            ResultSet rs = c.loadData("SELECT VALUE FROM V$PARAMETER WHERE NAME = 'resource_limit'");
            while (rs.next()) {
                val = rs.getString(1);
            }
            if (val.equalsIgnoreCase("true")) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserFacade.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public int setResourceLimit(String res) {
        return c.execQuery("ALTER SYSTEM SET RESOURCE_LIMIT = " + res + " SCOPE=BOTH");
    }
}
