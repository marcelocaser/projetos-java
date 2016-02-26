package br.com.core.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "servicos_grupos", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicosGruposTO.findAll", query = "SELECT s FROM ServicosGruposTO s")})
public class ServicosGruposTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 150)
    @Column(length = 150)
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idGrupo", fetch = FetchType.LAZY)
    private List<ServicosTO> servicosTOList;

    public ServicosGruposTO() {
    }

    public ServicosGruposTO(Integer id) {
        this.id = id;
    }

    public ServicosGruposTO(Integer id, Character status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public Date getExclusao() {
        return exclusao;
    }

    public void setExclusao(Date exclusao) {
        this.exclusao = exclusao;
    }

    @XmlTransient
    public List<ServicosTO> getServicosTOList() {
        return servicosTOList;
    }

    public void setServicosTOList(List<ServicosTO> servicosTOList) {
        this.servicosTOList = servicosTOList;
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
        if (!(object instanceof ServicosGruposTO)) {
            return false;
        }
        ServicosGruposTO other = (ServicosGruposTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.ServicosGruposTO[ id=" + id + " ]";
    }

}
