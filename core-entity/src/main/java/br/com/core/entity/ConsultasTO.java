package br.com.core.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
 * @author marce
 */
@Entity
@Table(name = "consultas", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsultasTO.findAll", query = "SELECT c FROM ConsultasTO c")})
public class ConsultasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private boolean status;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character siteCorreios;
    @JoinColumn(name = "idUsuarios", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UsuariosTO idUsuarios;

    public ConsultasTO() {
    }

    public ConsultasTO(Integer id) {
        this.id = id;
    }

    public ConsultasTO(Integer id, Date data, Date hora, String cep, boolean status, Character siteCorreios) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.cep = cep;
        this.status = status;
        this.siteCorreios = siteCorreios;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Character getSiteCorreios() {
        return siteCorreios;
    }

    public void setSiteCorreios(Character siteCorreios) {
        this.siteCorreios = siteCorreios;
    }

    public UsuariosTO getIdUsuarios() {
        return idUsuarios;
    }

    public void setIdUsuarios(UsuariosTO idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConsultasTO)) {
            return false;
        }
        ConsultasTO other = (ConsultasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.ConsultasTO[ id=" + id + " ]";
    }

}
