package br.com.qbe.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "tipos_coberturas", catalog = "qbe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposCoberturasTO.findAll", query = "SELECT t FROM TiposCoberturasTO t")})
public class TiposCoberturasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCobertura", fetch = FetchType.LAZY)
    private List<CoberturasPremiosTO> coberturasPremiosTOList;

    public TiposCoberturasTO() {
    }

    public TiposCoberturasTO(Integer id) {
        this.id = id;
    }

    public TiposCoberturasTO(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<CoberturasPremiosTO> getCoberturasPremiosTOList() {
        return coberturasPremiosTOList;
    }

    public void setCoberturasPremiosTOList(List<CoberturasPremiosTO> coberturasPremiosTOList) {
        this.coberturasPremiosTOList = coberturasPremiosTOList;
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
        if (!(object instanceof TiposCoberturasTO)) {
            return false;
        }
        TiposCoberturasTO other = (TiposCoberturasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.TiposCoberturasTO[ id=" + id + " ]";
    }
    
}
