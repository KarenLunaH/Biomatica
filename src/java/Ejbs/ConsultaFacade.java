/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejbs;

import Models.Consulta;
import Models.Doctor;
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
public class ConsultaFacade extends AbstractFacade<Consulta> implements ConsultaFacadeLocal {

    @PersistenceContext(unitName = "BiomaticaPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultaFacade() {
        super(Consulta.class);
    }

    @Override
    public List<Consulta> proximasConsultas(Doctor d) {
        Query q = em.createQuery("Select c FROM Consulta c WHERE c.idDoctor= :idDoctor AND c.registrado = :registrado ORDER BY c.fechaHoraFin ASC", Medicamento.class);
        q.setParameter("idDoctor", d);
        q.setParameter("registrado", false);
        return q.getResultList();
    }
    
}
