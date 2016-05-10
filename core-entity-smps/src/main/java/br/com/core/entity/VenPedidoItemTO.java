package br.com.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "VEN_PEDIDOITEM", catalog = "", schema = "VISITACAO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VenPedidoItemTO.findAll", query = "SELECT v FROM VenPedidoItemTO v")})
public class VenPedidoItemTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected VenPedidoItemTOPK venPedidoItemTOPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(precision = 10, scale = 2)
    private BigDecimal valorbruto;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorunitario;
    private BigInteger qntvenda;
    private BigInteger qntbonificacao;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorfaturado;
    private BigInteger qntfaturada;
    @Column(length = 200)
    private String produtodescricao;
    @Column(precision = 10, scale = 4)
    private BigDecimal descontopermitido;
    @Column(precision = 10, scale = 2)
    private BigDecimal valminpermitido;
    @Column(precision = 10, scale = 2)
    private BigDecimal comissao;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorunitbon;
    @Column(precision = 10, scale = 2)
    private BigDecimal custounitario;
    private BigInteger canal;
    private BigInteger condicao;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataexclusao;
    private BigInteger tevebloqueio;
    private BigInteger produtokit;

    public VenPedidoItemTO() {
    }

    public VenPedidoItemTO(VenPedidoItemTOPK venPedidoItemTOPK) {
        this.venPedidoItemTOPK = venPedidoItemTOPK;
    }

    public VenPedidoItemTO(BigInteger pedido, String produto) {
        this.venPedidoItemTOPK = new VenPedidoItemTOPK(pedido, produto);
    }

    public VenPedidoItemTOPK getVenPedidoItemTOPK() {
        return venPedidoItemTOPK;
    }

    public void setVenPedidoItemTOPK(VenPedidoItemTOPK venPedidoItemTOPK) {
        this.venPedidoItemTOPK = venPedidoItemTOPK;
    }

    public BigDecimal getValorbruto() {
        return valorbruto;
    }

    public void setValorbruto(BigDecimal valorbruto) {
        this.valorbruto = valorbruto;
    }

    public BigDecimal getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(BigDecimal valorunitario) {
        this.valorunitario = valorunitario;
    }

    public BigInteger getQntvenda() {
        return qntvenda;
    }

    public void setQntvenda(BigInteger qntvenda) {
        this.qntvenda = qntvenda;
    }

    public BigInteger getQntbonificacao() {
        return qntbonificacao;
    }

    public void setQntbonificacao(BigInteger qntbonificacao) {
        this.qntbonificacao = qntbonificacao;
    }

    public BigDecimal getValorfaturado() {
        return valorfaturado;
    }

    public void setValorfaturado(BigDecimal valorfaturado) {
        this.valorfaturado = valorfaturado;
    }

    public BigInteger getQntfaturada() {
        return qntfaturada;
    }

    public void setQntfaturada(BigInteger qntfaturada) {
        this.qntfaturada = qntfaturada;
    }

    public String getProdutodescricao() {
        return produtodescricao;
    }

    public void setProdutodescricao(String produtodescricao) {
        this.produtodescricao = produtodescricao;
    }

    public BigDecimal getDescontopermitido() {
        return descontopermitido;
    }

    public void setDescontopermitido(BigDecimal descontopermitido) {
        this.descontopermitido = descontopermitido;
    }

    public BigDecimal getValminpermitido() {
        return valminpermitido;
    }

    public void setValminpermitido(BigDecimal valminpermitido) {
        this.valminpermitido = valminpermitido;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public BigDecimal getValorunitbon() {
        return valorunitbon;
    }

    public void setValorunitbon(BigDecimal valorunitbon) {
        this.valorunitbon = valorunitbon;
    }

    public BigDecimal getCustounitario() {
        return custounitario;
    }

    public void setCustounitario(BigDecimal custounitario) {
        this.custounitario = custounitario;
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

    public Date getDataexclusao() {
        return dataexclusao;
    }

    public void setDataexclusao(Date dataexclusao) {
        this.dataexclusao = dataexclusao;
    }

    public BigInteger getTevebloqueio() {
        return tevebloqueio;
    }

    public void setTevebloqueio(BigInteger tevebloqueio) {
        this.tevebloqueio = tevebloqueio;
    }

    public BigInteger getProdutokit() {
        return produtokit;
    }

    public void setProdutokit(BigInteger produtokit) {
        this.produtokit = produtokit;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (venPedidoItemTOPK != null ? venPedidoItemTOPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenPedidoItemTO)) {
            return false;
        }
        VenPedidoItemTO other = (VenPedidoItemTO) object;
        if ((this.venPedidoItemTOPK == null && other.venPedidoItemTOPK != null) || (this.venPedidoItemTOPK != null && !this.venPedidoItemTOPK.equals(other.venPedidoItemTOPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.VenPedidoItemTO[ venPedidoItemTOPK=" + venPedidoItemTOPK + " ]";
    }

}
