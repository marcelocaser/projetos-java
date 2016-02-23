package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "especies", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"tipo"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EspeciesTO.findAll", query = "SELECT e FROM EspeciesTO e")})
public class EspeciesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String tipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEspecie", fetch = FetchType.LAZY)
    private List<RacasTO> racasTOList;

    public EspeciesTO() {
    }

    public EspeciesTO(Integer id) {
        this.id = id;
    }

    public EspeciesTO(Integer id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @XmlTransient
    public List<RacasTO> getRacasTOList() {
        return racasTOList;
    }

    public void setRacasTOList(List<RacasTO> racasTOList) {
        this.racasTOList = racasTOList;
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
        if (!(object instanceof EspeciesTO)) {
            return false;
        }
        EspeciesTO other = (EspeciesTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.EspeciesTO[ id=" + id + " ]";
    }

}
