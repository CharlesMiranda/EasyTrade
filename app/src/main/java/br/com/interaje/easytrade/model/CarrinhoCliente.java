package br.com.interaje.easytrade.model;

import android.content.Context;

import java.util.List;

import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.repositories.ClienteDAO;
import br.com.interaje.easytrade.repositories.impl.ClienteDAOImpl;

/**
 * Created by charles on 21/11/15.
 */
public class CarrinhoCliente {

    private Long id, cliente_id;
    private Cliente cliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(Long cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setCliente(Context context){
        ClienteDatabase clienteDatabase = new ClienteDatabase(new ClienteDatabaseHelper(context));
        ClienteDAO dao = new ClienteDAOImpl();

        List<Cliente> lista = dao.find(context, clienteDatabase, "select * from " + ClienteDatabaseHelper.NOME_TABELA + " where " + ClienteDatabaseHelper.COLUMN_ID + " = " + cliente_id);

        for (Cliente clienteRetornado : lista) {
            cliente = clienteRetornado;
        }

    }

    public Cliente getCliente(){
        return cliente;
    }
}
