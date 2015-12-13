package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;

/**
 * Created by charles on 21/11/15.
 */
public class ProdutoDAOImpl implements ProdutoDAO {

    private String tableDAO = "produto";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(Produto produto, ProdutoDatabase produtoDatabase) {
        ContentValues cv = new ContentValues();
        produtoDatabase.open();
        try {
            if (produto != null) {
                //cv.put(ProdutoDatabaseHelper.COLUMN_ID, produto.getId());
                cv.put(ProdutoDatabaseHelper.COLUMN_NOME, produto.getNome());
                cv.put(ProdutoDatabaseHelper.COLUMN_VALOR, produto.getValor());
                cv.put(ProdutoDatabaseHelper.COLUMN_QUANTIDADE, produto.getQuantidade());
                cv.put(ProdutoDatabaseHelper.COLUMN_DESCRICAO, produto.getDescricao());
                cv.put(ProdutoDatabaseHelper.COLUMN_FOTO, produto.getFoto());

                if(produto.getId() == 0l) {
                    produtoDatabase.get().insert(tableDAO, null, cv);
                }else {
                    produtoDatabase.get().update(tableDAO, cv, ProdutoDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(produto.getId())});
                }

            }
        } catch (Exception e) {
            Log.d("ProdutoDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getMessage());

            produtoDatabase.close();
            return false;
        }

        produtoDatabase.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<Produto> find(Context context, ProdutoDatabase produtoDatabase, String query) {
        Cursor cursor = null;
        produtoDatabase.open();

        try {
            cursor = produtoDatabase.get().rawQuery(query, null);
        } catch (Exception e) {

            Log.d("ProdutoDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }

        ArrayList<Produto> listEntity = null;
        Produto entity;

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Produto>();

            while (cursor.moveToNext()) {
                entity = new Produto();
                entity.setId(cursor.getLong(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_ID)));
                entity.setNome(cursor.getString(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_NOME)));
                entity.setValor(cursor.getDouble(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_VALOR)));
                entity.setQuantidade(cursor.getInt(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_QUANTIDADE)));
                entity.setDescricao(cursor.getString(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_DESCRICAO)));
                entity.setFoto(cursor.getBlob(cursor.getColumnIndex(ProdutoDatabaseHelper.COLUMN_FOTO)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        produtoDatabase.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public boolean remover(Long id, Context context, ProdutoDatabase produtoDatabase) {
        try {
            produtoDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            produtoDatabase.get().delete(tableDAO, ProdutoDatabaseHelper.COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("ProdutoDAOImpl",
                    "Method: remover().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());

            produtoDatabase.close();
            return false;
        }

        produtoDatabase.close();
        return true;
    }

}    