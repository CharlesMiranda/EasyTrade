package br.com.interaje.easytrade.model;

import android.content.Context;
import java.util.List;

import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

/**
 * Created by charles on 21/11/15.
 */
public class Carrinho {

    private Long id, produto_id;
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

    public void setProduto(Context context){
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(context));
        ProdutoDAO dao = new ProdutoDAOImpl();

        List<Produto> lista = dao.find(context, produtoDatabase, "select * from " + ProdutoDatabaseHelper.NOME_TABELA + " where " + ProdutoDatabaseHelper.COLUMN_ID + " = " + produto_id);

        for (Produto produtoRetornado : lista) {
            produto = produtoRetornado;
        }

    }

    public Produto getProduto(){
        return produto;
    }
}
