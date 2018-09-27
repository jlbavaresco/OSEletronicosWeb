package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.PessoaFisica;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class PessoaFisicaDAO<TIPO> extends DAOGenerico<PessoaFisica> implements Serializable {

    public PessoaFisicaDAO(){
        super();
        classePersistente = PessoaFisica.class;
        ordem = "nome";
    }
    
    @Override
    public PessoaFisica getObjectById(Object id) throws Exception {
        PessoaFisica obj = em.find(PessoaFisica.class, id);
        obj.getPermissoes().size();
        return obj;
    }        
}