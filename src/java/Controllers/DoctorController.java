/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Ejbs.DatosFiscalesFacadeLocal;
import Ejbs.DireccionFacadeLocal;
import Ejbs.DoctorFacadeLocal;
import Models.Antescedentes;
import Models.DatosFiscales;
import Models.Direccion;
import Models.Doctor;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author KarenLunaH
 */

@Named
@ViewScoped
public class DoctorController implements Serializable{
    
    /*
    *Etiqueta ViewScoped, indica que este controlador podrá
    *ser visto/usado en la vista en la que se mande a llamar,
    * es decir, no guardará información durante toda la sesión.
    */
    
    @EJB
    DoctorFacadeLocal doctorEJB;
    private Doctor doctor;
    private List<Doctor> doctores;
    private DatosFiscales datosFiscales,datosFiscalesConsulta;
    
    @EJB
    private DatosFiscalesFacadeLocal datosFiscalesEjb;
    
    @EJB
    private DireccionFacadeLocal direccionEjb;
    
    @PostConstruct
    public void init(){
        doctor = new Doctor();
        doctores = new ArrayList<Doctor>();
        datosFiscales = new DatosFiscales();
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
    
    public void crearDatosFiscales(){
        
        this.datosFiscales.setRfc(this.datosFiscales.getRfc().toUpperCase());
        this.datosFiscales.setIdDoctor(doctor);
        this.doctor.getDatosFiscalesList().add(datosFiscales);
        this.datosFiscalesEjb.create(datosFiscales);
        this.doctorEJB.edit(doctor);
        this.datosFiscales = null;
        this.datosFiscales = new DatosFiscales();
        this.setDatosFiscalesConsulta(this.doctor.getDatosFiscalesList().get(0));
        
    }
    
    public boolean hasDatosFiscales(){
        return (this.doctor.getDatosFiscalesList().size()>0)? true:false;
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

    /**
     * @return the datosFiscales
     */
    public DatosFiscales getDatosFiscales() {
        return datosFiscales;
    }

    /**
     * @param datosFiscales the datosFiscales to set
     */
    public void setDatosFiscales(DatosFiscales datosFiscales) {
        this.datosFiscales = datosFiscales;
    }
    
    public void findDireccion(){
        int cp = this.datosFiscales.getIdDireccion().getCodigoPostal();
        Direccion d = this.direccionEjb.find(cp);
        if(d!=null){
            this.datosFiscales.getIdDireccion().setColonia(d.getColonia());
            this.datosFiscales.getIdDireccion().setEstado(d.getEstado());
            this.datosFiscales.getIdDireccion().setMunicipio(d.getMunicipio());
        }
    }
    
    
    public void viewPage(Object id) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("idDoctor", id);
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/views/doctor/View.xhtml");
    }

    public void find() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Object id = sessionMap.get("idDoctor");
        this.doctor = this.doctorEJB.find(id);
        this.setDatosFiscalesConsulta(new DatosFiscales());
        if (this.hasDatosFiscales()) {
            this.setDatosFiscalesConsulta(this.doctor.getDatosFiscalesList().get(0));
        }
    }

    /**
     * @return the datosFiscalesConsulta
     */
    public DatosFiscales getDatosFiscalesConsulta() {
        return datosFiscalesConsulta;
    }

    /**
     * @param datosFiscalesConsulta the datosFiscalesConsulta to set
     */
    public void setDatosFiscalesConsulta(DatosFiscales datosFiscalesConsulta) {
        this.datosFiscalesConsulta = datosFiscalesConsulta;
    }
    
    public void findDatosFiscalesEdit(){
        this.datosFiscales = this.doctor.getDatosFiscalesList().get(0);
    }
    
    public void editDatosFiscales() {
        this.doctor.getDatosFiscalesList().set(0, datosFiscales);
        this.doctorEJB.edit(doctor);
        this.datosFiscalesConsulta = this.doctor.getDatosFiscalesList().get(0);
    }
    
    
    
}
