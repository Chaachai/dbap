/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.util.List;

/**
 *
 * @author CHAACHAI Youssef
 */
public class Profile {

//    private Long id;
    private String name;
    private Resource resource;

    public Profile() {
    }

//    public Profile(Long id) {
//        this.id = id;
//    }
    public Profile(String name) {
        this.name = name;
    }

//    public Long getId() {
//        return id;
//    }
//    public void setId(Long id) {
//        this.id = id;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public String toString() {
        return name;
    }

}
