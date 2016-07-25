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
public abstract class ItemAcervo {
    double custo;
    int codigoItem;
    boolean pago;
    Date dataAluguel, dataDevolucao;

    public ItemAcervo(double custo, int codigoItem, boolean pago, Date dataAluguel, Date dataDevolucao) {
        this.custo = custo;
        this.codigoItem = codigoItem;
        this.pago = pago;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
    }
    
    public ItemAcervo(){}
    
    public abstract void alugar();
        
    public abstract double devolver();
    
    public abstract boolean estaPago();
    
    public abstract void reservar();
    
}