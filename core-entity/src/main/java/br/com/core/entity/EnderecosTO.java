package br.com.core.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "enderecos", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EnderecosTO.findAll", query = "SELECT e FROM EnderecosTO e")})
public class EnderecosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(nullable = false, length = 300)
    private String logracompl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(nullable = false, length = 300)
    private String logradouro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(nullable = false, length = 300)
    private String nomeclog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(nullable = false, length = 300)
    private String nomeslog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String uf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uf_cod", nullable = false)
    private Integer ufCod;
    @JoinTable(name = "aux_pessoas_enderecos", joinColumns = {
        @JoinColumn(name = "idEndereco", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "idPessoa", referencedColumnName = "id", nullable = false)})
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PessoasTO> pessoasTOList;
    @JoinColumn(name = "idBairro", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private BairrosTO idBairro;
    @JoinColumn(name = "idCidade", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private CidadesTO idCidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEndereco", fetch = FetchType.LAZY)
    private List<EnderecosComplementosTO> enderecoComplementosTOList;

    public EnderecosTO() {
    }

    public EnderecosTO(Integer id) {
        this.id = id;
    }

    public EnderecosTO(Integer id, String cep, String logracompl, String logradouro, String nomeclog, String nomeslog, String uf, int ufCod) {
        this.id = id;
        this.cep = cep;
        this.logracompl = logracompl;
        this.logradouro = logradouro;
        this.nomeclog = nomeclog;
        this.nomeslog = nomeslog;
        this.uf = uf;
        this.ufCod = ufCod;
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

    public String getLogracompl() {
        return logracompl;
    }

    public void setLogracompl(String logracompl) {
        this.logracompl = logracompl;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNomeclog() {
        return nomeclog;
    }

    public void setNomeclog(String nomeclog) {
        this.nomeclog = nomeclog;
    }

    public String getNomeslog() {
        return nomeslog;
    }

    public void setNomeslog(String nomeslog) {
        this.nomeslog = nomeslog;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getUfCod() {
        return ufCod;
    }

    public void setUfCod(int ufCod) {
        this.ufCod = ufCod;
    }

    @XmlTransient
    public List<PessoasTO> getPessoasTOList() {
        return pessoasTOList;
    }

    public void setPessoasTOList(List<PessoasTO> pessoasTOList) {
        this.pessoasTOList = pessoasTOList;
    }

    public BairrosTO getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(BairrosTO idBairro) {
        this.idBairro = idBairro;
    }

    public CidadesTO getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(CidadesTO idCidade) {
        this.idCidade = idCidade;
    }

    @XmlTransient
    public List<EnderecosComplementosTO> getEnderecoComplementosTOList() {
        return enderecoComplementosTOList;
    }

    public void setEnderecoComplementosTOList(List<EnderecosComplementosTO> enderecoComplementosTOList) {
        this.enderecoComplementosTOList = enderecoComplementosTOList;
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
        if (!(object instanceof EnderecosTO)) {
            return false;
        }
        EnderecosTO other = (EnderecosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.EnderecosTO[ id=" + id + " ]";
    }

}
