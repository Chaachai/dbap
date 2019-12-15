/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;

/**
 *
 * @author Youssef
 */
public class Resource {

//    private int id;
    private String cpu_per_session;
    private String cpu_per_call;
    private String connect_time;
    private String idle_time;
    
    private String sessions_per_user;
    private String logical_reads_per_session;
    private String logical_reads_per_call;
    private String private_sga;
    private String composite_limit;
    
    private String password_life_time; //password expiry
    private String failed_login_attempts;
    private String password_lock_time;
    private String password_reuse_max;
    private String cpu_per_session;
    private String cpu_per_session;
    private String cpu_per_session;
//    private String limit;
//    private TypeRes type;

    public Resource() {
    }

    public Resource(int id) {
        this.id = id;
    }

    public Resource(int id, String name, String dipName, String limit, TypeRes type) {
        this.id = id;
        this.name = name;
        this.limit = limit;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public TypeRes getType() {
        return type;
    }

    public void setType(TypeRes type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return name;
    }

}
