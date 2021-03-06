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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "enderecos_complementos", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnderecosComplementosTO.findAll", query = "SELECT e FROM EnderecosComplementosTO e")})
public class EnderecosComplementosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(nullable = false, length = 150)
    private String complemento;
    @Size(max = 5)
    @Column(length = 5)
    private String numero;
    @Size(max = 15)
    @Column(length = 15)
    private String tipo;
    @Size(max = 150)
    @Column(length = 150)
    private String observacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 22)
    private Double latitude;
    @Column(precision = 22)
    private Double longitude;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @JoinTable(name = "aux_pessoas_enderecos_complementos", joinColumns = {
        @JoinColumn(name = "idEnderecoComplemento", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idPessoa", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<PessoasTO> pessoasTOList;
    @JoinColumn(name = "idEndereco", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EnderecosTO idEndereco;

    public EnderecosComplementosTO() {
    }

    public EnderecosComplementosTO(Integer id) {
        this.id = id;
    }

    public EnderecosComplementosTO(Integer id, String cep, String complemento) {
        this.id = id;
        this.cep = cep;
        this.complemento = complemento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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
    public List<PessoasTO> getPessoasTOList() {
        return pessoasTOList;
    }

    public void setPessoasTOList(List<PessoasTO> pessoasTOList) {
        this.pessoasTOList = pessoasTOList;
    }

    public EnderecosTO getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(EnderecosTO idEndereco) {
        this.idEndereco = idEndereco;
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
        if (!(object instanceof EnderecosComplementosTO)) {
            return false;
        }
        EnderecosComplementosTO other = (EnderecosComplementosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.EnderecosComplementosTO[ id=" + id + " ]";
    }

}
