/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;

import Ejbs.AntescedentesFacadeLocal;
import Ejbs.ConsultaFacadeLocal;
import Ejbs.DireccionFacadeLocal;
import Ejbs.DoctorFacadeLocal;
import Ejbs.ImagenesFacadeLocal;
import Ejbs.MedicamentoFacadeLocal;
import Ejbs.PacienteFacadeLocal;
import Helpers.SubirImagen;
import Models.Antescedentes;
import Models.Consulta;
import Models.Direccion;
import Models.Doctor;
import Models.Imagenes;
import Models.Medicamento;
import Models.Paciente;
import java.io.File;
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

    /*
    *Etiqueta ViewScoped, indica que este controlador podrá
    *ser visto/usado en la vista en la que se mande a llamar,
    * es decir, no guardará información durante toda la sesión.
    */
    
    @EJB
    private PacienteFacadeLocal pacienteEjb;
    private Paciente paciente;
    private List<Paciente> pacientes;
    private Medicamento medicamento;
    private Antescedentes antescedentes, antescedentesConsulta;
    private Imagenes imagen;
    private boolean uploaded;
    private Consulta consulta;
    
    @EJB
    private DireccionFacadeLocal direccionEjb;
    
    @EJB
    private MedicamentoFacadeLocal medicamentoEjb;
    
    @EJB
    private AntescedentesFacadeLocal antescedentesEjb;
    
    @EJB
    private ImagenesFacadeLocal imagenesEjb;
    
    @EJB
    private ConsultaFacadeLocal consultaEjb;
    
    @EJB
    private DoctorFacadeLocal doctorEjb;
    
    @PostConstruct
    public void init(){
        this.paciente = new Paciente();
        this.pacientes = new ArrayList<Paciente>();
        this.medicamento = new Medicamento();//instanciamos medicamento
        this.antescedentes = new Antescedentes();
        this.imagen = new Imagenes();
        uploaded = false;
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
    
    public void findForEditImagen(Object id){
        this.imagen = this.imagenesEjb.find(id);
        this.resetUploaded();
    }
    
    public void crearImagen() throws Exception{
        if (this.uploaded == false && this.imagen.getFile() != null) {
            SubirImagen si = new SubirImagen();
            String s = si.processUpload(this.imagen.getFile());
            this.uploaded = true;
            this.imagen.setUrl(s);
            this.imagen.setIdPaciente(paciente);
            this.paciente.getImagenesList().add(imagen);
            this.imagenesEjb.create(imagen);
            this.pacienteEjb.edit(paciente);
            this.imagen = null;
            this.imagen = new Imagenes();
            this.imagen.setNombre("");
            this.uploaded = true;
        }
    }
    
    public void editarImagen() throws Exception{
        if (this.uploaded == false && this.imagen.getFile() != null) {
            SubirImagen si = new SubirImagen();
            si.deleteFile(this.imagen.getUrl());
            String s = si.processUpload(this.imagen.getFile());
            this.imagen.setUrl(s);
            this.imagenesEjb.edit(imagen);
            this.pacienteEjb.edit(paciente);
        }else{
            this.imagenesEjb.edit(imagen);
            this.pacienteEjb.edit(paciente);
        }
    }
    
    public void resetUploaded(){
        this.uploaded = false;
    }
    
    public void findAntescedentesEdit(){
        this.antescedentes=this.paciente.getAntescedentesList().get(0);
    }
    
    public void editAntescedentes(){
        this.paciente.getAntescedentesList().set(0, antescedentes);
        this.pacienteEjb.edit(paciente);
        this.antescedentesConsulta = this.paciente.getAntescedentesList().get(0);
    }
    
    public void listar(){
        this.pacientes = this.pacienteEjb.findAll();
    }
    
    public void crear(){
        this.paciente.setRfc(this.paciente.getRfc().toUpperCase());
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
    
    public void crearCita(Doctor d){
        this.consulta.setIdDoctor(d);
        this.consulta.setIdPaciente(paciente);
        this.paciente.getConsultaList().add(consulta);
        d.getConsultaList().add(consulta);
        this.consultaEjb.create(consulta);
        this.pacienteEjb.edit(paciente);
        this.doctorEjb.edit(d);
        this.consulta=null;
        this.consulta=new Consulta();
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
    
    
    /**
     * @return the imagen
     */
    public Imagenes getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(Imagenes imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the consulta
     */
    public Consulta getConsulta() {
        return consulta;
    }

    /**
     * @param consulta the consulta to set
     */
    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }
            
    
}
