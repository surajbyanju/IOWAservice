/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.ServiceRequest;
import com.bigbang.iowaservices.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author dell
 */
@Stateless
public class ServiceRequestFacade extends AbstractFacade<ServiceRequest> implements ServiceRequestFacadeLocal {
    @PersistenceContext(unitName = "IOWA_SERVICE")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceRequestFacade() {
        super(ServiceRequest.class);
    }

    @Override
    public List<ServiceRequest> findActiveTask(Users user) {
        return em.createNamedQuery("findNewRequestForProvider").setParameter("user", user).getResultList();
    }
    
    @Override
    public List<ServiceRequest> findInActiveTask(Users user) {
        return em.createNamedQuery("findOldRequestForProvider").setParameter("user", user).getResultList();
    }
    
    @Override
    public List<ServiceRequest> findActiveTaskOfUser(Users user) {
        return em.createNamedQuery("findNewRequestOfUser").setParameter("user", user).getResultList();
    }
    
    @Override
    public List<ServiceRequest> findInActiveTaskOfUser(Users user) {
        return em.createNamedQuery("findOldRequestOfUser").setParameter("user", user).getResultList();
    }
    
}
