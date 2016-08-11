package br.com.core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "sistemas_configuracoes", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemasConfiguracoesTO.findAll", query = "SELECT s FROM SistemasConfiguracoesTO s")})
public class SistemasConfiguracoesTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    private Integer maximoClientesPainel;
    private Integer maximoAgendamentosPainel;
    private Integer maximoAgendamentosMesmaHora;
    private Boolean validaHorario;

    public SistemasConfiguracoesTO() {
    }

    public SistemasConfiguracoesTO(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaximoClientesPainel() {
        return maximoClientesPainel;
    }

    public void setMaximoClientesPainel(Integer maximoClientesPainel) {
        this.maximoClientesPainel = maximoClientesPainel;
    }

    public Integer getMaximoAgendamentosPainel() {
        return maximoAgendamentosPainel;
    }

    public void setMaximoAgendamentosPainel(Integer maximoAgendamentosPainel) {
        this.maximoAgendamentosPainel = maximoAgendamentosPainel;
    }

    public Integer getMaximoAgendamentosMesmaHora() {
        return maximoAgendamentosMesmaHora;
    }

    public void setMaximoAgendamentosMesmaHora(Integer maximoAgendamentosMesmaHora) {
        this.maximoAgendamentosMesmaHora = maximoAgendamentosMesmaHora;
    }

    public Boolean getValidaHorario() {
        return validaHorario;
    }

    public void setValidaHorario(Boolean validaHorario) {
        this.validaHorario = validaHorario;
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
        if (!(object instanceof SistemasConfiguracoesTO)) {
            return false;
        }
        SistemasConfiguracoesTO other = (SistemasConfiguracoesTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.SistemasConfiguracoesTO[ id=" + id + " ]";
    }

}
