package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "estados", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosTO.findAll", query = "SELECT e FROM EstadosTO e")})
public class EstadosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer ibge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(nullable = false, length = 150)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String uf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<CidadesTO> cidadesTOList;

    public EstadosTO() {
    }

    public EstadosTO(Integer id) {
        this.id = id;
    }

    public EstadosTO(Integer id, Integer ibge, String nome, String uf) {
        this.id = id;
        this.ibge = ibge;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @XmlTransient
    public List<CidadesTO> getCidadesTOList() {
        return cidadesTOList;
    }

    public void setCidadesTOList(List<CidadesTO> cidadesTOList) {
        this.cidadesTOList = cidadesTOList;
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
        if (!(object instanceof EstadosTO)) {
            return false;
        }
        EstadosTO other = (EstadosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.EstadosTO[ id=" + id + " ]";
    }

}
