/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceRequest;
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
 * @author sajana
 */
//@Named(value = "usersTaskController")
@ManagedBean
@RequestScoped
public class UserTaskController {

    private List<ServiceRequest> newRequests;
    private List<ServiceRequest> completedTasks;
    @EJB
    ServiceRequestFacadeLocal serviceRequestFacadeLocal;
    @ManagedProperty(value = "#{loginController}")
    private LoginController loginController;
    private ServiceRequest serviceRequest;

    @PostConstruct
    public void initData() {

        newRequests = serviceRequestFacadeLocal.findActiveTaskOfUser(loginController.getUser());
        completedTasks = serviceRequestFacadeLocal.findInActiveTaskOfUser(loginController.getUser());

    }

    /**
     * Creates a new instance of UsersTaskController
     */
    public UserTaskController() {
        newRequests = new ArrayList<>();
        completedTasks = new ArrayList<>();
    }

    public void approveTask() throws IOException {
        serviceRequest.setAccepted(Boolean.TRUE);
        serviceRequestFacadeLocal.edit(serviceRequest);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
//        return "main";
    }

    public void rejectTask() throws IOException {
        serviceRequest.setIsRejected(Boolean.TRUE);
        serviceRequestFacadeLocal.edit(serviceRequest);
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
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
