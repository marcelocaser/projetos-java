package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@Entity
@Table(name = "VEN_PEDIDO", catalog = "", schema = "VISITACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VenPedidoTO.findAll", query = "SELECT v FROM VenPedidoTO v")})
public class VenPedidoTO implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(nullable = false, precision = 38, scale = 0)
    private BigDecimal numero;
    private BigInteger numerodistribuidor;
    private BigInteger numeronota;
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datafaturamento;
    @Column(length = 20)
    private String cnpj;
    private BigInteger canal;
    private BigInteger condicao;
    private BigInteger forma;
    private BigInteger prazo;
    @Column(length = 30)
    private String tipopedido;
    @Column(length = 200)
    private String status;
    @Column(length = 30)
    private String origem;
    @Column(precision = 14, scale = 2)
    private BigDecimal valorbruto;
    @Column(precision = 12, scale = 2)
    private BigDecimal valorliquido;
    @Column(precision = 12, scale = 2)
    private BigDecimal valorfaturado;
    @Column(precision = 10, scale = 4)
    private BigDecimal coicidencia;
    @Column(length = 20)
    private String setorgerenten;
    @Column(length = 20)
    private String setorgerenter;
    @Column(length = 20)
    private String setorsupervisor;
    @Column(length = 20)
    private String setorvendedor;
    @Column(length = 200)
    private String gerentennome;
    @Column(length = 200)
    private String gerenternome;
    @Column(length = 200)
    private String supervisornome;
    @Column(length = 200)
    private String vendedornome;
    @Column(length = 100)
    private String canalnome;
    @Column(length = 100)
    private String condicaonome;
    @Column(length = 100)
    private String formanome;
    @Column(length = 100)
    private String prazonome;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datacriacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataatualizacao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataexclusao;
    @Column(length = 200)
    private String clienterazao;
    @Column(length = 200)
    private String clientefantasia;
    @Column(length = 200)
    private String clientecidade;
    @Column(length = 3)
    private String clienteuf;
    private BigInteger numerodispositivo;
    @Column(length = 500)
    private String obs;
    @Column(length = 100)
    private String equipe;
    @Column(length = 500)
    private String motivo;
    @Column(length = 100)
    private String motivousuario;
    @Temporal(TemporalType.TIMESTAMP)
    private Date motivodata;
    private BigInteger tevebloqueio;
    @Column(length = 100)
    private String unidadenegocio;
    @Column(length = 50)
    private String imei;
    @Temporal(TemporalType.TIMESTAMP)
    private Date datarecebimento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataenvio;

    public VenPedidoTO() {
    }

    public VenPedidoTO(BigDecimal numero) {
        this.numero = numero;
    }

    public BigDecimal getNumero() {
        return numero;
    }

    public void setNumero(BigDecimal numero) {
        this.numero = numero;
    }

    public BigInteger getNumerodistribuidor() {
        return numerodistribuidor;
    }

    public void setNumerodistribuidor(BigInteger numerodistribuidor) {
        this.numerodistribuidor = numerodistribuidor;
    }

    public BigInteger getNumeronota() {
        return numeronota;
    }

    public void setNumeronota(BigInteger numeronota) {
        this.numeronota = numeronota;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getDatafaturamento() {
        return datafaturamento;
    }

    public void setDatafaturamento(Date datafaturamento) {
        this.datafaturamento = datafaturamento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigInteger getCanal() {
        return canal;
    }

    public void setCanal(BigInteger canal) {
        this.canal = canal;
    }

    public BigInteger getCondicao() {
        return condicao;
    }

    public void setCondicao(BigInteger condicao) {
        this.condicao = condicao;
    }

    public BigInteger getForma() {
        return forma;
    }

    public void setForma(BigInteger forma) {
        this.forma = forma;
    }

    public BigInteger getPrazo() {
        return prazo;
    }

    public void setPrazo(BigInteger prazo) {
        this.prazo = prazo;
    }

    public String getTipopedido() {
        return tipopedido;
    }

    public void setTipopedido(String tipopedido) {
        this.tipopedido = tipopedido;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public BigDecimal getValorbruto() {
        return valorbruto;
    }

    public void setValorbruto(BigDecimal valorbruto) {
        this.valorbruto = valorbruto;
    }

    public BigDecimal getValorliquido() {
        return valorliquido;
    }

    public void setValorliquido(BigDecimal valorliquido) {
        this.valorliquido = valorliquido;
    }

    public BigDecimal getValorfaturado() {
        return valorfaturado;
    }

    public void setValorfaturado(BigDecimal valorfaturado) {
        this.valorfaturado = valorfaturado;
    }

    public BigDecimal getCoicidencia() {
        return coicidencia;
    }

    public void setCoicidencia(BigDecimal coicidencia) {
        this.coicidencia = coicidencia;
    }

    public String getSetorgerenten() {
        return setorgerenten;
    }

    public void setSetorgerenten(String setorgerenten) {
        this.setorgerenten = setorgerenten;
    }

    public String getSetorgerenter() {
        return setorgerenter;
    }

    public void setSetorgerenter(String setorgerenter) {
        this.setorgerenter = setorgerenter;
    }

    public String getSetorsupervisor() {
        return setorsupervisor;
    }

    public void setSetorsupervisor(String setorsupervisor) {
        this.setorsupervisor = setorsupervisor;
    }

    public String getSetorvendedor() {
        return setorvendedor;
    }

    public void setSetorvendedor(String setorvendedor) {
        this.setorvendedor = setorvendedor;
    }

    public String getGerentennome() {
        return gerentennome;
    }

    public void setGerentennome(String gerentennome) {
        this.gerentennome = gerentennome;
    }

    public String getGerenternome() {
        return gerenternome;
    }

    public void setGerenternome(String gerenternome) {
        this.gerenternome = gerenternome;
    }

    public String getSupervisornome() {
        return supervisornome;
    }

    public void setSupervisornome(String supervisornome) {
        this.supervisornome = supervisornome;
    }

    public String getVendedornome() {
        return vendedornome;
    }

    public void setVendedornome(String vendedornome) {
        this.vendedornome = vendedornome;
    }

    public String getCanalnome() {
        return canalnome;
    }

    public void setCanalnome(String canalnome) {
        this.canalnome = canalnome;
    }

    public String getCondicaonome() {
        return condicaonome;
    }

    public void setCondicaonome(String condicaonome) {
        this.condicaonome = condicaonome;
    }

    public String getFormanome() {
        return formanome;
    }

    public void setFormanome(String formanome) {
        this.formanome = formanome;
    }

    public String getPrazonome() {
        return prazonome;
    }

    public void setPrazonome(String prazonome) {
        this.prazonome = prazonome;
    }

    public Date getDatacriacao() {
        return datacriacao;
    }

    public void setDatacriacao(Date datacriacao) {
        this.datacriacao = datacriacao;
    }

    public Date getDataatualizacao() {
        return dataatualizacao;
    }

    public void setDataatualizacao(Date dataatualizacao) {
        this.dataatualizacao = dataatualizacao;
    }

    public Date getDataexclusao() {
        return dataexclusao;
    }

    public void setDataexclusao(Date dataexclusao) {
        this.dataexclusao = dataexclusao;
    }

    public String getClienterazao() {
        return clienterazao;
    }

    public void setClienterazao(String clienterazao) {
        this.clienterazao = clienterazao;
    }

    public String getClientefantasia() {
        return clientefantasia;
    }

    public void setClientefantasia(String clientefantasia) {
        this.clientefantasia = clientefantasia;
    }

    public String getClientecidade() {
        return clientecidade;
    }

    public void setClientecidade(String clientecidade) {
        this.clientecidade = clientecidade;
    }

    public String getClienteuf() {
        return clienteuf;
    }

    public void setClienteuf(String clienteuf) {
        this.clienteuf = clienteuf;
    }

    public BigInteger getNumerodispositivo() {
        return numerodispositivo;
    }

    public void setNumerodispositivo(BigInteger numerodispositivo) {
        this.numerodispositivo = numerodispositivo;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getMotivousuario() {
        return motivousuario;
    }

    public void setMotivousuario(String motivousuario) {
        this.motivousuario = motivousuario;
    }

    public Date getMotivodata() {
        return motivodata;
    }

    public void setMotivodata(Date motivodata) {
        this.motivodata = motivodata;
    }

    public BigInteger getTevebloqueio() {
        return tevebloqueio;
    }

    public void setTevebloqueio(BigInteger tevebloqueio) {
        this.tevebloqueio = tevebloqueio;
    }

    public String getUnidadenegocio() {
        return unidadenegocio;
    }

    public void setUnidadenegocio(String unidadenegocio) {
        this.unidadenegocio = unidadenegocio;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getDatarecebimento() {
        return datarecebimento;
    }

    public void setDatarecebimento(Date datarecebimento) {
        this.datarecebimento = datarecebimento;
    }

    public Date getDataenvio() {
        return dataenvio;
    }

    public void setDataenvio(Date dataenvio) {
        this.dataenvio = dataenvio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenPedidoTO)) {
            return false;
        }
        VenPedidoTO other = (VenPedidoTO) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.VenPedidoTO[ numero=" + numero + " ]";
    }

}
