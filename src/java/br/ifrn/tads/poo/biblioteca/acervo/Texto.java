/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrn.tads.poo.biblioteca.acervo;

import java.util.Date;

/**
 *
 * @author pablopc
 */
public class Texto extends ItemAcervo{
    String autor, quantidade;
    boolean alugado;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }   
    
    public Texto(){}

    @Override
    public void alugar() {
        
    }
    
    @Override
    public double devolver() {
        return 0;
    }

    @Override
    public boolean estaPago() {
        return true;
    }

    @Override
    public void reservar() {
        
    }
}
