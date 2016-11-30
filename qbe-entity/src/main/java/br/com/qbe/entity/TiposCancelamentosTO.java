package br.com.qbe.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "tipos_cancelamentos", catalog = "qbe", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposCancelamentosTO.findAll", query = "SELECT t FROM TiposCancelamentosTO t")})
public class TiposCancelamentosTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int tipo;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int motivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoCancelamento", fetch = FetchType.LAZY)
    private List<CancelamentosTO> cancelamentosTOList;

    public TiposCancelamentosTO() {
    }

    public TiposCancelamentosTO(Integer id) {
        this.id = id;
    }

    public TiposCancelamentosTO(Integer id, int tipo, int motivo, String descricao) {
        this.id = id;
        this.tipo = tipo;
        this.motivo = motivo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMotivo() {
        return motivo;
    }

    public void setMotivo(int motivo) {
        this.motivo = motivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @XmlTransient
    public List<CancelamentosTO> getCancelamentosTOList() {
        return cancelamentosTOList;
    }

    public void setCancelamentosTOList(List<CancelamentosTO> cancelamentosTOList) {
        this.cancelamentosTOList = cancelamentosTOList;
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
        if (!(object instanceof TiposCancelamentosTO)) {
            return false;
        }
        TiposCancelamentosTO other = (TiposCancelamentosTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.qbe.entity.TiposCancelamentosTO[ id=" + id + " ]";
    }
    
}
