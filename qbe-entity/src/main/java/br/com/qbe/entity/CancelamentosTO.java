package br.com.qbe.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "cancelamentos", catalog = "qbe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CancelamentosTO.findAll", query = "SELECT c FROM CancelamentosTO c")})
public class CancelamentosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String tipoArquivo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int numeroBilhete;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 14)
    @Column(nullable = false, length = 14)
    private String cnpjCpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(nullable = false, length = 10)
    private String dataCancelamento;
    @Temporal(TemporalType.DATE)
    private Date alteracao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date criacao;
    @Temporal(TemporalType.DATE)
    private Date exclusao;
    @JoinColumn(name = "idTipoCancelamento", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposCancelamentosTO idTipoCancelamento;

    public CancelamentosTO() {
    }

    public CancelamentosTO(Integer id) {
        this.id = id;
    }

    public CancelamentosTO(Integer id, String tipoArquivo, int numeroBilhete, String cnpjCpf, String dataCancelamento, Date criacao) {
        this.id = id;
        this.tipoArquivo = tipoArquivo;
        this.numeroBilhete = numeroBilhete;
        this.cnpjCpf = cnpjCpf;
        this.dataCancelamento = dataCancelamento;
        this.criacao = criacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public int getNumeroBilhete() {
        return numeroBilhete;
    }

    public void setNumeroBilhete(int numeroBilhete) {
        this.numeroBilhete = numeroBilhete;
    }

    public String getCnpjCpf() {
        return cnpjCpf;
    }

    public void setCnpjCpf(String cnpjCpf) {
        this.cnpjCpf = cnpjCpf;
    }

    public String getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(String dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

    public Date getAlteracao() {
        return alteracao;
    }

    public void setAlteracao(Date alteracao) {
        this.alteracao = alteracao;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public Date getExclusao() {
        return exclusao;
    }

    public void setExclusao(Date exclusao) {
        this.exclusao = exclusao;
    }

    public TiposCancelamentosTO getIdTipoCancelamento() {
        return idTipoCancelamento;
    }

    public void setIdTipoCancelamento(TiposCancelamentosTO idTipoCancelamento) {
        this.idTipoCancelamento = idTipoCancelamento;
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
        if (!(object instanceof CancelamentosTO)) {
            return false;
        }
        CancelamentosTO other = (CancelamentosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.CancelamentosTO[ id=" + id + " ]";
    }
    
}
