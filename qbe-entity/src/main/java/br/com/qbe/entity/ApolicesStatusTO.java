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
@Table(name = "apolices_status", catalog = "qbe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApolicesStatusTO.findAll", query = "SELECT a FROM ApolicesStatusTO a")})
public class ApolicesStatusTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int status;
    @Size(max = 60)
    @Column(length = 60)
    private String texto;
    @Temporal(TemporalType.DATE)
    private Date alteracao;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date criacao;
    @Size(max = 45)
    @Column(length = 45)
    private String exclusao;
    @JoinColumn(name = "apolice", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ApolicesTO apolice;

    public ApolicesStatusTO() {
    }

    public ApolicesStatusTO(Integer id) {
        this.id = id;
    }

    public ApolicesStatusTO(Integer id, int status, Date criacao) {
        this.id = id;
        this.status = status;
        this.criacao = criacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
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

    public String getExclusao() {
        return exclusao;
    }

    public void setExclusao(String exclusao) {
        this.exclusao = exclusao;
    }

    public ApolicesTO getApolice() {
        return apolice;
    }

    public void setApolice(ApolicesTO apolice) {
        this.apolice = apolice;
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
        if (!(object instanceof ApolicesStatusTO)) {
            return false;
        }
        ApolicesStatusTO other = (ApolicesStatusTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.ApolicesStatusTO[ id=" + id + " ]";
    }
    
}
