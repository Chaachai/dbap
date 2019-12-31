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
    private String password_grace_time;
    private String password_reuse_max;
    private String password_reuse_time;
    private String password_verify_function;
    private String failed_login_attempts;
    private String password_lock_time;
    
    public Resource() {
    }

    public String getCpu_per_session() {
        return cpu_per_session;
    }

    public void setCpu_per_session(String cpu_per_session) {
        this.cpu_per_session = cpu_per_session;
    }

    public String getCpu_per_call() {
        return cpu_per_call;
    }

    public void setCpu_per_call(String cpu_per_call) {
        this.cpu_per_call = cpu_per_call;
    }

    public String getConnect_time() {
        return connect_time;
    }

    public void setConnect_time(String connect_time) {
        this.connect_time = connect_time;
    }

    public String getIdle_time() {
        return idle_time;
    }

    public void setIdle_time(String idle_time) {
        this.idle_time = idle_time;
    }

    public String getSessions_per_user() {
        return sessions_per_user;
    }

    public void setSessions_per_user(String sessions_per_user) {
        this.sessions_per_user = sessions_per_user;
    }

    public String getLogical_reads_per_session() {
        return logical_reads_per_session;
    }

    public void setLogical_reads_per_session(String logical_reads_per_session) {
        this.logical_reads_per_session = logical_reads_per_session;
    }

    public String getLogical_reads_per_call() {
        return logical_reads_per_call;
    }

    public void setLogical_reads_per_call(String logical_reads_per_call) {
        this.logical_reads_per_call = logical_reads_per_call;
    }

    public String getPrivate_sga() {
        return private_sga;
    }

    public void setPrivate_sga(String private_sga) {
        this.private_sga = private_sga;
    }

    public String getComposite_limit() {
        return composite_limit;
    }

    public void setComposite_limit(String composite_limit) {
        this.composite_limit = composite_limit;
    }

    public String getPassword_life_time() {
        return password_life_time;
    }

    public void setPassword_life_time(String password_life_time) {
        this.password_life_time = password_life_time;
    }

    public String getPassword_grace_time() {
        return password_grace_time;
    }

    public void setPassword_grace_time(String password_grace_time) {
        this.password_grace_time = password_grace_time;
    }

    public String getPassword_reuse_max() {
        return password_reuse_max;
    }

    public void setPassword_reuse_max(String password_reuse_max) {
        this.password_reuse_max = password_reuse_max;
    }

    public String getPassword_reuse_time() {
        return password_reuse_time;
    }

    public void setPassword_reuse_time(String password_reuse_time) {
        this.password_reuse_time = password_reuse_time;
    }

    public String getPassword_verify_function() {
        return password_verify_function;
    }

    public void setPassword_verify_function(String password_verify_function) {
        this.password_verify_function = password_verify_function;
    }

    public String getFailed_login_attempts() {
        return failed_login_attempts;
    }

    public void setFailed_login_attempts(String failed_login_attempts) {
        this.failed_login_attempts = failed_login_attempts;
    }

    public String getPassword_lock_time() {
        return password_lock_time;
    }

    public void setPassword_lock_time(String password_lock_time) {
        this.password_lock_time = password_lock_time;
    }
    
    

    @Override
    public String toString() {
        return "Resource{" 
                + "cpu_per_session=" 
                + cpu_per_session 
                + ", cpu_per_call=" 
                + cpu_per_call 
                + ", connect_time=" 
                + connect_time 
                + ", idle_time=" 
                + idle_time 
                + ", sessions_per_user=" 
                + sessions_per_user 
                + ", logical_reads_per_session=" 
                + logical_reads_per_session 
                + ", logical_reads_per_call=" 
                + logical_reads_per_call 
                + ", private_sga=" 
                + private_sga 
                + ", composite_limit=" 
                + composite_limit + ", password_life_time=" 
                + password_life_time + ", password_grace_time=" 
                + password_grace_time + ", password_reuse_max=" 
                + password_reuse_max + ", password_reuse_time=" 
                + password_reuse_time + ", password_verify_function=" 
                + password_verify_function + ", failed_login_attempts=" 
                + failed_login_attempts 
                + ", password_lock_time=" 
                + password_lock_time 
                + '}';
    }
    
    
    

}
