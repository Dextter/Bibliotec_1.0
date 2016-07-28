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
    private List<Livro> listaLivrosAlugados;
    private List<Livro> listaLivros;        
    private Livro livros;                
    private List<Apostila> listaApostilasAlugadas;
    private List<Apostila> listaApostilas;        
    private Apostila apostilas;                
    private List<Texto> listaTextosAlugados;
    private List<Texto> listaTextos;        
    private Texto textos;                
    private ItemAcervoDAO acervodao;

    public List<Apostila> getListaApostilasAlugadas() {
        return listaApostilasAlugadas;
    }

    public void setListaApostilasAlugadas(List<Apostila> listaApostilasAlugadas) {
        this.listaApostilasAlugadas = listaApostilasAlugadas;
    }

    public List<Apostila> getListaApostilas() {
        listarApostilasAlugadas();
        return listaApostilas;
    }

    public void setListaApostilas(List<Apostila> listaApostilas) {
        this.listaApostilas = listaApostilas;
    }

    public Apostila getApostilas() {
        listarApostilas();
        return apostilas;
    }

    public void setApostilas(Apostila apostilas) {
        this.apostilas = apostilas;
    }

    public List<Texto> getListaTextosAlugados() {
        listarTextosAlugados();
        return listaTextosAlugados;
    }

    public void setListaTextosAlugados(List<Texto> listaTextosAlugados) {
        this.listaTextosAlugados = listaTextosAlugados;
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

    public List<Livro> getListaLivrosAlugados() {
        listarLivrosAlugados();
        return listaLivrosAlugados;
    }

    public void setListaLivrosAlugados(List<Livro> listaLivrosAlugados) {
        this.listaLivrosAlugados = listaLivrosAlugados;
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
    //chama o método que lista todos livros não alugados
    public final void listarLivros(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaLivros = this.acervodao.listarLivros();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //chama o método que lista os livros alugados
    public final void listarLivrosAlugados(){        
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaLivrosAlugados = this.acervodao.listarLivrosAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarApostilas() {
       try {
            this.acervodao = new ItemAcervoDAO();
            this.listaApostilas = this.acervodao.listarApostilas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarApostilasAlugadas() {
       try {
            this.acervodao = new ItemAcervoDAO();
            this.listaApostilasAlugadas = this.acervodao.listarApostilasAlugadas();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarTextos() {
       try {
            this.acervodao = new ItemAcervoDAO();
            this.listaTextos = this.acervodao.listarTextos();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listarTextosAlugados() {
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaTextosAlugados = this.acervodao.listarTextosAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
}