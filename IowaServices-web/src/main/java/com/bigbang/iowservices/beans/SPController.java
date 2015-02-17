/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.ServiceRequestFacade;
import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

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
    private ServiceRequest sRequest;
     @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    private Users user;

    public SPController() {
        service = new ServiceRequestFacade();
        sRequest = new ServiceRequest();

    }

    /**
     *
     * @param serviceProvider
     * @return
     */
    public String hireSP(ServiceProvider serviceProvider) {

//        user = userService.getUserInfo(loginController.getUser().getUsername());
        sRequest.setAccepted(Boolean.FALSE);
        sRequest.setServiceProvider(serviceProvider);
        sRequest.setUsers(loginController.getUser());
        sRequest.setRequestDate(null);
        service.create(sRequest);

        String mLink = "This is the test to sp";
        emailService.sendEmailAfterRegister("sajanamaharjan01@gmail.com", mLink);
        return "home";
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }


    public ServiceRequest getsRequest() {
        return sRequest;
    }

    public void setsRequest(ServiceRequest sRequest) {
        this.sRequest = sRequest;
    }
    

}
