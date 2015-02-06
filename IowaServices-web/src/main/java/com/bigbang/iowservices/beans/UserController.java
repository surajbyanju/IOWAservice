package com.bigbang.iowservices.beans;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import com.bigbang.iowaservices.boundary.UsersFacade;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Address;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Rejina
 */
@ManagedBean
@RequestScoped
public class UserController {
    
   
    @EJB
    UsersFacadeLocal service;
    @EJB
    EmailService emailService;
    private Users user;
    
    /**
     * Creates a new instance of UserController
     */
    public UserController() {
        service = new UsersFacade();
        user = new Users();
    }
    
    public UsersFacadeLocal getService() {
        return service;
    }
    
    public void setService(UsersFacadeLocal service) {
        this.service = service;
    }
    
    public Users getUser() {
        return user;
    }
    
    public void setUser(Users user) {
        this.user = user;
    }
    
    
    
    
    public String register(){
        user.setEnabled(Boolean.TRUE);
        user.setRole("ROLE_ADMIN");
        service.create(user);
        
 
        String messageLink="http://localhost:8080/IowaServices-web/validateUser?userId="+user.getId();
            emailService.sendEmailAfterRegister(user.getUsername(),messageLink);
        
        
        return "home";
    }
}
