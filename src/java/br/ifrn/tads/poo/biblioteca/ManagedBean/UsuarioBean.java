/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.tads.poo.biblioteca.ManagedBean;

import br.ifrn.tads.poo.biblioteca.DAO.UsuarioDAO;
import br.ifrn.tads.poo.biblioteca.usuario.Usuario;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author pablopc
 */
@ManagedBean
public class UsuarioBean {
    private Usuario usuario;
    private UsuarioDAO usuarioDao;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UsuarioDAO getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
    
    public int recuperarCodigo (){        
        return usuario.getCodUsuario();
    }
    
}
