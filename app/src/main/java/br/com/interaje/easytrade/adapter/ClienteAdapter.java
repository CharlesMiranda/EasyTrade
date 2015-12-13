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

import java.text.DecimalFormat;
import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.utils.ImageHelper;

/**
 * Created by charles on 21/11/15.
 */
public class ClienteAdapter extends BaseAdapter {

    private List<Cliente> list;
    private LayoutInflater mLayout;

    public ClienteAdapter(List<Cliente> lista, Context c) {
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
        convertView = mLayout.inflate(R.layout.item_list_cliente, parent, false);

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        Cliente cliente = list.get(position);

        nome.setText(cliente.getNome()+"\n"+cliente.getTelefone()+" "+cliente.getEmail());

        byte[] photo = cliente.getFoto();

        if(photo != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            imgFoto.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap));

        }

        return convertView;
    }
}