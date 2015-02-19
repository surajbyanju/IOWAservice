/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.services.EmailService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dell
 */
@ManagedBean
@RequestScoped
public class ServiceProviderTaskController {

    /**
     * Creates a new instance of ServiceProviderTaskController
     */
    private List<ServiceRequest> newRequests;
    private List<ServiceRequest> completedTasks;
    @EJB
    EmailService emailService;
    @EJB
    ServiceRequestFacadeLocal serviceRequestFacadeLocal;
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    private ServiceRequest serviceRequest;
    private String subject;
    private String msgBody;

    @PostConstruct
    public void initData() {
        newRequests = serviceRequestFacadeLocal.findActiveTask(loginController.getUser());
        completedTasks = serviceRequestFacadeLocal.findInActiveTask(loginController.getUser());

    }

    public ServiceProviderTaskController() {
        newRequests = new ArrayList<>();
        completedTasks = new ArrayList<>();
    }

    public void approveTask() throws IOException {
        serviceRequest.setAccepted(Boolean.TRUE);
        serviceRequestFacadeLocal.edit(serviceRequest);
        subject = " Service Request Approved";

        msgBody = "Dear  " + serviceRequest.getUsers().getUserInformation().getFullName()
                + ", \n\n Service provider has approved your request.\n";
        msgBody = msgBody + getRequestDetail(serviceRequest);
        msgBody = msgBody + "\n\nThank you!!!";
        emailService.serviceRequestEmail(serviceRequest.getUsers().getUsername(), subject, msgBody);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//        return "main";
    }

    public void rejectTask() throws IOException {
        serviceRequest.setIsRejected(Boolean.TRUE);
        serviceRequestFacadeLocal.edit(serviceRequest);
        subject = " Service Request Rejected";

        msgBody = "Dear  " + serviceRequest.getUsers().getUserInformation().getFullName()
                + ", \n\n Service provider has rejected your request.\n ";
        msgBody = msgBody + getRequestDetail(serviceRequest);
        msgBody = msgBody + "\n\nThank you!!!";

        emailService.serviceRequestEmail(serviceRequest.getUsers().getUsername(), subject, msgBody);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

    private String getRequestDetail(ServiceRequest serviceRequest) {

        return "\nRequest Details:\nRequest to: " + serviceRequest.getServiceProvider().getUserInformation().getFullName() 
                + "\nRequest For: " + serviceRequest.getWorkDescription()
                + "\nRequest Date: " + serviceRequest.getRequestDate();

    }

    public List<ServiceRequest> getNewRequests() {
        return newRequests;
    }

    public void setNewRequests(List<ServiceRequest> newRequests) {
        this.newRequests = newRequests;
    }

    public List<ServiceRequest> getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(List<ServiceRequest> completedTasks) {
        this.completedTasks = completedTasks;
    }

    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public ServiceRequest getServiceRequest() {
        System.out.println("reqq+++ " + serviceRequest);
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

}
