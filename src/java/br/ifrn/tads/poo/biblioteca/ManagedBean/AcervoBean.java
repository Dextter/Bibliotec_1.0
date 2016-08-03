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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pablopc
 */
@ManagedBean
@ViewScoped
public class AcervoBean {    
    private List<Livro> listaLivros;    
    private List<Apostila> listaApostilas;  
    private List<Texto> listaTextos;  
    private ItemAcervoDAO acervodao;
    private Livro livros;                   
    private Texto textos;   
    private Apostila Apostilas;   

    public List<Apostila> getListaApostilas() {
        listarApostilas();
        return listaApostilas;
    }

    public void setListaApostilas(List<Apostila> listaApostilas) {
        this.listaApostilas = listaApostilas;
    }

    public List<Texto> getListaTextos() {
        listarTextos();
        return listaTextos;
    }

    public void setListaTextos(List<Texto> listaTextos) {
        this.listaTextos = listaTextos;
    }

    public Texto getTextos() {
        return textos;
    }

    public void setTextos(Texto textos) {
        this.textos = textos;
    }

    public Apostila getApostilas() {
        return Apostilas;
    }

    public void setApostilas(Apostila Apostilas) {
        this.Apostilas = Apostilas;
    }
        
    public ItemAcervoDAO getAcervodao() {
        return acervodao;
    }

    public void setAcervodao(ItemAcervoDAO acervodao) {
        this.acervodao = acervodao;
    }

    public Livro getLivros() {
        return livros;
    }

    public void setLivros(Livro livros) {
        this.livros = livros;
    }
    public AcervoBean() {
        this.livros = new Livro();                
    }
    

    public List<Livro> getListaLivros() {
        listarLivros();
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }
    
//chama o método que lista todos os livros não alugados
    public final void listarLivros(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaLivros = this.acervodao.listarLivros();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                       
    
    //chama o método que lista todas as apostilas não alugados
    public final void listarApostilas(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaApostilas = this.acervodao.listarApostilas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                       
    
    //chama o método que lista todos os textos não alugados
    public final void listarTextos(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaTextos = this.acervodao.listarTextos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                       
}