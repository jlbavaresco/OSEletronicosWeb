package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Permissao;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class PermissaoDAO<TIPO> extends DAOGenerico<Permissao> implements Serializable {

    public PermissaoDAO(){
        super();
        classePersistente = Permissao.class;
        ordem = "nome";
    }
}