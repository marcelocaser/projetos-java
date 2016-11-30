package br.com.qbe.bean;

import br.com.qbe.entity.ApolicesTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marce
 */
@XmlRootElement
@JsonIgnoreProperties()
public class ApolicesEnvios implements Serializable {

    @Column(name = "apolices")
    private List<ApolicesTO> apolices;

    public List<ApolicesTO> getApolices() {
        return apolices;
    }

    public void setApolices(List<ApolicesTO> apolices) {
        this.apolices = apolices;
    }

}
