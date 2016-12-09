/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KarenLunaH
 */
@Entity
@Table(name = "Medicamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m")
    , @NamedQuery(name = "Medicamento.findByIdMedicamento", query = "SELECT m FROM Medicamento m WHERE m.idMedicamento = :idMedicamento")})
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMedicamento")
    private Integer idMedicamento;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "nombreComercial")
    private String nombreComercial;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "descripcion")
    private String descripcion;
    @Lob
    @Size(max = 65535)
    @Column(name = "presentacion")
    private String presentacion;
    @Lob
    @Size(max = 65535)
    @Column(name = "nombreGenerico")
    private String nombreGenerico;
    @Lob
    @Size(max = 65535)
    @Column(name = "contraIndicaciones")
    private String contraIndicaciones;
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    @ManyToOne(optional = false)
    private Paciente idPaciente;

    public Medicamento() {
    }

    public Medicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public Medicamento(Integer idMedicamento, String nombreComercial, String marca, String descripcion) {
        this.idMedicamento = idMedicamento;
        this.nombreComercial = nombreComercial;
        this.marca = marca;
        this.descripcion = descripcion;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getNombreGenerico() {
        return nombreGenerico;
    }

    public void setNombreGenerico(String nombreGenerico) {
        this.nombreGenerico = nombreGenerico;
    }

    public String getContraIndicaciones() {
        return contraIndicaciones;
    }

    public void setContraIndicaciones(String contraIndicaciones) {
        this.contraIndicaciones = contraIndicaciones;
    }

    public Paciente getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Paciente idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMedicamento != null ? idMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.idMedicamento == null && other.idMedicamento != null) || (this.idMedicamento != null && !this.idMedicamento.equals(other.idMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Medicamento[ idMedicamento=" + idMedicamento + " ]";
    }
    
}
