/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifrn.tads.poo.biblioteca.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author pablopc
 */

//faz a conexão com o banco de dados
public class ConexaoBd {
    private String driver = "org.postgresql.Driver";
     private String url = "jdbc:postgresql://localhost/bibliotec", user = "postgres", password = "projetopoo";
     public Connection getConnection() throws ClassNotFoundException{
         try{
             Class.forName(driver);
             return DriverManager.getConnection(url,user,password);
         }catch(SQLException e){
             throw new RuntimeException(e);
         }
     }
}