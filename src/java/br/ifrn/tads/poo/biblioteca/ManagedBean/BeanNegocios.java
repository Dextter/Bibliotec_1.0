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
public class BeanNegocios {
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

    public BeanNegocios() {
        this.livros = new Livro();                
        this.apostilas = new Apostila();
        this.textos = new Texto();
    }
    
    public List<Livro> getListaLivrosAlugados() {
        listarLivrosAlugados();
        return listaLivrosAlugados;
    }

    public void setListaLivrosAlugados(List<Livro> listaLivrosAlugados) {
        this.listaLivrosAlugados = listaLivrosAlugados;
    }

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }

    public Livro getLivros() {
        return livros;
    }

    public void setLivros(Livro livros) {
        this.livros = livros;
    }

    public List<Apostila> getListaApostilasAlugadas() {
        listarApostilasAlugadas();
        return listaApostilasAlugadas;
    }

    public void setListaApostilasAlugadas(List<Apostila> listaApostilasAlugadas) {
        this.listaApostilasAlugadas = listaApostilasAlugadas;
    }

    public List<Apostila> getListaApostilas() {
        return listaApostilas;
    }

    public void setListaApostilas(List<Apostila> listaApostilas) {
        this.listaApostilas = listaApostilas;
    }

    public Apostila getApostilas() {
        return apostilas;
    }

    public void setApostilas(Apostila apostilas) {
        this.apostilas = apostilas;
    }

    public List<Texto> getListaTextosAlugados() {
        return listaTextosAlugados;
    }

    public void setListaTextosAlugados(List<Texto> listaTextosAlugados) {
        this.listaTextosAlugados = listaTextosAlugados;
    }

    public List<Texto> getListaTextos() {
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

    public ItemAcervoDAO getAcervodao() {
        return acervodao;
    }

    public void setAcervodao(ItemAcervoDAO acervodao) {
        this.acervodao = acervodao;
    }
    
    public void alugarLivro(){
        try {
            this.acervodao = new ItemAcervoDAO();
            boolean alugar = this.acervodao.alugarLivros(livros);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alugarApostila(){
        try {
            this.acervodao = new ItemAcervoDAO();
            boolean alugar = this.acervodao.alugarApostilas(apostilas);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void alugarTexto(){
        try {
            this.acervodao = new ItemAcervoDAO();
            boolean alugar = this.acervodao.alugarTextos(textos);
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
    
    //chama o método que lista todas as apostilas  alugados
    public final void listarApostilasAlugadas(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaApostilas = this.acervodao.listarApostilasAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //chama o método que lista os textos alugados
    public final void listarTextosAlugados(){        
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaTextosAlugados = this.acervodao.listarTextosAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public final void reservarLivro(){
        
    }
    
    public final void reservarApostila(){
        
    }
    
    public final void reservarTexto(){
        
    }
}
