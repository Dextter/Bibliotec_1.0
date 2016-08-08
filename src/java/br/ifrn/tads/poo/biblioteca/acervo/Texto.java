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
public class Texto extends ItemAcervo{                    
    private String autor;                 
    private double custoTexto;
    private int code, codeReservante;
    private Date dataA;
    private Date dataD;
    private boolean textoPago;
    private Boolean alugado;
    private Boolean reservado;    

    public Texto(double custo, int codigoItem, boolean pago, Date dataAluguel, Date dataDevolucao) {
        super(custo, codigoItem, pago, dataAluguel, dataDevolucao);
    }
    
    public Texto() {
        this.custo = custoTexto;
        this.codigoItem = code;
        this.dataAluguel = dataA;
        this.dataDevolucao = dataD;
        this.pago = textoPago;
    }
    
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public double getCustoTexto() {
        return custoTexto;
    }

    public void setCustoTexto(double custoTexto) {
        this.custoTexto = custoTexto;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCodeReservante() {
        return codeReservante;
    }

    public void setCodeReservante(int codeReservante) {
        this.codeReservante = codeReservante;
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

    public boolean isTextoPago() {
        return textoPago;
    }

    public void setTextoPago(boolean textoPago) {
        this.textoPago = textoPago;
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
        return true;
    }

    @Override
    public void reservar() {
        
    }
}
