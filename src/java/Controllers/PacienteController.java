/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Ejbs.AntescedentesFacadeLocal;
import Ejbs.DireccionFacadeLocal;
import Ejbs.MedicamentoFacadeLocal;
import Ejbs.PacienteFacadeLocal;
import Models.Antescedentes;
import Models.Direccion;
import Models.Medicamento;
import Models.Paciente;
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
public class PacienteController implements Serializable{
    
    @EJB
    private PacienteFacadeLocal pacienteEjb;
    private Paciente paciente;
    private List<Paciente> pacientes;
    private Medicamento medicamento;
    private Antescedentes antescedentes, antescedentesConsulta;
   
    
    @EJB
    private DireccionFacadeLocal direccionEjb;
    
    @EJB
    private MedicamentoFacadeLocal medicamentoEjb;
    
    @EJB
    private AntescedentesFacadeLocal antescedentesEjb;
    
    @PostConstruct
    public void init(){
        this.paciente = new Paciente();
        this.pacientes = new ArrayList<Paciente>();
        this.medicamento = new Medicamento();//instanciamos medicamento
        this.antescedentes = new Antescedentes();
    }
    
    public void crearAntescedentes(){
       this.antescedentes.setIdPaciente(paciente);
        this.paciente.getAntescedentesList().add(antescedentes);
        this.antescedentesEjb.create(antescedentes);
        this.pacienteEjb.edit(paciente);
        this.antescedentes=null;
        this.antescedentes=new Antescedentes();
        this.antescedentesConsulta = this.paciente.getAntescedentesList().get(0);
    }
    
    public void findAntescedentesEdit(){
        this.antescedentes=this.paciente.getAntescedentesList().get(0);
    }
    
    public void editAntescedentes(){
        this.antescedentesEjb.edit(antescedentes);
        this.pacienteEjb.edit(paciente);
        this.antescedentesConsulta = this.paciente.getAntescedentesList().get(0);
    }
    
    public void listar(){
        this.pacientes = this.pacienteEjb.findAll();
    }
    
    public void crear(){
        this.pacienteEjb.create(paciente);
        this.paciente = null;
        this.paciente = new Paciente();
    }
    
    public void crearMedicamento(){
        this.medicamento.setIdPaciente(paciente);
        this.paciente.getMedicamentoList().add(medicamento);
        this.medicamentoEjb.create(medicamento);
        this.pacienteEjb.edit(paciente);
        this.medicamento=null;
        this.medicamento=new Medicamento();
    }
    
    public void findForEditMedicamento(Object id){
        this.medicamento = this.medicamentoEjb.find(id);
    }
    
    public void editarMedicamento(){
        this.medicamentoEjb.edit(medicamento);
        this.medicamento=null;
        this.medicamento=new Medicamento();
    }
    
    public void eliminarMedicamento(Medicamento m){
        this.paciente.getMedicamentoList().remove(m);
        this.pacienteEjb.edit(paciente);
        this.medicamentoEjb.remove(m);
    }
    
    public void limpiarformulario(){
        this.paciente = null;
        this.paciente = new Paciente();
    }
    
    public void findForEdit(Object id){
        this.paciente = this.pacienteEjb.find(id);
    }
    
    public void editar(){
        this.pacienteEjb.edit(paciente);
        this.limpiarformulario();
    }
    
    public void findDireccion(){
        int cp = this.paciente.getIdDireccion().getCodigoPostal();
        Direccion d = this.direccionEjb.find(cp);
        if(d != null){
            this.paciente.getIdDireccion().setColonia(d.getColonia());
            this.paciente.getIdDireccion().setMunicipio(d.getMunicipio());
            this.paciente.getIdDireccion().setEstado(d.getEstado());
        }
    }
    
    public boolean tieneRegistradoAntescedentes(){
        /*
        if ternario
        (condicion) ? (si se cumple) valor verdadero (else:else) valor falso
        */
        return (this.paciente.getAntescedentesList().size() > 0 )? true : false; 
    }
            
            
    /**
     * @return the paciente
     */
    public Paciente getPaciente() {
        return paciente;
    }
    
    
    public void updateRFC(){
        String cu = "";
        for(int i = 0;i<this.paciente.getCurp().length() && i<10;i++){
            cu+=this.paciente.getCurp().toUpperCase().charAt(i);
        }
        this.paciente.setRfc(cu);
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * @return the pacientes
     */
    public List<Paciente> getPacientes() {
        return pacientes;
    }

    /**
     * @param pacientes the pacientes to set
     */
    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    
    public void viewPage(Object id) throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("idPaciente", id);
        String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
        FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/faces/views/paciente/View.xhtml");
    }
    
    public void find(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        Object id = sessionMap.get("idPaciente");
        this.paciente = this.pacienteEjb.find(id);
           this.antescedentesConsulta = new Antescedentes();
        if(this.tieneRegistradoAntescedentes()){
            this.antescedentesConsulta = this.paciente.getAntescedentesList().get(0);
        }
    }

    /**
     * @return the medicamento
     */
    public Medicamento getMedicamento() {
        return medicamento;
    }

    /**
     * @param medicamento the medicamento to set
     */
    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    /**
     * @return the antescedentes
     */
    public Antescedentes getAntescedentes() {
        return antescedentes;
    }

    /**
     * @param antescedentes the antescedentes to set
     */
    public void setAntescedentes(Antescedentes antescedentes) {
        this.antescedentes = antescedentes;
    }

    /**
     * @return the antescedentesConsulta
     */
    public Antescedentes getAntescedentesConsulta() {
        return antescedentesConsulta;
    }

    /**
     * @param antescedentesConsulta the antescedentesConsulta to set
     */
    public void setAntescedentesConsulta(Antescedentes antescedentesConsulta) {
        this.antescedentesConsulta = antescedentesConsulta;
    }
    
    
    
}
