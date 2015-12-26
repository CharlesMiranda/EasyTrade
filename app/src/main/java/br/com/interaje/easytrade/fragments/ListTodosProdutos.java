package br.com.interaje.easytrade.fragments;

/**
 * Created by charles on 10/12/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.activites.DetailProdutoPedido;
import br.com.interaje.easytrade.adapter.ProdutoAdapter;
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class ListTodosProdutos extends Fragment {

    private ListView lista_todos_produtos;
    private ProdutoAdapter lista_todos_adapter;
    private View convertView = null;
    private Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = inflater.getContext();
        convertView =  inflater.inflate(R.layout.fragment_list_todos_produtos, container, false);
        this.inicializarElementos();
        return convertView;

    }


    private void inicializarElementos(){
        lista_todos_produtos = (ListView) convertView.findViewById(R.id.lista_todos_produtos);
        lista_todos_adapter = new ProdutoAdapter(getListaTodos(), context);
        lista_todos_produtos.setAdapter(lista_todos_adapter);

        lista_todos_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, DetailProdutoPedido.class);
                Produto produto = (Produto) lista_todos_adapter.getItem(position);

                intent.putExtra("_id", produto.getId());
                intent.putExtra("nome", produto.getNome());
                intent.putExtra("valor", produto.getValor());
                intent.putExtra("quantidade", produto.getQuantidade());
                intent.putExtra("descricao", produto.getDescricao());
                intent.putExtra("foto", produto.getFoto());

                startActivity(intent);

            }
        });


    }

    public List<Produto> getListaTodos() {
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(context));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.find(context, produtoDatabase, "select * from " + ProdutoDatabaseHelper.NOME_TABELA + " order by " + ProdutoDatabaseHelper.COLUMN_NOME);
    }
}