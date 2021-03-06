package com.lista_de_compras.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by evely on 18/10/2017.
 */

public class DAO extends SQLiteOpenHelper {

    private final Context context;

    public DAO(Context context) {
        super(context, "lista_de_compras", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        //Produtos
        db.execSQL("CREATE TABLE produtos (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "descricao VARCHAR(255) NOT NULL," +
                "categoria INT DEFAULT 1," +
                "valor DOUBLE DEFAULT 0);"
        );
        //Categoria de Produto
        db.execSQL("CREATE TABLE categoriaDeProduto(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR (255) NOT NULL)"
        );
        //Listas
        db.execSQL("CREATE TABLE listas (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "categoria INT DEFAULT 1," +
                //"produtos INT  NOT NULL," +
                "nome VARCHAR(255) NOT NULL," +
                "dataCriacao DATE ," +
                "dataCompra DATE );"
        );
        //Categoria de Lista
        db.execSQL("CREATE TABLE CategoriaDeLista(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(255) NOT NULL)"
        );
        //Lista-Produtos
        db.execSQL("CREATE TABLE listaProdutos (" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "produto INT NOT NULL," +
                "lista INT NOT NULL," +
                "selecionado INT DEFAULT 0);"
        );

        incluirRegistrosPadroes(db);
    }

    private void incluirRegistrosPadroes(SQLiteDatabase db) {

        db.execSQL("INSERT INTO categoriaDeProduto (codigo, nome) VALUES " +
                "(1, 'Sem categoria'), " +
                "(2, 'Açougue'), " +
                "(3, 'Bebidas')," +
                "(4, 'Carnes')," +
                "(5, 'Frios')," +
                "(6, 'Higiene')," +
                "(7, 'Horti Fruti')," +
                "(8, 'Limpeza')," +
                "(9, 'Padaria')");

        db.execSQL("INSERT INTO CategoriaDeLista (codigo, nome) VALUES " +
                "(1, 'Sem categoria'), " +
                "(2, 'Aniversário'), " +
                "(3, 'Compras')," +
                "(4, 'Festa')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
