/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.CommentFacadeLocal;
import com.bigbang.iowaservices.boundary.ServiceRequestFacade;
import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Comment;
import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author sajana
 */
@ManagedBean
@RequestScoped

public class SPController {
    
    /**
     * Creates a new instance of SPController
     */
    @EJB
            ServiceRequestFacadeLocal service;
    @EJB
            EmailService emailService;
    
    @EJB
            UsersFacadeLocal userService;
    @EJB
            CommentFacadeLocal commentFacade;
    private ServiceRequest sRequest;
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    private Users user;
    
    private Comment comments;
    
    public SPController() {
        service = new ServiceRequestFacade();
        sRequest = new ServiceRequest();
        comments = new Comment();
        
    }
    
    /**
     *
     * @param serviceProvider
     * @return
     */
    public String hireSP(ServiceProvider serviceProvider) {
        
//        username = userService.getUserInfo(loginController.getUser().getUsername());
        sRequest.setAccepted(Boolean.FALSE);
        sRequest.setServiceProvider(serviceProvider);
        sRequest.setUsers(loginController.getUser());
        sRequest.setRequestDate(new Date());
        sRequest.setWorkDescription("JUSt dummy for now");
        sRequest.setStartDate(new Date());
        service.create(sRequest);
        subject = "New Service Request";
        
        msgBody = "Dear Service Provider " + serviceProvider.getUserInformation().getFullName()
                + ", \n\n You have a new Service Request!"
                + "\n\n\n\n please check your Iowa Services account ";
        
        emailService.serviceRequestEmail(serviceProvider.getUsername(), subject, msgBody);
        return "home";
    }
    
    public LoginController getLoginController() {
        return loginController;
    }
    
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    public void postComment(ServiceProvider provider) throws IOException {
        
        comments.setServiceProvider(provider);
        
        Date curDate = new Date();
        
        comments.setCreationDate(curDate);
        commentFacade.create(comments);
        
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + "/viewProviderDetail.jsf?providerId=" + provider.getId());
    }
    
    public Comment getComments() {
        return comments;
    }
    
    public void setComments(Comment comments) {
        this.comments = comments;
    }
    
}
