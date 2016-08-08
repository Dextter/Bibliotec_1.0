/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifrn.tads.poo.biblioteca.acervo;

import java.sql.Date;


/**
 *
 * @author pablopc
 */
public class Livro extends ItemAcervo{
    private String data;
    private String autor;
    private String isbn;    
    private String titulo;    
    private Integer edicao;  
    private double custoLivro;
    private int code, codeReservante;
    private Date dataA;
    private Date dataD;
    private boolean livroPago;
    private Boolean alugado;
    private Boolean reservado;
    

    public Livro(double custo, int codigoItem, boolean pago, Date dataAluguel, Date dataDevolucao) {
        super(custo, codigoItem, pago, dataAluguel, dataDevolucao);
    }

    public Livro() {
        this.custo = custoLivro;
        this.codigoItem = code;
        this.dataAluguel = dataA;
        this.dataDevolucao = dataD;
        this.pago = livroPago;
    }
    
    public Integer getEdicao() {
        return edicao;
    }

    public void setEdicao(Integer edicao) {
        this.edicao = edicao;
    }        

    public String getData() {
        return data;
    }

    public void setData(String data) {
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

    public double getCustoLivro() {
        return custoLivro;
    }

    public void setCustoLivro(double custoLivro) {
        this.custoLivro = custoLivro;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getDataA() {
        return dataA;
    }

    public void setDataA(Date dataA) {
        this.dataA = dataA;
    }

    public Date getDataD() {
        return dataD;
    }

    public void setDataD(Date dataD) {
        this.dataD = dataD;
    }

    public boolean isLivroPago() {
        return livroPago;
    }

    public void setLivroPago(boolean livroPago) {
        this.livroPago = livroPago;
    }     

    public int getCodeReservante() {
        return codeReservante;
    }

    public void setCodeReservante(int codeReservante) {
        this.codeReservante = codeReservante;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }

    public Boolean getReservado() {
        return reservado;
    }

    public void setReservado(Boolean reservado) {
        this.reservado = reservado;
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