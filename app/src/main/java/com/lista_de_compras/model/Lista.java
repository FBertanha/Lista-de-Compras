package com.lista_de_compras.model;

import java.util.Date;
import java.util.List;

/**
 * Created by evely on 30/08/2017.
 */

public class Lista {
    private int codigo;
    private CategoriaDeLista categoria;
    private List<Produto> produtos;
    private String nome;
    private Date dataCriacao;
    private Date dataCompra;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public CategoriaDeLista getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDeLista categoria) {
        this.categoria = categoria;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }
}
