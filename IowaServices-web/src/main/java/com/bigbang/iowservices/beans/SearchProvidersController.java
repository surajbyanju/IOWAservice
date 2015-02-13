/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.SearchProviderService;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author dell
 */
@Named(value = "searchProvidersController")
@RequestScoped
public class SearchProvidersController {

    private String searchString;
    @EJB
    SearchProviderService searchProviderService;
    private List<ServiceProvider> serviceProviders;
    /**
     * Creates a new instance of SearchProvidersController
     */
    public SearchProvidersController() {
        serviceProviders = new ArrayList<>();
    }
    
    public List<Users> findAllServiceProviders(){
        
        return null;
    
    } 
    
    public String searchServiceProviders(){
        System.out.println("ser+++ "+searchString);
        serviceProviders = searchProviderService.searchServiceProviders(searchString);
        return "serviceProviderList";
    }
    
      public String spByCode(String code){
        System.out.println("ser+++ "+code);
        serviceProviders = searchProviderService.searchServiceProviders(code);
        return "serviceDetails";
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public List<ServiceProvider> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<ServiceProvider> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }
    
    
    
}
