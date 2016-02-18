package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @EmbeddedId
    protected AnimaisTOPK animaisTOPK;
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
    @Column(precision = 3, scale = 3)
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAnimal")
    private List<AgendasTO> agendasTOList;
    @JoinColumn(name = "idCliente", referencedColumnName = "id")
    @ManyToOne
    private ClientesTO idCliente;
    @JoinColumn(name = "idRaca", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private RacasTO racasTO;

    public AnimaisTO() {
    }

    public AnimaisTO(AnimaisTOPK animaisTOPK) {
        this.animaisTOPK = animaisTOPK;
    }

    public AnimaisTO(AnimaisTOPK animaisTOPK, String nome, Character sexo, String especie, Character castrado) {
        this.animaisTOPK = animaisTOPK;
        this.nome = nome;
        this.sexo = sexo;
        this.castrado = castrado;
    }

    public AnimaisTO(int id, int idRaca) {
        this.animaisTOPK = new AnimaisTOPK(id, idRaca);
    }

    public AnimaisTOPK getAnimaisTOPK() {
        return animaisTOPK;
    }

    public void setAnimaisTOPK(AnimaisTOPK animaisTOPK) {
        this.animaisTOPK = animaisTOPK;
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

    public RacasTO getRacasTO() {
        return racasTO;
    }

    public void setRacasTO(RacasTO racasTO) {
        this.racasTO = racasTO;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (animaisTOPK != null ? animaisTOPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AnimaisTO)) {
            return false;
        }
        AnimaisTO other = (AnimaisTO) object;
        if ((this.animaisTOPK == null && other.animaisTOPK != null) || (this.animaisTOPK != null && !this.animaisTOPK.equals(other.animaisTOPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.AnimaisTO[ animaisTOPK=" + animaisTOPK + " ]";
    }

}
