/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KarenLunaH
 */
@Entity
@Table(name = "Consulta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Consulta.findAll", query = "SELECT c FROM Consulta c")
    , @NamedQuery(name = "Consulta.findByIdConsulta", query = "SELECT c FROM Consulta c WHERE c.idConsulta = :idConsulta")
    , @NamedQuery(name = "Consulta.findByEdad", query = "SELECT c FROM Consulta c WHERE c.edad = :edad")
    , @NamedQuery(name = "Consulta.findByTalla", query = "SELECT c FROM Consulta c WHERE c.talla = :talla")
    , @NamedQuery(name = "Consulta.findByPeso", query = "SELECT c FROM Consulta c WHERE c.peso = :peso")
    , @NamedQuery(name = "Consulta.findByTemperatura", query = "SELECT c FROM Consulta c WHERE c.temperatura = :temperatura")
    , @NamedQuery(name = "Consulta.findByTensionArterial", query = "SELECT c FROM Consulta c WHERE c.tensionArterial = :tensionArterial")
    , @NamedQuery(name = "Consulta.findByFrecuenciaCardiaca", query = "SELECT c FROM Consulta c WHERE c.frecuenciaCardiaca = :frecuenciaCardiaca")
    , @NamedQuery(name = "Consulta.findByFrecuenciaRespiratorio", query = "SELECT c FROM Consulta c WHERE c.frecuenciaRespiratorio = :frecuenciaRespiratorio")
    , @NamedQuery(name = "Consulta.findByFechaHoraInicio", query = "SELECT c FROM Consulta c WHERE c.fechaHoraInicio = :fechaHoraInicio")
    , @NamedQuery(name = "Consulta.findByFechaHoraFin", query = "SELECT c FROM Consulta c WHERE c.fechaHoraFin = :fechaHoraFin")})
public class Consulta implements Serializable {

    /**
     * @return the registrado
     */
    public boolean isRegistrado() {
        return registrado;
    }

    /**
     * @param registrado the registrado to set
     */
    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConsulta")
    private Integer idConsulta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "edad")
    private int edad;
    @Column(name = "talla")
    private Long talla;
    @Column(name = "peso")
    private Long peso;
    @Column(name = "temperatura")
    private Long temperatura;
    @Column(name = "tensionArterial")
    private Long tensionArterial;
    @Column(name = "frecuenciaCardiaca")
    private Integer frecuenciaCardiaca;
    @Column(name = "frecuenciaRespiratorio")
    private Integer frecuenciaRespiratorio;
    @Lob
    @Size(max = 65535)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Lob
    @Size(max = 65535)
    @Column(name = "enfermedad")
    private String enfermedad;
    @Lob
    @Size(max = 65535)
    @Column(name = "pronostico")
    private String pronostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaHoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaHoraFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHoraFin;
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    @ManyToOne(optional = false)
    private Paciente idPaciente;
    @JoinColumn(name = "idDoctor", referencedColumnName = "idDoctor")
    @ManyToOne(optional = false)
    private Doctor idDoctor;
    @Column(name = "registrado")
    private boolean registrado;

    public Consulta() {
        this.fechaHoraInicio  = new Date();
        this.fechaHoraFin = new Date();
        this.idPaciente = new Paciente();
        this.idDoctor = new Doctor();
    }

    public Consulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Consulta(Integer idConsulta, int edad, Date fechaHoraInicio, Date fechaHoraFin) {
        this.idConsulta = idConsulta;
        this.edad = edad;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Long getTalla() {
        return talla;
    }

    public void setTalla(Long talla) {
        this.talla = talla;
    }

    public Long getPeso() {
        return peso;
    }

    public void setPeso(Long peso) {
        this.peso = peso;
    }

    public Long getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Long temperatura) {
        this.temperatura = temperatura;
    }

    public Long getTensionArterial() {
        return tensionArterial;
    }

    public void setTensionArterial(Long tensionArterial) {
        this.tensionArterial = tensionArterial;
    }

    public Integer getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(Integer frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public Integer getFrecuenciaRespiratorio() {
        return frecuenciaRespiratorio;
    }

    public void setFrecuenciaRespiratorio(Integer frecuenciaRespiratorio) {
        this.frecuenciaRespiratorio = frecuenciaRespiratorio;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getPronostico() {
        return pronostico;
    }

    public void setPronostico(String pronostico) {
        this.pronostico = pronostico;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Doctor getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Doctor idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConsulta != null ? idConsulta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consulta)) {
            return false;
        }
        Consulta other = (Consulta) object;
        if ((this.idConsulta == null && other.idConsulta != null) || (this.idConsulta != null && !this.idConsulta.equals(other.idConsulta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Consulta[ idConsulta=" + idConsulta + " ]";
    }
    
}
