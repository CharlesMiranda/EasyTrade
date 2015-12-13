package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.CarrinhoDatabase;
import br.com.interaje.easytrade.database.CarrinhoDatabaseHelper;
import br.com.interaje.easytrade.model.Carrinho;
import br.com.interaje.easytrade.repositories.CarrinhoDAO;

/**
 * Created by charles on 21/11/15.
 */
public class CarrinhoDAOImpl implements CarrinhoDAO {

    private String tableDAO = "carrinho";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(Carrinho carrinho, CarrinhoDatabase carrinhoDatabase) {
        ContentValues cv = new ContentValues();
        carrinhoDatabase.open();
        try {
            if (carrinho != null) {
                cv.put(CarrinhoDatabaseHelper.COLUMN_PRODUTO, carrinho.getProduto_id());
                carrinhoDatabase.get().insert(tableDAO, null, cv);

            }
        } catch (Exception e) {
            Log.d("CarrinhoDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getCause());

            carrinhoDatabase.close();
            return false;
        }

        carrinhoDatabase.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<Carrinho> find(Context context, CarrinhoDatabase carrinhoDatabase, String query) {
        Cursor cursor = null;
        carrinhoDatabase.open();

        try {
            cursor = carrinhoDatabase.get().rawQuery(query, null);
        } catch (Exception e) {

            Log.d("CarrinhoDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }

        ArrayList<Carrinho> listEntity = null;
        Carrinho entity;

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Carrinho>();

            while (cursor.moveToNext()) {
                entity = new Carrinho();
                entity.setId(cursor.getLong(cursor.getColumnIndex(CarrinhoDatabaseHelper.COLUMN_ID)));
                entity.setProduto_id(cursor.getLong(cursor.getColumnIndex(CarrinhoDatabaseHelper.COLUMN_PRODUTO)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        carrinhoDatabase.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public boolean remover(Long id, Context context, CarrinhoDatabase carrinhoDatabase) {
        try {
            carrinhoDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            carrinhoDatabase.get().delete(tableDAO, CarrinhoDatabaseHelper.COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("CarrinhoDAOImpl",
                    "Method: remover().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());

            carrinhoDatabase.close();
            return false;
        }

        carrinhoDatabase.close();
        return true;
    }

}    