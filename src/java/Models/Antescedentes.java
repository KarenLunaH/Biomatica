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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author KarenLunaH
 */
@Entity
@Table(name = "Antescedentes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Antescedentes.findAll", query = "SELECT a FROM Antescedentes a")
    , @NamedQuery(name = "Antescedentes.findByIdAntescedente", query = "SELECT a FROM Antescedentes a WHERE a.idAntescedente = :idAntescedente")})
public class Antescedentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idAntescedente")
    private Integer idAntescedente;
    @Lob
    @Size(max = 65535)
    @Column(name = "heredoFam")
    private String heredoFam;
    @Lob
    @Size(max = 65535)
    @Column(name = "persoPatologicos")
    private String persoPatologicos;
    @Lob
    @Size(max = 65535)
    @Column(name = "persoNoPato")
    private String persoNoPato;
    @Lob
    @Size(max = 65535)
    @Column(name = "genecoObste")
    private String genecoObste;
    @JoinColumn(name = "idPaciente", referencedColumnName = "idPaciente")
    @ManyToOne(optional = false)
    private Paciente idPaciente;

    public Antescedentes() {
    }

    public Antescedentes(Integer idAntescedente) {
        this.idAntescedente = idAntescedente;
    }

    public Integer getIdAntescedente() {
        return idAntescedente;
    }

    public void setIdAntescedente(Integer idAntescedente) {
        this.idAntescedente = idAntescedente;
    }

    public String getHeredoFam() {
        return heredoFam;
    }

    public void setHeredoFam(String heredoFam) {
        this.heredoFam = heredoFam;
    }

    public String getPersoPatologicos() {
        return persoPatologicos;
    }

    public void setPersoPatologicos(String persoPatologicos) {
        this.persoPatologicos = persoPatologicos;
    }

    public String getPersoNoPato() {
        return persoNoPato;
    }

    public void setPersoNoPato(String persoNoPato) {
        this.persoNoPato = persoNoPato;
    }

    public String getGenecoObste() {
        return genecoObste;
    }

    public void setGenecoObste(String genecoObste) {
        this.genecoObste = genecoObste;
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
        hash += (idAntescedente != null ? idAntescedente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Antescedentes)) {
            return false;
        }
        Antescedentes other = (Antescedentes) object;
        if ((this.idAntescedente == null && other.idAntescedente != null) || (this.idAntescedente != null && !this.idAntescedente.equals(other.idAntescedente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Antescedentes[ idAntescedente=" + idAntescedente + " ]";
    }
    
}
