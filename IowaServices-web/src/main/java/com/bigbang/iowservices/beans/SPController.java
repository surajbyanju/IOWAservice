/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.ServiceRequestFacade;
import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacade;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedProperty;

/**
 *
 * @author sajana
 */
@Named(value = "sPController")
@Dependent

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
    public ServiceRequest sRequest;
    @ManagedProperty(value = "#{loginControl}")
    private LoginController loginControl;
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

        System.out.println("ser+++ " + serviceProvider + "  " + loginControl);
//        user = userService.getUserInfo(loginController.getUser().getUsername());
        sRequest.setAccepted(Boolean.FALSE);
        sRequest.setServiceProvider(serviceProvider);
        sRequest.setUsers(loginControl.getUser());
        sRequest.setRequestDate(null);
        service.create(sRequest);

        String mLink = "This is the test to sp";
        emailService.sendEmailAfterRegister("sajanamaharjan01@gmail.com", mLink);
        return "home";
    }

    public LoginController getLoginControl() {
        return loginControl;
    }

    public void setLoginControl(LoginController loginControl) {
        this.loginControl = loginControl;
    }

}
