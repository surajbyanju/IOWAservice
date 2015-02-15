/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bigbang.iowaservices.boundary;

import com.bigbang.iowaservices.entities.Skill;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dell
 */
@Stateless
public class SkillFacade extends AbstractFacade<Skill> implements SkillFacadeLocal {
    @PersistenceContext(unitName = "IOWA_SERVICE")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SkillFacade() {
        super(Skill.class);
    }

    @Override
    public Skill findByCode(String code) {
    
         try {
                Query query = em.createNamedQuery("findSkillByCode");
                query.setParameter("code", code);    
                return (Skill)query.getSingleResult();
          } catch (NoResultException e) {
            return null;
          }
      
        }
    }
    
