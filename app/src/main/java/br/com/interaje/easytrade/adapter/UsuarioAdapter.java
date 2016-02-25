package br.com.interaje.easytrade.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.model.Usuario;
import br.com.interaje.easytrade.utils.ImageHelper;

/**
 * Created by charles on 21/11/15.
 */
public class UsuarioAdapter extends BaseAdapter {

    private List<Usuario> list;
    private LayoutInflater mLayout;

    public UsuarioAdapter(List<Usuario> lista, Context c) {
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
        convertView = mLayout.inflate(R.layout.item_list_usuario, parent, false);

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        Usuario usuario = list.get(position);

        nome.setText(usuario.getNome()+" "+usuario.getEmail());

        return convertView;
    }
}