package br.com.core.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "servicos_categorias", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nome"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicosCategoriasTO.findAll", query = "SELECT s FROM ServicosCategoriasTO s")})
public class ServicosCategoriasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String nome;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @ManyToMany(mappedBy = "servicosCategoriasTOList", fetch = FetchType.LAZY)
    private List<ServicosTO> servicosTOList;
    @JoinColumn(name = "idTabelaPreco", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TabelasPrecosTO idTabelaPreco;
    @OneToMany(mappedBy = "idServicoCategoria", fetch = FetchType.LAZY)
    private List<AgendasTO> agendasTOList;

    public ServicosCategoriasTO() {
    }

    public ServicosCategoriasTO(Integer id) {
        this.id = id;
    }

    public ServicosCategoriasTO(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public TabelasPrecosTO getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(TabelasPrecosTO idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
    }

    @XmlTransient
    public List<AgendasTO> getAgendasTOList() {
        return agendasTOList;
    }

    public void setAgendasTOList(List<AgendasTO> agendasTOList) {
        this.agendasTOList = agendasTOList;
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
        if (!(object instanceof ServicosCategoriasTO)) {
            return false;
        }
        ServicosCategoriasTO other = (ServicosCategoriasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.ServicosCategoriasTO[ id=" + id + " ]";
    }

}
