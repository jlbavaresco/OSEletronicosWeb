package br.edu.ifsul.controle;


import br.edu.ifsul.dao.EquipamentoDAO;
import br.edu.ifsul.dao.OrdemServicoDAO;
import br.edu.ifsul.dao.PessoaFisicaDAO;
import br.edu.ifsul.dao.ProdutoDAO;
import br.edu.ifsul.dao.ServicoDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Equipamento;
import br.edu.ifsul.modelo.FormaPagamento;
import br.edu.ifsul.modelo.ItemServico;


import br.edu.ifsul.modelo.OrdemServico;
import br.edu.ifsul.modelo.PessoaFisica;
import br.edu.ifsul.modelo.Produto;
import br.edu.ifsul.modelo.Servico;
import br.edu.ifsul.modelo.Status;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Named(value = "controleOrdemServico")
@ViewScoped
public class ControleOrdemServico implements Serializable {
    
    @EJB
    private OrdemServicoDAO<OrdemServico> dao;
    private OrdemServico objeto;
    @EJB
    private UsuarioDAO<Usuario> daoUsuario;
    @EJB
    private PessoaFisicaDAO<PessoaFisica> daoPessoaFisica;
    @EJB
    private ServicoDAO<Servico> daoServico;
    @EJB
    private EquipamentoDAO<Equipamento> daoEquipamento;
    @EJB
    private ProdutoDAO<Produto> daoProduto;
    private ItemServico itemServico;
    private Boolean novoItemServico;
    
    public ControleOrdemServico(){
        
    }
       
    public String listar(){
        return "/privado/ordemservico/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new OrdemServico();        
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
    
    public void novoItemServico(){
        itemServico = new ItemServico();
        novoItemServico = true;
    }    
    
    public void alterarItemServico(int index){
        itemServico = objeto.getListaServicos().get(index);
        novoItemServico = false;
    }
    
    public void salvarItemServico(){
        if (novoItemServico){
            objeto.adicionarServico(itemServico);
        } else {
            atualizarValorTotal();
        }
        Util.mensagemInformacao("Serviço adicionado com sucesso");
    }  
    
    public void removerItemServico(int index){
        objeto.removerServico(index);
        Util.mensagemInformacao("Serviço removido com sucesso");
    }     
  
    public void atualizaValorUnitarioItemServico(){
        if (itemServico != null){
            if (itemServico.getServico()!= null){
                itemServico.setValorUnitario(itemServico.getServico().getValor());
            }
        }
    }   
    
    public void calculaValorTotalItemServico(){
        if (itemServico.getValorUnitario() != null
                && itemServico.getQuantidade() != null){
            itemServico.setValorTotal(itemServico.getValorUnitario() * itemServico.getQuantidade());
        }
    }  
    
    private void atualizarValorTotal() {
        objeto.setValorTotal(0.0);
        Double total = 0.0;
        for (ItemServico is : objeto.getListaServicos()){
            total += is.getValorTotal();
        }
        objeto.setValorTotal(total);
    }        

    public OrdemServicoDAO getDao() {
        return dao;
    }

    public void setDao(OrdemServicoDAO dao) {
        this.dao = dao;
    }

    public OrdemServico getObjeto() {
        return objeto;
    }

    public void setObjeto(OrdemServico objeto) {
        this.objeto = objeto;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public PessoaFisicaDAO<PessoaFisica> getDaoPessoaFisica() {
        return daoPessoaFisica;
    }

    public void setDaoPessoaFisica(PessoaFisicaDAO<PessoaFisica> daoPessoaFisica) {
        this.daoPessoaFisica = daoPessoaFisica;
    }

    public ServicoDAO<Servico> getDaoServico() {
        return daoServico;
    }

    public void setDaoServico(ServicoDAO<Servico> daoServico) {
        this.daoServico = daoServico;
    }

    public EquipamentoDAO<Equipamento> getDaoEquipamento() {
        return daoEquipamento;
    }

    public void setDaoEquipamento(EquipamentoDAO<Equipamento> daoEquipamento) {
        this.daoEquipamento = daoEquipamento;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public ItemServico getItemServico() {
        return itemServico;
    }

    public void setItemServico(ItemServico itemServico) {
        this.itemServico = itemServico;
    }

    public Boolean getNovoItemServico() {
        return novoItemServico;
    }

    public void setNovoItemServico(Boolean novoItemServico) {
        this.novoItemServico = novoItemServico;
    }




}
