 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package br.ifrn.tads.poo.biblioteca.ManagedBean;

import br.ifrn.tads.poo.biblioteca.DAO.ItemAcervoDAO;
import br.ifrn.tads.poo.biblioteca.acervo.Apostila;
import br.ifrn.tads.poo.biblioteca.acervo.Livro;
import br.ifrn.tads.poo.biblioteca.acervo.Texto;
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
   private Apostila apostila;
   private Texto texto;
   private ItemAcervoDAO itemacervoDao;   

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Apostila getApostila() {
        return apostila;
    }

    public void setApostila(Apostila apostila) {
        this.apostila = apostila;
    }

    public Texto getTexto() {
        return texto;
    }

    public void setTexto(Texto texto) {
        this.texto = texto;
    }        

    public ItemAcervoDAO getItemacervoDao() {
        return itemacervoDao;
    }

    public void setItemacervoDao(ItemAcervoDAO itemacervoDao) {
        this.itemacervoDao = itemacervoDao;
    }
    public BeanCadastrar(){
        this.livro = new Livro();
        this.apostila = new Apostila();
        this.texto = new Texto();
    }
    
    //chama o método que cadastra um livro
     public void cadastrarLivro(){         
        try {            
            this.itemacervoDao = new ItemAcervoDAO();
            boolean chamacadas = this.itemacervoDao.cadastrarLivro(this.livro);                        
             try {
                 FacesContext.getCurrentInstance().getExternalContext().redirect("faces/acervo.xhtml");
             } catch (IOException ex) {
                 Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }this.livro = new Livro();
       }        
     
     //chama o método que cadastra uma apostila
     public void cadastrarApostila(){         
        try {            
            this.itemacervoDao = new ItemAcervoDAO();
            boolean chamacadas = this.itemacervoDao.cadastrarApostila(this.apostila);                        
             try {
                 FacesContext.getCurrentInstance().getExternalContext().redirect("faces/acervo.xhtml");
             } catch (IOException ex) {
                 Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }this.apostila = new Apostila();
       }     
     
     //chama o método que cadastra um texto
     public void cadastrarTexto(){         
        try {            
            this.itemacervoDao = new ItemAcervoDAO();
            boolean chamacadas = this.itemacervoDao.cadastrarTexto(this.texto);                        
             try {
                 FacesContext.getCurrentInstance().getExternalContext().redirect("faces/acervo.xhtml");
             } catch (IOException ex) {
                 Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
             }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BeanCadastrar.class.getName()).log(Level.SEVERE, null, ex);
        }this.texto = new Texto();
       }     
}