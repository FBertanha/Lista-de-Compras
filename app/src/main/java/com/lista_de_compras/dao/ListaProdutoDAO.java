package com.lista_de_compras.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lista_de_compras.model.Lista;
import com.lista_de_compras.model.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by berta on 11/9/2017.
 */

public class ListaProdutoDAO extends DAO {

    private Context context;
    private Lista lista;

    public ListaProdutoDAO(Context context, Lista lista) {
        super(context);
        this.context = context;
        this.lista = lista;
    }

    public List<Produto> pegarProdutos() {
        List<Produto> produtos;

        String sql = "SELECT * FROM listaProdutos lp" +
                " INNER JOIN  produtos p" +
                " ON lp.produto = p.codigo" +
                " WHERE lista = ? ";
        SQLiteDatabase db = getWritableDatabase();

        String[] selectionArgs = new String[]{String.valueOf(lista.getCodigo())};

        Cursor cursor = db.rawQuery(sql, selectionArgs);

        produtos = getProdutosDoCursor(cursor);
        return produtos;
    }

    private List<Produto> getProdutosDoCursor(Cursor cursor) {
        List<Produto> produtos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Produto produto = new Produto();

            produto.setCodigo(cursor.getInt(cursor.getColumnIndex("codigo")));
            produto.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            produto.setCategoria(new CategoriaDeProdutoDAO(context).pegarPorCodigo(cursor.getInt(cursor.getColumnIndex("categoria"))));
            produto.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));

            produto.setSelecionado(cursor.getInt(cursor.getColumnIndex("selecionado")) > 0);
            //Adiciona produto no ArrayList
            produtos.add(produto);
        }
        return produtos;
    }

    private ContentValues getDadosProduto(Produto produto) {
        ContentValues dados = new ContentValues();
        dados.put("produto", produto.getCodigo());
        dados.put("lista", lista.getCodigo());
        dados.put("selecionado", produto.isSelecionado());
        return dados;
    }

    private void adicionar(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        //Verifica se produto está cadastrado
        if (produto.getCodigo() == null) {
            Long codigoProdutoNovo = new ProdutoDAO(context).adicionar(produto);
            produto.setCodigo(codigoProdutoNovo.intValue());
        }

        ContentValues dadosProduto = getDadosProduto(produto);

        db.insert("listaProdutos", null, dadosProduto);
    }

    public void editar(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dadosProduto = getDadosProduto(produto);

        String[] whereArgs = {String.valueOf(produto.getCodigo()), String.valueOf(lista.getCodigo())};

        db.update("listaProdutos", dadosProduto, "produto = ? and lista = ?", whereArgs);
    }

    public void excluir(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = {String.valueOf(produto.getCodigo()), String.valueOf(lista.getCodigo())};

        db.delete("listaProdutos", "produto = ? and lista = ?", whereArgs);
    }

    public void excluirProdutos() {
        SQLiteDatabase db = getWritableDatabase();

        String[] whereArgs = new String[]{String.valueOf(lista.getCodigo())};

        db.delete("listaProdutos", "lista = ?", whereArgs);

    }

    public void editarProdutos() {
        excluirProdutos();
        adicionarProdutos();
    }

    public void adicionarProdutos() {

        List<Produto> produtosDaLista = lista.getProdutos();

        for (Produto produto : produtosDaLista) {
            adicionar(produto);
        }


    }
}
