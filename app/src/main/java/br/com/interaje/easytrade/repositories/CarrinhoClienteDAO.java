package br.com.interaje.easytrade.repositories;

import android.content.Context;

import java.util.List;

import br.com.interaje.easytrade.database.CarrinhoClienteDatabase;
import br.com.interaje.easytrade.model.CarrinhoCliente;

/**
 * Created by charles on 21/11/15.
 */
public interface CarrinhoClienteDAO {

    boolean salvar(CarrinhoCliente carrinhoCliente, CarrinhoClienteDatabase carrinhoClienteDatabase);

    List<CarrinhoCliente> find(Context context, CarrinhoClienteDatabase carrinhoClienteDatabase, String query);

    boolean remover(Long id, Context context, CarrinhoClienteDatabase carrinhoClienteDatabase);

    void removerAll(Context context, CarrinhoClienteDatabase carrinhoClienteDatabase);
}
