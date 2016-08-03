/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.tads.poo.biblioteca.ManagedBean;

import br.ifrn.tads.poo.biblioteca.DAO.AdministradorDAO;
import br.ifrn.tads.poo.biblioteca.usuario.Administrador;
import java.io.IOException;
//import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author pablopc
 */
@ManagedBean
public class AdministradorBean {

    private Administrador bibliotecario;
    private AdministradorDAO bibliotecariodao;
    private List<Administrador> listaSolicitacoes;

    public List<Administrador> getListaSolicitacoes() {
        return listaSolicitacoes;
    }

    public void setListaSolicitacoes(List<Administrador> listaSolicitacoes) {
        this.listaSolicitacoes = listaSolicitacoes;
    }
     
    public AdministradorBean() {
        this.bibliotecario = new Administrador();        
    }

    public Administrador getBibliotecario() {
        return bibliotecario;
    }

    public void setBibliotecario(Administrador bibliotecario) {
        this.bibliotecario = bibliotecario;
    }

    public AdministradorDAO getBibliotecariodao() {
        return bibliotecariodao;
    }

    public void setBibliotecariodao(AdministradorDAO bibliotecariodao) {
        this.bibliotecariodao = bibliotecariodao;
    }
    
    
    //chama o método que faz o login
    public String Logar(){        
        try{            
            this.bibliotecariodao = new AdministradorDAO();
            this.bibliotecario = this.bibliotecariodao.getBibliotecario(this.bibliotecario);
            try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } catch (IOException ex) {
                    Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
                }                      
    }catch(ClassNotFoundException ex){
             Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        FacesMessage message = new FacesMessage("Usuario ou Senha Invalidos!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
        return "index";
    }
    
    public void cadastro2(){
        try {
            this.bibliotecariodao = new AdministradorDAO();
            boolean chamasolicitacao = this.bibliotecariodao.ativar(bibliotecario);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }this.bibliotecario = new Administrador();
 
    }
    public void redirecionar(){        
        try {            
            FacesContext.getCurrentInstance().getExternalContext().redirect("cadastro.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //chama o método que cadastra o administrador
    public void cadasrarAdministrador(){         
        try {
            this.bibliotecariodao = new AdministradorDAO();
            boolean chamacadas = this.bibliotecariodao.cadastrarAdministrador(this.bibliotecario);                                                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }this.bibliotecario = new Administrador();
       }
    public void ativar(Administrador ativa){        
        try {
            this.bibliotecariodao = new AdministradorDAO();
           boolean corrige = this.bibliotecariodao.ativar(ativa);
           if(corrige){            
            this.bibliotecario = new Administrador();
            this.bibliotecariodao = new AdministradorDAO();
           }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void clicarBotaoCadastraUsuario() {
        addMessage("Sucesso!", "Usuário cadastrado com sucesso.");
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     public void clicarBotaoPesquisa() {
         addMessage("Sucesso!", "Usuario encontrado");
    }
     //chama o método que seleciona o administrador
      public void selecionarUsuario(){          
          try{                           
              this.bibliotecariodao = new AdministradorDAO();
              this.bibliotecario = this.bibliotecariodao.selecionarUsuario(this.bibliotecario);
              if(bibliotecario != null){
                  System.out.println("entrou no if");
                FacesMessage mensagem = new FacesMessage("Usuário encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
              }
              else{
                FacesMessage mensagem = new FacesMessage("Usuário não encontrado"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
              }
          }catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      
      //chama o método que desativa um administrador
      public void desativarLogin(){                
        try {
            this.bibliotecariodao = new AdministradorDAO();
            boolean excluirLogin = this.bibliotecariodao.excluriLogin(this.bibliotecario);           
            if(excluirLogin){                
                FacesMessage mensagem = new FacesMessage("Usuario excluído"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }else{
                FacesMessage mensagem = new FacesMessage("Falha na exclusão"); 
                FacesContext.getCurrentInstance().addMessage(null, mensagem);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bibliotecario = new Administrador();
    }
      
      //esse é o método que saí da aplicação
      public void Logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.invalidate();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(AdministradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}