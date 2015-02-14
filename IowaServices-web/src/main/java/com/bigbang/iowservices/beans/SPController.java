/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.ServiceRequestFacade;
import com.bigbang.iowaservices.boundary.ServiceRequestFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.services.EmailService;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

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
//    @EJB
//    ServiceRequestFacadeLocal service;
   @EJB
    EmailService emailService;
    public ServiceRequest sRequest;
  
    public SPController() {
//        service = new ServiceRequestFacade();
        sRequest = new ServiceRequest();
        
    }
 
    /**
     *
     * @param spId
     * @return
     */
    public String hireSP(Long spId) {
        System.out.println("ser+++ " + spId + "sebding service request");
        sRequest.setAccepted("False");
        sRequest.setSpId(spId);
//        serviceProviders = searchProviderService.searchServiceProviders(code);
//        user.setEnabled(Boolean.FALSE);
//        user.setRole("ROLE_USER");
//        service.create(sRequest);
//
        String mLink = "This is the test to sp";
        emailService.sendEmailAfterRegister("sajanamaharjan01@gmail.com", mLink);
        return "home";
//        return "services";
    }
}
