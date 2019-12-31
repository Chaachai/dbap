/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CHAACHAI Youssef
 */
public class Config {

    public Connection connect(String username, String password) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");

            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:dbap", username, password);

            if (con != null) {
                System.out.println("Connected !");
            } else {
                System.out.println("Not connected !!");
            }

            return con;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public ResultSet loadData(String username, String password, String query) {
        Connection con = connect(username, password);
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public int execQuery(String username, String password, String query) {
        Connection con = connect(username, password);
        try {
            Statement stmt = con.createStatement();
            System.out.println("*****************************");
            System.out.println(query);
            stmt.executeUpdate(query);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            return - 1;
        }

    }

//    public Long generateId(String tableName, String idName) {
//        int maxId = 0;
//        String query = " SELECT max(" + idName + ") FROM " + tableName + " ";
//        ResultSet rs = loadData(query);
//        try {
//            while (rs.next()) {
//                maxId = rs.getInt(1);
//            }
//            return maxId + 1L;
//        } catch (SQLException ex) {
//            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
//            return 1L;
//        }        
//    }
}
