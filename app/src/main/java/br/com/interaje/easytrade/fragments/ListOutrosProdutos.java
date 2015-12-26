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

public class ListOutrosProdutos extends Fragment {

    private ListView lista_ultimos_add_produtos, lista_escolhidos_por_cliente_produtos;
    private ProdutoAdapter lista_ultimos_add_adapter, lista_escolhidos_por_cliente_adapter;
    private View convertView = null;
    private Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = inflater.getContext();
        convertView =  inflater.inflate(R.layout.fragment_list_outros_produtos, container, false);
        this.inicializarElementos();
        return convertView;

    }


    private void inicializarElementos(){
        lista_ultimos_add_produtos = (ListView) convertView.findViewById(R.id.lista_ultimos_add_produtos);
        lista_ultimos_add_adapter = new ProdutoAdapter(getListaUltimosAdd(), context);
        lista_ultimos_add_produtos.setAdapter(lista_ultimos_add_adapter);
        lista_ultimos_add_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, DetailProdutoPedido.class);
                Produto produto = (Produto) lista_ultimos_add_adapter.getItem(position);

                intent.putExtra("_id", produto.getId());
                intent.putExtra("nome", produto.getNome());
                intent.putExtra("valor", produto.getValor());
                intent.putExtra("quantidade", produto.getQuantidade());
                intent.putExtra("descricao", produto.getDescricao());
                intent.putExtra("foto", produto.getFoto());

                startActivity(intent);

            }
        });


        lista_escolhidos_por_cliente_produtos = (ListView) convertView.findViewById(R.id.lista_escolhidos_por_cliente_produtos);
        lista_escolhidos_por_cliente_adapter = new ProdutoAdapter(getListaEscolhidosPorCliente(), context);
        lista_escolhidos_por_cliente_produtos.setAdapter(lista_escolhidos_por_cliente_adapter);
        lista_escolhidos_por_cliente_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, DetailProdutoPedido.class);
                Produto produto = (Produto) lista_escolhidos_por_cliente_adapter.getItem(position);

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

    public List<Produto> getListaUltimosAdd() {
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(context));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.find(context, produtoDatabase, "select * from " + ProdutoDatabaseHelper.NOME_TABELA + " order by " + ProdutoDatabaseHelper.COLUMN_ID + " desc limit 10");
    }

    public List<Produto> getListaEscolhidosPorCliente() {
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(context));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.find(context, produtoDatabase, "select * from " + ProdutoDatabaseHelper.NOME_TABELA +  " where _id in (2,15) ");
    }
}