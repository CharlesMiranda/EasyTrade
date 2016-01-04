package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.CarrinhoClienteDatabase;
import br.com.interaje.easytrade.database.CarrinhoClienteDatabaseHelper;
import br.com.interaje.easytrade.model.CarrinhoCliente;
import br.com.interaje.easytrade.repositories.CarrinhoClienteDAO;

/**
 * Created by charles on 21/11/15.
 */
public class CarrinhoClienteDAOImpl implements CarrinhoClienteDAO {

    private String tableDAO = "carrinho_cliente";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(CarrinhoCliente carrinhoCliente, CarrinhoClienteDatabase carrinhoClienteDatabase) {
        ContentValues cv = new ContentValues();
        carrinhoClienteDatabase.open();
        try {
            if (carrinhoCliente != null) {
                cv.put(CarrinhoClienteDatabaseHelper.COLUMN_CLIENTE, carrinhoCliente.getCliente_id());
                carrinhoClienteDatabase.get().insert(tableDAO, null, cv);

            }
        } catch (Exception e) {
            Log.d("CarrinhoDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getCause());

            carrinhoClienteDatabase.close();
            return false;
        }

        carrinhoClienteDatabase.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<CarrinhoCliente> find(Context context, CarrinhoClienteDatabase carrinhoClienteDatabase, String query) {
        Cursor cursor = null;
        carrinhoClienteDatabase.open();

        try {
            cursor = carrinhoClienteDatabase.get().rawQuery(query, null);
        } catch (Exception e) {

            Log.d("CarrinhoDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }

        ArrayList<CarrinhoCliente> listEntity = null;
        CarrinhoCliente entity;

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<CarrinhoCliente>();

            while (cursor.moveToNext()) {
                entity = new CarrinhoCliente();
                entity.setId(cursor.getLong(cursor.getColumnIndex(CarrinhoClienteDatabaseHelper.COLUMN_ID)));
                entity.setCliente_id(cursor.getLong(cursor.getColumnIndex(CarrinhoClienteDatabaseHelper.COLUMN_CLIENTE)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        carrinhoClienteDatabase.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public boolean remover(Long id, Context context, CarrinhoClienteDatabase carrinhoClienteDatabase) {
        try {
            carrinhoClienteDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            carrinhoClienteDatabase.get().delete(tableDAO, CarrinhoClienteDatabaseHelper.COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("CarrinhoDAOImpl",
                    "Method: remover().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());

            carrinhoClienteDatabase.close();
            return false;
        }

        carrinhoClienteDatabase.close();
        return true;
    }

    /**
     * Remover all
     */
    @Override
    public void removerAll(Context context, CarrinhoClienteDatabase carrinhoClienteDatabase) {
        try {
            carrinhoClienteDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            carrinhoClienteDatabase.get().rawQuery("delete * from " + tableDAO, null);
        } catch (Exception e) {
            Log.d("CarrinhoDAOImpl",
                    "Method: remover().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());

            carrinhoClienteDatabase.close();
        }

        carrinhoClienteDatabase.close();
    }

}    