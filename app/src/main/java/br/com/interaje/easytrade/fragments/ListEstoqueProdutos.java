package br.com.interaje.easytrade.fragments;

/**
 * Created by charles on 10/12/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.activites.AddProduto;
import br.com.interaje.easytrade.adapter.ProdutoAdapter;
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class ListEstoqueProdutos extends Fragment {

    private ListView lista_estoque_produtos;
    private ProdutoAdapter lista_estoque_adapter;
    private View convertView = null;
    private Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = inflater.getContext();
        convertView =  inflater.inflate(R.layout.fragment_list_estoque_produtos, container, false);
        this.inicializarElementos();

        return convertView;

    }


    private void inicializarElementos(){

        lista_estoque_produtos = (ListView) convertView.findViewById(R.id.lista_estoque_produtos);
        lista_estoque_adapter = new ProdutoAdapter(getListaMaisVendidos(), context);
        lista_estoque_produtos.setAdapter(lista_estoque_adapter);

        FloatingActionButton fab = (FloatingActionButton) convertView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AddProduto.class));
            }
        });

        lista_estoque_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, AddProduto.class);
                Produto produto = (Produto) lista_estoque_adapter.getItem(position);

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

    public List<Produto> getListaMaisVendidos() {
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(context));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.find(context, produtoDatabase, "select * from " + ProdutoDatabaseHelper.NOME_TABELA + " where _id in (4,11,13,16,10) ");
    }
}