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
public class Livro extends ItemAcervo{
    private Date data = new Date();
    private String autor;
    private String isbn;    
    private String titulo;    
    private Integer edicao;    
   
    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }        

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public void alugar() {
        
    }

    @Override
    public double devolver() {
        return 0;
    }

    @Override
    public boolean estaPago() {
        return false;
    }

    @Override
    public void reservar() {
        
    }

}