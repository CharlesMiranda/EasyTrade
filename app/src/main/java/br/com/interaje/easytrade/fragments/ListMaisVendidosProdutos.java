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

public class ListMaisVendidosProdutos extends Fragment {

    private ListView lista_mais_vendidos_produtos;
    private ProdutoAdapter lista_mais_vendidos_adapter;
    private View convertView = null;
    private Context context = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        context = inflater.getContext();
        convertView =  inflater.inflate(R.layout.fragment_list_mais_vendidos_produtos, container, false);
        this.inicializarElementos();
        return convertView;

    }


    private void inicializarElementos(){
        lista_mais_vendidos_produtos = (ListView) convertView.findViewById(R.id.lista_mais_vendidos_produtos);
        lista_mais_vendidos_adapter = new ProdutoAdapter(getListaMaisVendidos(), context);
        lista_mais_vendidos_produtos.setAdapter(lista_mais_vendidos_adapter);

        lista_mais_vendidos_produtos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(context, DetailProdutoPedido.class);
                Produto produto = (Produto) lista_mais_vendidos_adapter.getItem(position);

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