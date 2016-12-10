/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.Doctor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author KarenLunaH
 */
@Stateless
public class DoctorFacade extends AbstractFacade<Doctor> implements DoctorFacadeLocal {

    @PersistenceContext(unitName = "BiomaticaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DoctorFacade() {
        super(Doctor.class);
    }

    @Override
    public Doctor login(String email, String psw) {
        
        Query q = em.createQuery("SELECT d FROM Doctor d WHERE d.email= :e AND d.password= :p",Doctor.class);
        q.setParameter("e", email);
        q.setParameter("p", psw);
        return (Doctor)q.getSingleResult();
        
    }
    
    @Override
    public List<Doctor> findAll(){
        Query q = em.createQuery("Select d FROM Doctor d ORDER BY d.idDoctor DESC",Doctor.class);
        return q.getResultList();
    }
    
}
