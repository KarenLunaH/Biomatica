/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.Medicamento;
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
public class MedicamentoFacade extends AbstractFacade<Medicamento> implements MedicamentoFacadeLocal {

    @PersistenceContext(unitName = "BiomaticaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentoFacade() {
        super(Medicamento.class);
    }
    
    @Override
    public List<Medicamento> findAll() {
        Query q = em.createQuery("Select m FROM Medicamento m ORDER BY m.idMedicamento DESC",Medicamento.class);
        return q.getResultList();
    }
    
}
