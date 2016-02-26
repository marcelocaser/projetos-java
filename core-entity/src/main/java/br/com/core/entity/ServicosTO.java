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
@Table(name = "servicos", catalog = "bdg", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nome"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicosTO.findAll", query = "SELECT s FROM ServicosTO s")})
public class ServicosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Size(max = 60)
    @Column(length = 60)
    private String nome;
    @Size(max = 150)
    @Column(length = 150)
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character cobrancaDiaria;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character servicoClienteSemAnimal;
    private Integer remarcarConsultarEmDias;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @ManyToMany(mappedBy = "servicosTOList", fetch = FetchType.LAZY)
    private List<ServicosCategoriasTO> servicosCategoriasTOList;
    @JoinColumn(name = "idGrupo", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ServicosGruposTO idGrupo;
    @JoinColumn(name = "idTabelaPreco", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TabelasPrecosTO idTabelaPreco;

    public ServicosTO() {
    }

    public ServicosTO(Integer id) {
        this.id = id;
    }

    public ServicosTO(Integer id, Character cobrancaDiaria, Character servicoClienteSemAnimal) {
        this.id = id;
        this.cobrancaDiaria = cobrancaDiaria;
        this.servicoClienteSemAnimal = servicoClienteSemAnimal;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Character getCobrancaDiaria() {
        return cobrancaDiaria;
    }

    public void setCobrancaDiaria(Character cobrancaDiaria) {
        this.cobrancaDiaria = cobrancaDiaria;
    }

    public Character getServicoClienteSemAnimal() {
        return servicoClienteSemAnimal;
    }

    public void setServicoClienteSemAnimal(Character servicoClienteSemAnimal) {
        this.servicoClienteSemAnimal = servicoClienteSemAnimal;
    }

    public Integer getRemarcarConsultarEmDias() {
        return remarcarConsultarEmDias;
    }

    public void setRemarcarConsultarEmDias(Integer remarcarConsultarEmDias) {
        this.remarcarConsultarEmDias = remarcarConsultarEmDias;
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
    public List<ServicosCategoriasTO> getServicosCategoriasTOList() {
        return servicosCategoriasTOList;
    }

    public void setServicosCategoriasTOList(List<ServicosCategoriasTO> servicosCategoriasTOList) {
        this.servicosCategoriasTOList = servicosCategoriasTOList;
    }

    public ServicosGruposTO getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(ServicosGruposTO idGrupo) {
        this.idGrupo = idGrupo;
    }

    public TabelasPrecosTO getIdTabelaPreco() {
        return idTabelaPreco;
    }

    public void setIdTabelaPreco(TabelasPrecosTO idTabelaPreco) {
        this.idTabelaPreco = idTabelaPreco;
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
        if (!(object instanceof ServicosTO)) {
            return false;
        }
        ServicosTO other = (ServicosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.ServicosTO[ id=" + id + " ]";
    }

}
