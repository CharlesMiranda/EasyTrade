package br.com.interaje.easytrade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.model.Produto;

/**
 * Created by charles on 21/11/15.
 */
public class MainActivityAdapter extends BaseAdapter {

    private List<String> list;
    private LayoutInflater mLayout;

    public MainActivityAdapter(List<String> lista, Context c) {
        list = lista;
        mLayout = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 1l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayout.inflate(R.layout.item_list_main_activity, parent, false);

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        ImageView imagem = (ImageView) convertView.findViewById(R.id.imagem);

        String opcao = list.get(position);
        nome.setText(opcao);

        imagem.setImageResource(getImagemItem(position));

        return convertView;
    }

    private int getImagemItem(int position){
        int imagemItem = 0;

        switch (position){
            case 1:
                imagemItem = R.drawable.produtos;
                break;
            case 2:
                imagemItem = R.drawable.vendas;
                break;
            case 3:
                imagemItem = R.drawable.recomendacao;
                break;
            case 4:
                imagemItem = R.drawable.ajuda;
                break;
            default:
                imagemItem = R.drawable.clientes;
                break;
        }

        return imagemItem;
    }
}