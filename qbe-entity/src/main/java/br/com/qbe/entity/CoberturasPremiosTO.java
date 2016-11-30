package br.com.qbe.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "coberturas_premios", catalog = "qbe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoberturasPremiosTO.findAll", query = "SELECT c FROM CoberturasPremiosTO c")})
@JsonIgnoreProperties(value = {"idApolice"})
public class CoberturasPremiosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Integer idApolice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 10, scale = 2)
    @JsonProperty(value = "premio")
    private BigDecimal valorPremio;
    @JoinColumn(name = "idApolice", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private ApolicesTO apolicesTO;
    @JoinColumn(name = "idCobertura", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonProperty(value = "codigoCobertura")
    private TiposCoberturasTO idCobertura;

    public CoberturasPremiosTO() {
    }

    public CoberturasPremiosTO(Integer idApolice) {
        this.idApolice = idApolice;
    }

    public CoberturasPremiosTO(Integer idApolice, BigDecimal valorPremio) {
        this.idApolice = idApolice;
        this.valorPremio = valorPremio;
    }

    public Integer getIdApolice() {
        return idApolice;
    }

    public void setIdApolice(Integer idApolice) {
        this.idApolice = idApolice;
    }

    public BigDecimal getValorPremio() {
        return valorPremio;
    }

    public void setValorPremio(BigDecimal valorPremio) {
        this.valorPremio = valorPremio;
    }

    public ApolicesTO getApolicesTO() {
        return apolicesTO;
    }

    public void setApolicesTO(ApolicesTO apolicesTO) {
        this.apolicesTO = apolicesTO;
    }

    public TiposCoberturasTO getIdCobertura() {
        return idCobertura;
    }

    public void setIdCobertura(TiposCoberturasTO idCobertura) {
        this.idCobertura = idCobertura;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idApolice != null ? idApolice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoberturasPremiosTO)) {
            return false;
        }
        CoberturasPremiosTO other = (CoberturasPremiosTO) object;
        if ((this.idApolice == null && other.idApolice != null) || (this.idApolice != null && !this.idApolice.equals(other.idApolice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.CoberturasPremiosTO[ idApolice=" + idApolice + " ]";
    }

}
