package br.com.core.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author marce
 */
@Embeddable
public class VenPedidoItemTOPK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private BigInteger pedido;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String produto;

    public VenPedidoItemTOPK() {
    }

    public VenPedidoItemTOPK(BigInteger pedido, String produto) {
        this.pedido = pedido;
        this.produto = produto;
    }

    public BigInteger getPedido() {
        return pedido;
    }

    public void setPedido(BigInteger pedido) {
        this.pedido = pedido;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pedido != null ? pedido.hashCode() : 0);
        hash += (produto != null ? produto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VenPedidoItemTOPK)) {
            return false;
        }
        VenPedidoItemTOPK other = (VenPedidoItemTOPK) object;
        if ((this.pedido == null && other.pedido != null) || (this.pedido != null && !this.pedido.equals(other.pedido))) {
            return false;
        }
        if ((this.produto == null && other.produto != null) || (this.produto != null && !this.produto.equals(other.produto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.core.entity.VenPedidoItemTOPK[ pedido=" + pedido + ", produto=" + produto + " ]";
    }

}
