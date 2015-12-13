package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.repositories.ClienteDAO;

/**
 * Created by charles on 21/11/15.
 */
public class ClienteDAOImpl implements ClienteDAO {

    private String tableDAO = "cliente";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(Cliente cliente, ClienteDatabase clienteDatabase) {
        ContentValues cv = new ContentValues();
        clienteDatabase.open();
        try {
            if (cliente != null) {
                //cv.put(ClienteDatabaseHelper.COLUMN_ID, cliente.getId());
                cv.put(ClienteDatabaseHelper.COLUMN_NOME, cliente.getNome());
                cv.put(ClienteDatabaseHelper.COLUMN_TELEFONE, cliente.getTelefone());
                cv.put(ClienteDatabaseHelper.COLUMN_EMAIL, cliente.getEmail());
                cv.put(ClienteDatabaseHelper.COLUMN_OBSERVACAO, cliente.getObservacao());
                cv.put(ClienteDatabaseHelper.COLUMN_FOTO, cliente.getFoto());

                if(cliente.getId() == 0l) {
                    clienteDatabase.get().insert(tableDAO, null, cv);
                }else {
                    clienteDatabase.get().update(tableDAO, cv, ClienteDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(cliente.getId())});
                }

            }
        } catch (Exception e) {
            Log.d("ClienteDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getMessage());

            clienteDatabase.close();
            return false;
        }

        clienteDatabase.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<Cliente> find(Context context, ClienteDatabase clienteDatabase, String query) {
        Cursor cursor = null;
        clienteDatabase.open();

        try {
            cursor = clienteDatabase.get().rawQuery(query, null);
        } catch (Exception e) {

            Log.d("ClienteDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }

        ArrayList<Cliente> listEntity = null;
        Cliente entity;

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Cliente>();

            while (cursor.moveToNext()) {
                entity = new Cliente();
                entity.setId(cursor.getLong(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_ID)));
                entity.setNome(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_NOME)));
                entity.setTelefone(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_TELEFONE)));
                entity.setEmail(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_EMAIL)));
                entity.setObservacao(cursor.getString(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_OBSERVACAO)));
                entity.setFoto(cursor.getBlob(cursor.getColumnIndex(ClienteDatabaseHelper.COLUMN_FOTO)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        clienteDatabase.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public boolean remover(Long id, Context context, ClienteDatabase clienteDatabase) {
        try {
            clienteDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            clienteDatabase.get().delete(tableDAO, ClienteDatabaseHelper.COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("ClienteDAOImpl",
                    "Method: remover().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());

            clienteDatabase.close();
            return false;
        }

        clienteDatabase.close();
        return true;
    }

}    