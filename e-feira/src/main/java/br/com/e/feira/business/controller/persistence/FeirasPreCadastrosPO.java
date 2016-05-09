package br.com.e.feira.business.controller.persistence;

import br.com.core.entity.FeirasPreCadastrosTO;
import br.com.core.persistence.Persistence;
import br.com.e.feira.business.controller.persistence.interfaces.FeirasPreCadastros;
import java.util.List;
import javax.inject.Named;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author marce
 */
@Named
public class FeirasPreCadastrosPO extends Persistence<FeirasPreCadastrosTO> implements FeirasPreCadastros {
    
    public FeirasPreCadastrosPO() {
        setClazz(FeirasPreCadastrosTO.class);
    }
    
    @Override
    @Transactional
    public void alterar(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        update(feirasPreCadastrosTO);
    }
    
    @Override
    @Transactional
    public void excluir(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        delete(feirasPreCadastrosTO);
    }
    
    @Override
    @Transactional
    public void incluir(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        create(feirasPreCadastrosTO);
    }
    
    @Override
    public FeirasPreCadastrosTO consultar(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        return find(feirasPreCadastrosTO);
    }
    
    @Override
    public List<FeirasPreCadastrosTO> listar(FeirasPreCadastrosTO feirasPreCadastrosTO) {
        return list(feirasPreCadastrosTO);
    }
    
}
