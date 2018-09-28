package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Stateful
public class UsuarioDAO<TIPO> extends DAOGenerico<Usuario> implements Serializable {

    public UsuarioDAO(){
        super();
        classePersistente = Usuario.class;
        // inicializar as ordenações possiveis                
        listaOrdem.add(new Ordem("nomeUsuario", "Nome de usuário", "like"));
        listaOrdem.add(new Ordem("email", "Email", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor com a lista de ordens
        converterOrdem = new ConverterOrdem(listaOrdem);
    }
    
    @Override
    public Usuario getObjectById(Object id) throws Exception {
        Usuario obj = em.find(Usuario.class, id);
        obj.getPermissoes().size();
        return obj;
    }    
}