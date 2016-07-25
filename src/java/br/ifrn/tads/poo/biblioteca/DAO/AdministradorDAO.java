/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.tads.poo.biblioteca.DAO;



import br.ifrn.tads.poo.biblioteca.usuario.Administrador;
import br.ifrn.tads.poo.biblioteca.conexao.ConexaoBd;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

/**
 *
 * @author pablopc
 */

public class AdministradorDAO {
private Connection cone; 
private Administrador adm;
    public AdministradorDAO() throws ClassNotFoundException{
        //chama a classe de conexão com o banco de dados
        this.cone =  new ConexaoBd().getConnection();        
        adm = new Administrador();
    }
    //faz o login
     public Administrador getBibliotecario(Administrador bibliotecarios){         
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
        try (PreparedStatement ps = cone.prepareStatement(sql)) {
            ps.setInt(1, bibliotecarios.getCodUsuario());
            ps.setString(2, bibliotecarios.getSenha());
            ResultSet result = ps.executeQuery();
                if(result.next()){
                    Administrador usuarioLogado = new Administrador();
                    usuarioLogado.setCodUsuario(bibliotecarios.getCodUsuario());
                    usuarioLogado.setSenha(bibliotecarios.getSenha());                                        
                    return usuarioLogado;  
                }
        }catch(SQLException e){
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            if(cone != null){
                try {
                    cone.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }                
            }
        }
        return null;
    }
    public boolean cadastrarAdministrador(Administrador bibliotecario){
        String bd = "INSERT INTO usuario (nome, codUsuario, cpf, endereco, senha, ativo)"
                + "VALUES(?,?,?,?,?,?)";
        try(PreparedStatement ps = cone.prepareStatement(bd)){
            ps.setString(1,bibliotecario.getNome());
            ps.setInt(2,bibliotecario.getCodUsuario());
            ps.setString(3,bibliotecario.getCpf());
            ps.setString(4,bibliotecario.getEndereco());
            ps.setString(5,bibliotecario.getSenha());   
            ps.setString(6,bibliotecario.getAtivo());
            int testar  = ps.executeUpdate();
            if(testar == 1){
                return true;                   
            }
        } catch(SQLException e){
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE,null,e);
        }finally{
            if(cone!=null){
                try{
                    cone.close();
                }catch(SQLException ex){
                     Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE,null,ex);
                }                
            }
        }
            return false;
     }
          
    
     public boolean ativar(Administrador bibliotecario){         
         String sql = "UPDATE usuario SET ativo = ? WHERE codusuario = ?";
         try(PreparedStatement ps = cone.prepareStatement(sql)){
            ps.setString(1, "sim");
            ps.setInt(2, bibliotecario.getCodUsuario());            
         }catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
                    if(cone != null){
                        try {
                            cone.close();                            
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }
    
    //seleciona um administrador ou bibliotecário 
    public Administrador selecionarUsuario(Administrador bibliotecario){
        String sqls = "SELECT usuario SET ativo = ? WHERE usuario = ?";         
        try (PreparedStatement pst = cone.prepareStatement(sqls)){             
            pst.setString(1,"nao" );
            pst.setInt(2, bibliotecario.getCodUsuario());
            ResultSet result = pst.executeQuery();
                if(result.next()){                    
                    Administrador usuarioEncontrado =  new Administrador();
                    usuarioEncontrado.setNome(result.getString("nome"));
                    usuarioEncontrado.setCodUsuario(result.getInt("codusuario"));
                    usuarioEncontrado.setSenha(result.getString("senha"));                                                                                                                        
                    return usuarioEncontrado;
                }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(cone != null){
                try {
                    cone.close();                    
                } catch (SQLException ex) {
                    Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
       
        return null;
    }
    
    //invalida um administrador
    public boolean excluriLogin(Administrador bibliotecario){
        String sql = "UPDATE login SET ativo = ?  WHERE codusuario = ?";                    
            try (PreparedStatement ps = cone.prepareStatement(sql)){
                ps.setString(1, "nao");
                ps.setInt(2, bibliotecario.getCodUsuario());                  
                int retorno = ps.executeUpdate();                    
                        if(retorno == 1){                            
                            return true;
                        }
            } catch (SQLException ex) {
                        Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
            }finally{
                    if(cone != null){
                        try {
                            cone.close();             
                        } catch (SQLException ex) {
                            Logger.getLogger(AdministradorDAO.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
       
        return false;
    }            
}