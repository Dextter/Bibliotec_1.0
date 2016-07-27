/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifrn.tads.poo.biblioteca.DAO;

import br.ifrn.tads.poo.biblioteca.conexao.ConexaoBd;
import br.ifrn.tads.poo.biblioteca.acervo.Livro;
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
    //faz o cadastro de formulário de normalização
    public boolean cadastrarLivro(Livro livro){
        String bd = "INSERT INTO formulario(autor, isbn, titulo, edicao, data, alugado,"                
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
  
    //lista todos itens não alugados em ordem decrescente
    public List<Livro> listaItens(){
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
    //lista todos os livros alugados
    public List<Livro> listarLivrosAlugados(){
        String sqls = "SELECT * FROM acervo WHERE alugado = ? ";
         
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
}