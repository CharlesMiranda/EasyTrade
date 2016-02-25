package br.com.interaje.easytrade.repositories;

import android.content.Context;

import java.util.List;

import br.com.interaje.easytrade.database.CarrinhoDatabase;
import br.com.interaje.easytrade.model.Carrinho;
import br.com.interaje.easytrade.model.Produto;

/**
 * Created by charles on 21/11/15.
 */
public interface CarrinhoDAO {

    boolean salvar(Carrinho carrinho, CarrinhoDatabase carrinhoDatabase);

    List<Carrinho> find(Context context, CarrinhoDatabase carrinhoDatabase, String query);

    boolean remover(Long id, Context context, CarrinhoDatabase carrinhoDatabase);

    Double getTotalItens(Context context, CarrinhoDatabase carrinhoDatabase);
}
