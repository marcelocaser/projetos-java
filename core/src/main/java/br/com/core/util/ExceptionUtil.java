package br.com.core.util;

import br.com.core.exception.AcessoException;
import br.com.core.exception.NegocioException;
import javax.persistence.PersistenceException;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate4.HibernateSystemException;

/**
 *
 * @author marcelocaser
 */
public class ExceptionUtil {
    
    private ExceptionUtil() {
    }
    
    public static Exception getException(Throwable e){
        Exception ex = new Exception(e);
        if(e instanceof NegocioException){
                ex = (Exception)e;
        }else if(e.getCause() instanceof NegocioException
                        || e.getCause() instanceof AcessoException){
               
                ex = (Exception)e.getCause();
               
        }else if(e.getCause().getCause() instanceof NegocioException
                        || e.getCause().getCause() instanceof AcessoException
                                /*|| e.getCause().getCause() instanceof BDException*/
                                        /*|| e.getCause().getCause() instanceof PropertyValueException*/
                                                || e.getCause().getCause() instanceof DataIntegrityViolationException
                                                        || e.getCause().getCause() instanceof IndexOutOfBoundsException
                                                                || e.getCause().getCause() instanceof HibernateSystemException
                                                                        || e.getCause().getCause() instanceof NullPointerException
                                                                                || e.getCause().getCause() instanceof PersistenceException
                                                                                        || e.getCause().getCause() instanceof DatabaseException){
               
                ex = (Exception)e.getCause().getCause();  
               
        }else if(e.getCause().getCause().getCause() instanceof NegocioException
                        || e.getCause().getCause().getCause() instanceof AcessoException
                                /*|| e.getCause().getCause().getCause() instanceof BDException*/
                                        /*|| e.getCause().getCause().getCause() instanceof PropertyValueExc*/
                                                || e.getCause().getCause().getCause() instanceof DataIntegrityViolationException
                                                        || e.getCause().getCause().getCause() instanceof IndexOutOfBoundsException
                                                                || e.getCause().getCause().getCause() instanceof HibernateSystemException
                                                                        || e.getCause().getCause().getCause() instanceof NullPointerException
                                                                                || e.getCause().getCause().getCause() instanceof PersistenceException
                                                                                        /*|| e.getCause().getCause().getCause() instanceof InvalidStateException*/){
               
                ex = (Exception)e.getCause().getCause().getCause();  
               
        }else if(e.getCause().getCause().getCause().getCause() instanceof NegocioException
                        || e.getCause().getCause().getCause().getCause() instanceof AcessoException
                                /*|| e.getCause().getCause().getCause().getCause() instanceof BDException*/
                                        /*|| e.getCause().getCause().getCause().getCause() instanceof PropertyValueException*/
                                                || e.getCause().getCause().getCause().getCause() instanceof DataIntegrityViolationException
                                                        || e.getCause().getCause().getCause().getCause() instanceof IndexOutOfBoundsException
                                                                || e.getCause().getCause().getCause().getCause() instanceof HibernateSystemException
                                                                        || e.getCause().getCause().getCause().getCause() instanceof NullPointerException
                                                                                || e.getCause().getCause().getCause().getCause() instanceof PersistenceException
                                                                                        /*|| e.getCause().getCause().getCause().getCause() instanceof InvalidStateException*/){
               
                ex = (Exception)e.getCause().getCause().getCause().getCause();                  
        }
        return ex;
    }

    
}
