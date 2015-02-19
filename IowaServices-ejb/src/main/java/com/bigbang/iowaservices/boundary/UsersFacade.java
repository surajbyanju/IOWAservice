/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author dell
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "IOWA_SERVICE")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }

    @Override
    public Users getUserInfo(String email) {
        try {
            return (Users) em.createNamedQuery("findUsersByUsername").setParameter("username", email).getSingleResult();

        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Users> findActiveUsers() {
        try {
            return em.createNamedQuery("findActiveUsers").getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
