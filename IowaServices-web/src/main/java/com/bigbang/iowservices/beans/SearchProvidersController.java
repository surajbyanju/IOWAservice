/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowservices.beans;

import com.bigbang.iowaservices.boundary.ServiceProviderFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.Users;
import com.bigbang.iowaservices.services.SearchProviderService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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
    @EJB
    ServiceProviderFacadeLocal serviceProviderFacadeLocal;
    private List<ServiceProvider> serviceProviders;
    private ServiceProvider serviceProvider;

    /**
     * Creates a new instance of SearchProvidersController
     */
    @PostConstruct
    public void initProviders() {
        
        serviceProviders=serviceProviderFacadeLocal.findAll();
        System.out.println("ser+  "+getServiceProviders());
    }

    public SearchProvidersController() {
        serviceProviders = new ArrayList<>();
    }

    public List<Users> findAllServiceProviders() {

        return null;

    }

    public String searchServiceProviders() {
        serviceProviders = searchProviderService.searchServiceProviders(searchString);
        return "serviceProviderList";
    }

    public String spByCode(String code) {
        serviceProviders = searchProviderService.searchServiceProviders(code);
        return "serviceDetails";
    }

    public void preRenderView(String providerId) {
        if (!providerId.isEmpty()) {
            serviceProvider = serviceProviderFacadeLocal.find(Long.parseLong(providerId));
        }
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

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

}
