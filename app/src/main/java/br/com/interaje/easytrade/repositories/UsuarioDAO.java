package br.com.interaje.easytrade.repositories;

import android.content.Context;

import java.util.List;

import br.com.interaje.easytrade.database.UsuarioDatabase;
import br.com.interaje.easytrade.model.Usuario;

/**
 * Created by charles on 21/11/15.
 */
public interface UsuarioDAO {

    boolean salvar(Usuario usuario, UsuarioDatabase usuarioDatabase);

    List<Usuario> find(Context context, UsuarioDatabase usuarioDatabase, String query);

    boolean remover(Long id, Context context, UsuarioDatabase usuarioDatabase);
}
