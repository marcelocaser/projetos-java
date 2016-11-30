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
@Table(name = "tipos_assistencias", catalog = "qbe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposAssistenciasTO.findAll", query = "SELECT t FROM TiposAssistenciasTO t")})
public class TiposAssistenciasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int codigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAssistencias", fetch = FetchType.LAZY)
    private List<AssistenciasPremiosTO> assistenciasPremiosTOList;

    public TiposAssistenciasTO() {
    }

    public TiposAssistenciasTO(Integer id) {
        this.id = id;
    }

    public TiposAssistenciasTO(Integer id, int codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<AssistenciasPremiosTO> getAssistenciasPremiosTOList() {
        return assistenciasPremiosTOList;
    }

    public void setAssistenciasPremiosTOList(List<AssistenciasPremiosTO> assistenciasPremiosTOList) {
        this.assistenciasPremiosTOList = assistenciasPremiosTOList;
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
        if (!(object instanceof TiposAssistenciasTO)) {
            return false;
        }
        TiposAssistenciasTO other = (TiposAssistenciasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.TiposAssistenciasTO[ id=" + id + " ]";
    }
    
}
