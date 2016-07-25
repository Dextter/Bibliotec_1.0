 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package br.ifrn.tads.poo.biblioteca.ManagedBean;

import br.ifrn.tads.poo.biblioteca.DAO.ItemAcervoDAO;
import br.ifrn.tads.poo.biblioteca.acervo.Livro;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
/**
 *
 * @author pablopc
 */
@ManagedBean

public class BeanCadastrar{
   private Livro livro; 
   private ItemAcervoDAO itemacervoDao;   

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public ItemAcervoDAO getItemacervoDao() {
        return itemacervoDao;
    }

    public void setItemacervoDao(ItemAcervoDAO itemacervoDao) {
        this.itemacervoDao = itemacervoDao;
    }
    public BeanCadastrar(){
        this.livro = new Livro();
    }
    //chama o método que cadastra um formulário
     public void Cadastrar(){         
        try {            
            this.itemacervoDao = new ItemAcervoDAO();
            boolean chamacadas = this.itemacervoDao.cadastrarLivro(this.livro);                        
             try {
                 FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
             } catch (IOException ex) {
                 Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }this.livro = new Livro();
       }         
}