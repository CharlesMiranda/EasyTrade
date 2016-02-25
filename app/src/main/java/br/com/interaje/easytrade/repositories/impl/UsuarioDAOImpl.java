package br.com.interaje.easytrade.repositories.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import br.com.interaje.easytrade.database.UsuarioDatabase;
import br.com.interaje.easytrade.database.UsuarioDatabaseHelper;
import br.com.interaje.easytrade.model.Usuario;
import br.com.interaje.easytrade.repositories.UsuarioDAO;

/**
 * Created by charles on 21/11/15.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

    private String tableDAO = "usuario";
    /**
     * Inserir
     */
    @Override
    public boolean salvar(Usuario usuario, UsuarioDatabase usuarioDatabase) {
        ContentValues cv = new ContentValues();
        usuarioDatabase.open();
        try {
            if (usuario != null) {
                //cv.put(UsuarioDatabaseHelper.COLUMN_ID, usuario.getId());
                cv.put(UsuarioDatabaseHelper.COLUMN_NOME, usuario.getNome());
                cv.put(UsuarioDatabaseHelper.COLUMN_EMAIL, usuario.getEmail());
                cv.put(UsuarioDatabaseHelper.COLUMN_SENHA, usuario.getSenha());

                if(usuario.getId() == 0l) {
                    usuarioDatabase.get().insert(tableDAO, null, cv);
                }else {
                    usuarioDatabase.get().update(tableDAO, cv, UsuarioDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(usuario.getId())});
                }

            }
        } catch (Exception e) {
            Log.d("UsuarioDAOImpl",
                    "Method: salvar().\nErro ao inserir dados do banco. Causa: "
                            + e.getMessage());

            usuarioDatabase.close();
            return false;
        }

        usuarioDatabase.close();
        return true;

    }

    /**
     * Listar todos
     */
    @Override
    public ArrayList<Usuario> find(Context context, UsuarioDatabase usuarioDatabase, String query) {
        Cursor cursor = null;
        usuarioDatabase.open();

        try {
            cursor = usuarioDatabase.get().rawQuery(query, null);
        } catch (Exception e) {

            Log.d("UsuarioDAOImpl",
                    "Method: findall().\nErro ao recuperar dados do banco. Causa: "
                            + e.getMessage());

        }

        ArrayList<Usuario> listEntity = null;
        Usuario entity;

        if (cursor != null && !cursor.isClosed()) {
            listEntity = new ArrayList<Usuario>();

            while (cursor.moveToNext()) {
                entity = new Usuario();
                entity.setId(cursor.getLong(cursor.getColumnIndex(UsuarioDatabaseHelper.COLUMN_ID)));
                entity.setNome(cursor.getString(cursor.getColumnIndex(UsuarioDatabaseHelper.COLUMN_NOME)));
                entity.setEmail(cursor.getString(cursor.getColumnIndex(UsuarioDatabaseHelper.COLUMN_EMAIL)));
                entity.setSenha(cursor.getString(cursor.getColumnIndex(UsuarioDatabaseHelper.COLUMN_SENHA)));

                listEntity.add(entity);
            }
            cursor.close();
        }

        usuarioDatabase.close();
        return listEntity;
    }

    /**
     * Remover
     */
    @Override
    public boolean remover(Long id, Context context, UsuarioDatabase usuarioDatabase) {
        try {
            usuarioDatabase.open();
            // (TABELA, COLUNA, WHERE CLAUSE)
            usuarioDatabase.get().delete(tableDAO, UsuarioDatabaseHelper.COLUMN_ID + "=?",
                    new String[]{String.valueOf(id)});
        } catch (Exception e) {
            Log.d("UsuarioDAOImpl",
                    "Method: remover().\nErro ao remover dados do banco. Causa: "
                            + e.getMessage());

            usuarioDatabase.close();
            return false;
        }

        usuarioDatabase.close();
        return true;
    }

}    