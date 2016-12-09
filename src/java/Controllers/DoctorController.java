/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Ejbs.DoctorFacadeLocal;
import Models.Doctor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author KarenLunaH
 */

@Named
@ViewScoped
public class DoctorController implements Serializable{
    
    @EJB
    DoctorFacadeLocal doctorEJB;
    private Doctor doctor;
    private List<Doctor> doctores;
    
    @PostConstruct
    public void init(){
        doctor = new Doctor();
        doctores = new ArrayList<Doctor>();
    }
    
    public void listar(){
        this.doctores = this.doctorEJB.findAll();
    }
    
    public void crear(){
        this.doctorEJB.create(doctor);
        this.doctor.limpiar();
        this.doctor = null;
        this.doctor = new Doctor();
    }
    
    public void findForEdit(Object id){
        this.doctor = this.doctorEJB.find(id);
    }
    
    
    public void editar(){
        this.doctorEJB.edit(doctor);
        this.doctor.limpiar();
        this.doctor = null;
        this.doctor = new Doctor();
    }
    
    public void limpiarFormulario(){
        this.doctor.limpiar();
        this.doctor = null;
        this.doctor = new Doctor();
    }

    /**
     * @return the doctor
     */
    public Doctor getDoctor() {
        return doctor;
    }

    /**
     * @param doctor the doctor to set
     */
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * @return the doctores
     */
    public List<Doctor> getDoctores() {
        return doctores;
    }

    /**
     * @param doctores the doctores to set
     */
    public void setDoctores(List<Doctor> doctores) {
        this.doctores = doctores;
    }
    
    
    
}
