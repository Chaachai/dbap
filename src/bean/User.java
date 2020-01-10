/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Youssef
 */
public class User {

    private int id;
    private String username;
    private Profile profile;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, Profile profile) {
        this.id = id;
        this.username = username;
        this.profile = profile;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", profile=" + profile + '}';
    }

}
