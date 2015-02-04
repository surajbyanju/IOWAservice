/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.ServiceProvider;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dell
 */
@Stateless
public class ServiceProviderFacade extends AbstractFacade<ServiceProvider> implements ServiceProviderFacadeLocal {
    @PersistenceContext(unitName = "IOWA_SERVICE")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceProviderFacade() {
        super(ServiceProvider.class);
    }
    
}
