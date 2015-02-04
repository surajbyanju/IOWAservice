package com.bigbang.iowservices.beans;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import com.bigbang.iowaservices.boundary.UsersFacade;
import com.bigbang.iowaservices.entities.Users;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author Rejina
 */
@ManagedBean
@RequestScoped
public class UserController {
    
    @EJB
    private UsersFacade usersFacade;
    private Users user;

    public UsersFacade getUsersFacade() {
        return usersFacade;
    }

    public void setUsersFacade(UsersFacade usersFacade) {
        this.usersFacade = usersFacade;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
    }
    
    public String register(){
        this.usersFacade.create(user);
        return "home";
    }
}
