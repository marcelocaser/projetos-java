package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "animais", catalog = "bdg", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AnimaisTO.findAll", query = "SELECT a FROM AnimaisTO a")})
public class AnimaisTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(nullable = false, length = 60)
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private Character sexo;
    @Temporal(TemporalType.TIMESTAMP)
    private Date nascimento;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 5, scale = 2)
    private BigDecimal peso;
    @Size(max = 45)
    @Column(length = 45)
    private String chip;
    @Size(max = 45)
    @Column(length = 45)
    private String pelagem;
    private Character aVenda;
    private Character castrado;
    private Character obito;
    private Character controleDeRacao;
    @Size(max = 150)
    @Column(length = 150)
    private String observacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaVisita;
    @Lob
    private byte[] foto;
    @Temporal(TemporalType.TIMESTAMP)
    private Date alteracao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date exclusao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnimal", fetch = FetchType.LAZY)
    private List<AgendasTO> agendasTOList;
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ClientesTO idCliente;
    @JoinColumn(name = "idRaca", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private RacasTO idRaca;

    public AnimaisTO() {
    }

    public AnimaisTO(Integer id) {
        this.id = id;
    }

    public AnimaisTO(Integer id, String nome, Character sexo) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
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

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getPelagem() {
        return pelagem;
    }

    public void setPelagem(String pelagem) {
        this.pelagem = pelagem;
    }

    public Character getAVenda() {
        return aVenda;
    }

    public void setAVenda(Character aVenda) {
        this.aVenda = aVenda;
    }

    public Character getCastrado() {
        return castrado;
    }

    public void setCastrado(Character castrado) {
        this.castrado = castrado;
    }

    public Character getObito() {
        return obito;
    }

    public void setObito(Character obito) {
        this.obito = obito;
    }

    public Character getControleDeRacao() {
        return controleDeRacao;
    }

    public void setControleDeRacao(Character controleDeRacao) {
        this.controleDeRacao = controleDeRacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getUltimaVisita() {
        return ultimaVisita;
    }

    public void setUltimaVisita(Date ultimaVisita) {
        this.ultimaVisita = ultimaVisita;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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
    public List<AgendasTO> getAgendasTOList() {
        return agendasTOList;
    }

    public void setAgendasTOList(List<AgendasTO> agendasTOList) {
        this.agendasTOList = agendasTOList;
    }

    public ClientesTO getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(ClientesTO idCliente) {
        this.idCliente = idCliente;
    }

    public RacasTO getIdRaca() {
        return idRaca;
    }

    public void setIdRaca(RacasTO idRaca) {
        this.idRaca = idRaca;
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
        if (!(object instanceof AnimaisTO)) {
            return false;
        }
        AnimaisTO other = (AnimaisTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.AnimaisTO[ id=" + id + " ]";
    }

}
