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
import br.ifrn.tads.poo.biblioteca.usuario.Usuario;
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
    private Usuario usuario;

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }        
    
    public void alugarLivro(int preco, int valor, int codUsuario){
        boolean cobreOValor;
        if (valor >= preco){
            cobreOValor = true;
            try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarLivros(livros, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }       
        }else {
            cobreOValor = false;
           try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarLivros(livros, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }     
         }
    }
    
    public void alugarLivroReservado(int preco, int valor, int codUsuario){
        boolean cobreOValor, reservaAprovada;         
        int codUsuarioReservante = acervodao.verificarReservanteDeLivro(codUsuario);
        if (codUsuario == codUsuarioReservante){
            reservaAprovada = true;
        } else {
            reservaAprovada = false;
        }
        if ((valor >= preco) && reservaAprovada){
            cobreOValor = true;
            try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarLivros(livros, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }               
         }
    }
    
    public void alugarApostila(int preco, int valor, int codUsuario){
        boolean cobreOValor;
        if (valor >= preco){
            cobreOValor = true;
            try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarApostilas(apostilas, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }       
        }else {
            cobreOValor = false;
           try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarApostilas(apostilas, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }     
         }
    }
    
    public void alugarTexto(int preco, int valor, int codUsuario){
        boolean cobreOValor;
        if (valor >= preco){
            cobreOValor = true;
            try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarTextos(textos, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }       
        }else {
            cobreOValor = false;
           try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.alugarTextos(textos, cobreOValor, codUsuario);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            }     
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
    
    //chama o método que lista todas as apostilas alugadas
    public final void listarApostilasAlugadas(){       
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaApostilas = this.acervodao.listarApostilasAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //chama o método que lista todos os textos alugados
    public final void listarTextosAlugados(){        
        try {
            this.acervodao = new ItemAcervoDAO();
            this.listaTextosAlugados = this.acervodao.listarTextosAlugados();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void reservarLivro(int codUsuarioReservante){
        try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.reservarLivros(livros, codUsuarioReservante);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
    }
    
    public void reservarApostila(int codUsuarioReservante){
        try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.reservarApostilas(apostilas, codUsuarioReservante);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
    }
    
    public void reservarTexto(int codUsuarioReservante){
        try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.reservarTextos(textos, codUsuarioReservante);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
    }
    
    public boolean verificarPagamento(int codeUser, String tipoDocumento){
        boolean pago;
        double saldo = acervodao.consultarEstadoDePagamento(codeUser);
        if (saldo == 0){
            pago = true;
        } else {
            pago = false;
        }
        if (null != tipoDocumento)switch (tipoDocumento) {
            case "Livro":
                devolverLivro(pago);
                break;
            case "Apostila":
                devolverApostila(pago);
                break;
            case "Texto":
                devolverTexto(pago);
                break;
            default:
                break;
        }
        return pago;        
    }
    
    public void devolverLivro(boolean pago){        
        try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.devolverLivros(livros, pago);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
    }
    public void devolverApostila(boolean pago){        
        try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.devolverApostilas(apostilas, pago);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
    }
    public void devolverTexto(boolean pago){        
        try {
                this.acervodao = new ItemAcervoDAO();
                boolean alugar = this.acervodao.devolverTextos(textos, pago);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AcervoBean.class.getName()).log(Level.SEVERE, null, ex);
            
            } 
    }
}

