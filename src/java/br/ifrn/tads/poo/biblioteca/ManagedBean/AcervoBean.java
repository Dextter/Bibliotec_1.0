/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.tads.poo.biblioteca.ManagedBean;



import br.ifrn.tads.poo.biblioteca.DAO.ItemAcervoDAO;
import br.ifrn.tads.poo.biblioteca.acervo.Livro;
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
    private ItemAcervoDAO acervodao;
    private Livro livros;                

    public List<Livro> getListaLivrosAlugados() {
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
        listarItens();
        listarItensEmprestados();
    }
    

    public List<Livro> getListaLivros() {
        return listaLivros;
    }

    public void setListaLivros(List<Livro> listaLivros) {
        this.listaLivros = listaLivros;
    }
    //chama o método que lista todos livros não alugados
    public final void listarItens(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaLivros = this.acervodao.listaItens();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //chama o método que lista os livros alugados
    public final void listarItensEmprestados(){        
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaLivrosAlugados = this.acervodao.listarLivrosAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
}