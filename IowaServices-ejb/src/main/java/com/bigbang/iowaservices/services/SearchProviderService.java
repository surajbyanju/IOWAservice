/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.services;

import com.bigbang.iowaservices.boundary.ServiceProviderFacadeLocal;
import com.bigbang.iowaservices.entities.ServiceProvider;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author dell
 */
@Stateless
public class SearchProviderService {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    ServiceProviderFacadeLocal serviceP;
    
    public List<ServiceProvider> searchServiceProviders(String searchString){
        return serviceP.searchServiceProvider(searchString);
    
    }
    
}
