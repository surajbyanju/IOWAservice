/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.CommentFacadeLocal;
import com.bigbang.iowaservices.boundary.ServiceProviderFacadeLocal;
import com.bigbang.iowaservices.boundary.ServiceRequestFacade;
import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.boundary.UsersFacadeLocal;
import com.bigbang.iowaservices.entities.Comment;
import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.EmailService;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
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

public class ServiceProviderController {

    /**
     * Creates a new instance of ServiceProviderController
     */
    @EJB
    ServiceRequestFacadeLocal service;
    @EJB
    EmailService emailService;

    @EJB
    UsersFacadeLocal userService;
    @EJB
    CommentFacadeLocal commentFacade;
    private ServiceRequest serviceRequest;
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    @EJB
    ServiceProviderFacadeLocal serviceProviderFacadeLocal;
    private Users user;
    private String subject;
    private String msgBody;

    private Comment comments;
    private ServiceProvider serviceProvider;

    public ServiceProviderController() {
        service = new ServiceRequestFacade();
        serviceRequest = new ServiceRequest();
        comments = new Comment();
        serviceProvider = new ServiceProvider();

    }

    /**
     *
     * @param serviceProvider
     * @return
     */
    public void preRenderView(String providerId) {
        if (!providerId.isEmpty()) {
            serviceProvider = serviceProviderFacadeLocal.find(Long.parseLong(providerId));
            serviceRequest.setStartDate(new Date());
            System.out.println("servvvvvvvvv +++ "+serviceProvider);
        }
    }

    public String hireSP() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        serviceProvider = serviceProviderFacadeLocal.find(Long.parseLong(params.get("serviceProviderId")));
        System.out.println("service pr  "+serviceProvider);
        serviceRequest.setAccepted(Boolean.FALSE);
        serviceRequest.setServiceProvider(serviceProvider);
        serviceRequest.setUsers(loginController.getUser());
        serviceRequest.setRequestDate(new Date());
//        serviceRequest.setStartDate(new Date());
        service.create(serviceRequest);
        subject = "New Service Request";

        msgBody = "Dear Service Provider " + serviceProvider.getUserInformation().getFullName()
                + ", \n\n You have a new Service Request!"
                + "\n\n\n\n please check your Iowa Services account ";

        emailService.serviceRequestEmail(serviceProvider.getUsername(), subject, msgBody);
        FacesContext.getCurrentInstance().addMessage("test", new javax.faces.application.FacesMessage("Your Service Request has been sent."));
        return "home";
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public void postComment() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        ServiceProvider provider = serviceProviderFacadeLocal.find(Long.parseLong(params.get("serviceProviderId")));
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

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

}
