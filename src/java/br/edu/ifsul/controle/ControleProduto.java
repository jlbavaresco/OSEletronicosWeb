package br.edu.ifsul.controle;


import br.edu.ifsul.dao.MarcaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.modelo.Marca;


import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleProduto")
@ViewScoped
public class ControleProduto implements Serializable {
    
    @EJB
    private ProdutoDAO<Produto> dao;
    private Produto objeto;
    @EJB
    private MarcaDAO<Marca> daoMarca;
    
    public ControleProduto(){
        
    }    
   
    public String listar(){
        return "/privado/produto/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Produto();        
    }
    

    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);            
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        } 
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");            
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public ProdutoDAO getDao() {
        return dao;
    }

    public void setDao(ProdutoDAO dao) {
        this.dao = dao;
    }

    public Produto getObjeto() {
        return objeto;
    }

    public void setObjeto(Produto objeto) {
        this.objeto = objeto;
    }

    public MarcaDAO<Marca> getDaoMarca() {
        return daoMarca;
    }

    public void setDaoMarca(MarcaDAO<Marca> daoMarca) {
        this.daoMarca = daoMarca;
    }

}
