/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.ServiceProvider;
import com.bigbang.iowaservices.entities.ServiceProvider_;
import com.bigbang.iowaservices.entities.Skill;
import com.bigbang.iowaservices.entities.Skill_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

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

    @Override
    public List<ServiceProvider> searchServiceProvider(String providerString) {
        System.out.println("prov+++ "+providerString);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ServiceProvider> criteriaQuery = builder.createQuery(ServiceProvider.class);
        Root<ServiceProvider> root = criteriaQuery.from(ServiceProvider.class);
        Join<ServiceProvider,Skill> join1 = root.join(ServiceProvider_.skills);
        criteriaQuery.select(root).where(builder.like(join1.get(Skill_.name),"%"+providerString+"%"));
        TypedQuery<ServiceProvider> typedQuery = em.createQuery(criteriaQuery);
        
        return typedQuery.getResultList();
    }
    
}
