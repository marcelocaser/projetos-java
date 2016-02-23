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
@Table(name = "cidades", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CidadesTO.findAll", query = "SELECT c FROM CidadesTO c")})
public class CidadesTO implements Serializable {

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
    @Size(min = 1, max = 11)
    @Column(name = "codigo_ibge", nullable = false, length = 11)
    private String codigoIbge;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(nullable = false, length = 200)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(nullable = false, length = 2)
    private String uf;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCidade", fetch = FetchType.LAZY)
    private List<BairrosTO> bairrosTOList;
    @JoinColumn(name = "idEstado", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosTO idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCidade", fetch = FetchType.LAZY)
    private List<EnderecosTO> enderecosTOList;

    public CidadesTO() {
    }

    public CidadesTO(Integer id) {
        this.id = id;
    }

    public CidadesTO(Integer id, String cep, String codigoIbge, String nome, String uf) {
        this.id = id;
        this.cep = cep;
        this.codigoIbge = codigoIbge;
        this.nome = nome;
        this.uf = uf;
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

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
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
    public List<BairrosTO> getBairrosTOList() {
        return bairrosTOList;
    }

    public void setBairrosTOList(List<BairrosTO> bairrosTOList) {
        this.bairrosTOList = bairrosTOList;
    }

    public EstadosTO getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(EstadosTO idEstado) {
        this.idEstado = idEstado;
    }

    @XmlTransient
    public List<EnderecosTO> getEnderecosTOList() {
        return enderecosTOList;
    }

    public void setEnderecosTOList(List<EnderecosTO> enderecosTOList) {
        this.enderecosTOList = enderecosTOList;
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
        if (!(object instanceof CidadesTO)) {
            return false;
        }
        CidadesTO other = (CidadesTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.CidadesTO[ id=" + id + " ]";
    }

}
