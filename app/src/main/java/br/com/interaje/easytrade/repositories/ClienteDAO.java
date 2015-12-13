package br.com.interaje.easytrade.repositories;

import android.content.Context;

import java.util.List;

import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.model.Cliente;

/**
 * Created by charles on 21/11/15.
 */
public interface ClienteDAO {

    boolean salvar(Cliente cliente, ClienteDatabase clienteDatabase);

    List<Cliente> find(Context context, ClienteDatabase clienteDatabase, String query);

    boolean remover(Long id, Context context, ClienteDatabase clienteDatabase);
}
