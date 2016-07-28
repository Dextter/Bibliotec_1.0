/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifrn.tads.poo.biblioteca.DAO;

import br.ifrn.tads.poo.biblioteca.acervo.Apostila;
import br.ifrn.tads.poo.biblioteca.conexao.ConexaoBd;
import br.ifrn.tads.poo.biblioteca.acervo.Livro;
import br.ifrn.tads.poo.biblioteca.acervo.Texto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pablopc
 */
public class ItemAcervoDAO {
    private Connection conexaobd;
    private Livro livro;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    public ItemAcervoDAO()throws ClassNotFoundException{
        this.conexaobd = new ConexaoBd().getConnection();      
    }
    //faz o cadastro de um livro
    public boolean cadastrarLivro(Livro livro){
        String bd = "INSERT INTO livro(autor, isbn, titulo, edicao, data, alugado,"                
                + "VALUES(?,?,?,?,?,?)";        
        try(PreparedStatement ps = conexaobd.prepareStatement(bd)){
            ps.setString(1,livro.getAutor());
            ps.setString(2,livro.getIsbn());            
            ps.setString(3,livro.getTitulo());
            ps.setInt(4,livro.getEdicao());            
            ps.setDate(5, new Date(livro.getData().getTime()));
            ps.setString(6, "nao");            
          int testar  = ps.executeUpdate();
        if(testar == 1){
        return true;                   
        }
        } catch(SQLException e){
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,e);
    }finally{
            if(conexaobd!=null){
                try{
                    conexaobd.close();
                }catch(SQLException ex){
                     Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
  
//faz o cadastro de um livro
    public boolean cadastrarApostila(Apostila apostila){
        String bd = "INSERT INTO livro(autor, titulo, alugado,"                
                + "VALUES(?,?,?)";        
        try(PreparedStatement ps = conexaobd.prepareStatement(bd)){
            ps.setString(1,apostila.getAutor());
            ps.setString(2,apostila.getTitulo());            
            ps.setString(3, "nao");            
          int testar  = ps.executeUpdate();
        if(testar == 1){
        return true;                   
        }
        } catch(SQLException e){
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,e);
    }finally{
            if(conexaobd!=null){
                try{
                    conexaobd.close();
                }catch(SQLException ex){
                     Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
  
    //faz o cadastro de um texto
    public boolean cadastrarTexto(Texto txt){
        String bd = "INSERT INTO texto(autor, alugado,"                
                + "VALUES(?,?)";        
        try(PreparedStatement ps = conexaobd.prepareStatement(bd)){
            ps.setString(1,txt.getAutor());            
            ps.setString(2, "nao");            
          int testar  = ps.executeUpdate();
        if(testar == 1){
        return true;                   
        }
        } catch(SQLException e){
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,e);
    }finally{
            if(conexaobd!=null){
                try{
                    conexaobd.close();
                }catch(SQLException ex){
                     Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
  
    
//lista todos livros não alugados em ordem decrescente
    public List<Livro> listarLivros(){
        String sqls = "SELECT * FROM livro WHERE alugado = ?  ORDER BY titulo_livro DESC ";
         
        List<Livro> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "nao");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Livro livros = new Livro();
                        livros.setIsbn(result.getString("isbn_livro"));
                        livros.setAutor(result.getString("autor_livro"));                        
                        livros.setTitulo(result.getString("titulo_livro"));                        
                        livros.setEdicao(result.getInt("edicao_livro"));                        
                        //livros.setEdicao(result.getInt("edicao"));                    
                    lista.add(livros);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    //lista todas as apostilas não alugadas em ordem decrescente
    public List<Apostila> listarApostilas(){
        String sqls = "SELECT * FROM apostila WHERE alugado = ?  ORDER BY titulo_livro DESC ";
         
        List<Apostila> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "nao");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Apostila apostilas = new Apostila();                        
                        apostilas.setAutor(result.getString("autor_livro"));                        
                        apostilas.setTitulo(result.getString("titulo_livro"));                                                                        
                    lista.add(apostilas);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    //lista todos os textos não alugados em ordem decrescente
    public List<Texto> listarTextos(){
        String sqls = "SELECT * FROM texto WHERE alugado = ?  ORDER BY titulo_livro DESC ";
         
        List<Texto> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "nao");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Texto textos = new Texto();                        
                        textos.setAutor(result.getString("autor_livro"));                                                                        
                    lista.add(textos);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    //lista todos os livros alugados
    public List<Livro> listarLivrosAlugados(){
        String sqls = "SELECT * FROM livro WHERE alugado = ? ";
         
        List<Livro> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "sim");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Livro livros = new Livro();
                        livros.setAutor(result.getString("nome"));
                        livros.setIsbn(result.getString("matricula"));                       
                        livros.setTitulo(result.getString("titulo_proj"));       
                        livros.setEdicao(result.getInt("edicao"));
                        livros.setData(result.getDate("data_solicitacao"));                                      
                    lista.add(livros);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }    
    
    //lista todas as apostilas alugadas
    public List<Apostila> listarApostilasAlugadas(){
        String sqls = "SELECT * FROM apostila WHERE alugado = ? ";
         
        List<Apostila> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "sim");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Apostila apostilas = new Apostila();
                        apostilas.setAutor(result.getString("nome"));                       
                        apostilas.setTitulo(result.getString("titulo_proj"));                                                                  
                    lista.add(apostilas);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }    
    
    //lista todos os textos alugados
    public List<Texto> listarTextosAlugados(){
        String sqls = "SELECT * FROM texto WHERE alugado = ? ";
         
        List<Texto> lista = new ArrayList<>();
        try (PreparedStatement pst = conexaobd.prepareStatement(sqls)){
            pst.setString(1, "sim");
            ResultSet result = pst.executeQuery();
                while(result.next()){
                    Texto textos = new Texto();
                        textos.setAutor(result.getString("nome"));                                              
                    lista.add(textos);                    
                }
                return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(conexaobd != null){
                try {
                    conexaobd.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(ItemAcervoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }    
}