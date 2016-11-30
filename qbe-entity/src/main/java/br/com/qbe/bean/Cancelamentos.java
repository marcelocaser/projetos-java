package br.com.qbe.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@XmlRootElement
@JsonIgnoreProperties()
public class Cancelamentos implements Serializable {

    @Column(name = "cancelamentos")
    @JsonProperty(value = "apolices")
    private List<Cancelamento> cancelamentos ;

    public List<Cancelamento> getCancelamentos() {
        if (cancelamentos == null) {
            cancelamentos = new ArrayList<>();
        }
        return cancelamentos;
    }

    public void setCancelamentos(List<Cancelamento> cancelamentos) {
        this.cancelamentos = cancelamentos;
    }


    public void addCancelamentos(Cancelamento cancelamento) {
        Cancelamento c = new Cancelamento();
        c.setCnpjCpf(cancelamento.getCnpjCpf());
        c.setDataCancelamento(cancelamento.getDataCancelamento());
        c.setTipoCancelamento(cancelamento.getTipoCancelamento());
        c.setNumeroBilhete(cancelamento.getNumeroBilhete());
        c.setTipoArquivo(cancelamento.getTipoArquivo());
        getCancelamentos().add(c);
    }

    public class Cancelamento {

        @Column(name = "tipoArquivo")
        private String tipoArquivo;
        @Column(name = "numeroBilhete")
        private String numeroBilhete;
        @Column(name = "cnpjCpf")
        private String cnpjCpf;
        @Column(name = "dataCancelamento")
        private String dataCancelamento;
        @Column(name = "tipoCancelamento")
        private String tipoCancelamento;

        public String getTipoArquivo() {
            return tipoArquivo;
        }

        public void setTipoArquivo(String tipoArquivo) {
            this.tipoArquivo = tipoArquivo;
        }

        public String getNumeroBilhete() {
            return numeroBilhete;
        }

        public void setNumeroBilhete(String numeroBilhete) {
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

        public String getTipoCancelamento() {
            return tipoCancelamento;
        }

        public void setTipoCancelamento(String tipoCancelamento) {
            this.tipoCancelamento = tipoCancelamento;
        }

    }

}
