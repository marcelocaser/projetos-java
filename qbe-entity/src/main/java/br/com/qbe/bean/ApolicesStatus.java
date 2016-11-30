package br.com.qbe.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class ApolicesStatus implements Serializable {
    
    @Column(name = "apolices")
    private List<Apolice> apolicesStatus;

    public List<Apolice> getApolices() {
        if (apolicesStatus == null) {
            apolicesStatus = new ArrayList<>();
        }
        return apolicesStatus;
    }

    public void setApolices(List<Apolice> apolices) {
        this.apolicesStatus = apolices;
    }
    
    public void addApolices(Apolice apolice) {
        Apolice ap = new Apolice();
        ap.setApolice(apolice.getApolice());
        ap.setStatus(apolice.getStatus());
        ap.setTexto(apolice.getTexto());
        getApolices().add(ap);
    }
    
    public class Apolice {

        @Column(name = "apolice")
        private String apolice;
        @Column(name = "status")
        private String status;
        @Column(name = "texto")
        private String texto;

        public String getApolice() {
            return apolice;
        }

        public void setApolice(String apolice) {
            this.apolice = apolice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTexto() {
            return texto;
        }

        public void setTexto(String texto) {
            this.texto = texto;
        }

    }

}
