package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "tabelas_precos", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TabelasPrecosTO.findAll", query = "SELECT t FROM TabelasPrecosTO t")})
public class TabelasPrecosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 5, scale = 2)
    private BigDecimal margemLucro;
    @Column(precision = 5, scale = 2)
    private BigDecimal precoCusto;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal precoVenda;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @OneToMany(mappedBy = "idTabelaPreco", fetch = FetchType.LAZY)
    private List<ServicosTO> servicosTOList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTabelaPreco", fetch = FetchType.LAZY)
    private List<ServicosCategoriasTO> servicosCategoriasTOList;

    public TabelasPrecosTO() {
    }

    public TabelasPrecosTO(Integer id) {
        this.id = id;
    }

    public TabelasPrecosTO(Integer id, BigDecimal precoVenda) {
        this.id = id;
        this.precoVenda = precoVenda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(BigDecimal margemLucro) {
        this.margemLucro = margemLucro;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
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

    @XmlTransient
    public List<ServicosCategoriasTO> getServicosCategoriasTOList() {
        return servicosCategoriasTOList;
    }

    public void setServicosCategoriasTOList(List<ServicosCategoriasTO> servicosCategoriasTOList) {
        this.servicosCategoriasTOList = servicosCategoriasTOList;
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
        if (!(object instanceof TabelasPrecosTO)) {
            return false;
        }
        TabelasPrecosTO other = (TabelasPrecosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.TabelasPrecosTO[ id=" + id + " ]";
    }

}
