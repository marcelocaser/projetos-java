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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "clientes", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientesTO.findAll", query = "SELECT c FROM ClientesTO c")})
public class ClientesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character status;
    @Size(max = 15)
    @Column(length = 15)
    private String bonificacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date clienteDesde;
    @Size(max = 150)
    @Column(length = 150)
    private String observacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<AgendasTO> agendasTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<DependentesTO> dependentesTOList;
    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<AnimaisTO> animaisTOList;
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private PessoasTO pessoasTO;

    public ClientesTO() {
    }

    public ClientesTO(Integer id) {
        this.id = id;
    }

    public ClientesTO(Integer id, Character status) {
        this.id = id;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getBonificacao() {
        return bonificacao;
    }

    public void setBonificacao(String bonificacao) {
        this.bonificacao = bonificacao;
    }

    public Date getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(Date clienteDesde) {
        this.clienteDesde = clienteDesde;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
    public List<AgendasTO> getAgendasTOList() {
        return agendasTOList;
    }

    public void setAgendasTOList(List<AgendasTO> agendasTOList) {
        this.agendasTOList = agendasTOList;
    }

    @XmlTransient
    public List<DependentesTO> getDependentesTOList() {
        return dependentesTOList;
    }

    public void setDependentesTOList(List<DependentesTO> dependentesTOList) {
        this.dependentesTOList = dependentesTOList;
    }

    @XmlTransient
    public List<AnimaisTO> getAnimaisTOList() {
        return animaisTOList;
    }

    public void setAnimaisTOList(List<AnimaisTO> animaisTOList) {
        this.animaisTOList = animaisTOList;
    }

    public PessoasTO getPessoasTO() {
        return pessoasTO;
    }

    public void setPessoasTO(PessoasTO pessoasTO) {
        this.pessoasTO = pessoasTO;
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
        if (!(object instanceof ClientesTO)) {
            return false;
        }
        ClientesTO other = (ClientesTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.ClientesTO[ id=" + id + " ]";
    }

}
