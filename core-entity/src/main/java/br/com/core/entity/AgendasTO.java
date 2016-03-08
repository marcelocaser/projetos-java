package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "agendas", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AgendasTO.findAll", query = "SELECT a FROM AgendasTO a")})
public class AgendasTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    private Character horarioAConfirmar;
    private Character teleBusca;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String statusDescricao;
    @Size(max = 150)
    @Column(length = 150)
    private String observacao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal valor;
    @Column(precision = 5, scale = 2)
    private BigDecimal valorACobrar;
    private Integer iraRepetirEm;
    private Integer idFuncionario;
    private Integer idCategoria;
    private Integer idMotorista;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @JoinColumn(name = "idAnimal", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private AnimaisTO idAnimal;
    @JoinColumn(name = "idCliente", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClientesTO idCliente;
    @JoinColumn(name = "idServico", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ServicosTO idServico;

    public AgendasTO() {
    }

    public AgendasTO(Integer id) {
        this.id = id;
    }

    public AgendasTO(Integer id, Date data, Date hora, String statusDescricao, BigDecimal valor) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.statusDescricao = statusDescricao;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Character getHorarioAConfirmar() {
        return horarioAConfirmar;
    }

    public void setHorarioAConfirmar(Character horarioAConfirmar) {
        this.horarioAConfirmar = horarioAConfirmar;
    }

    public Character getTeleBusca() {
        return teleBusca;
    }

    public void setTeleBusca(Character teleBusca) {
        this.teleBusca = teleBusca;
    }

    public String getStatusDescricao() {
        return statusDescricao;
    }

    public void setStatusDescricao(String statusDescricao) {
        this.statusDescricao = statusDescricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorACobrar() {
        return valorACobrar;
    }

    public void setValorACobrar(BigDecimal valorACobrar) {
        this.valorACobrar = valorACobrar;
    }

    public Integer getIraRepetirEm() {
        return iraRepetirEm;
    }

    public void setIraRepetirEm(Integer iraRepetirEm) {
        this.iraRepetirEm = iraRepetirEm;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdMotorista() {
        return idMotorista;
    }

    public void setIdMotorista(Integer idMotorista) {
        this.idMotorista = idMotorista;
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

    public AnimaisTO getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(AnimaisTO idAnimal) {
        this.idAnimal = idAnimal;
    }

    public ClientesTO getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClientesTO idCliente) {
        this.idCliente = idCliente;
    }

    public ServicosTO getIdServico() {
        return idServico;
    }

    public void setIdServico(ServicosTO idServico) {
        this.idServico = idServico;
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
        if (!(object instanceof AgendasTO)) {
            return false;
        }
        AgendasTO other = (AgendasTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.AgendasTO[ id=" + id + " ]";
    }

}
