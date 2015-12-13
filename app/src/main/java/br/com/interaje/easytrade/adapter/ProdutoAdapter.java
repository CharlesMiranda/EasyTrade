package br.com.interaje.easytrade.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.utils.ImageHelper;

/**
 * Created by charles on 21/11/15.
 */
public class ProdutoAdapter extends BaseAdapter {

    private List<Produto> list;
    private LayoutInflater mLayout;

    public ProdutoAdapter(List<Produto> lista, Context c) {
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
        convertView = mLayout.inflate(R.layout.item_list_produto, parent, false);

        TextView nome = (TextView) convertView.findViewById(R.id.nome);
        TextView valor = (TextView) convertView.findViewById(R.id.valor);
        ImageView imgFoto = (ImageView) convertView.findViewById(R.id.imgFoto);
        Produto produto = list.get(position);
        DecimalFormat numeroFormatado;
        String numero;

        nome.setText(produto.getNome());

        numeroFormatado = new DecimalFormat("'R$ '0.00");
        numero = numeroFormatado.format(produto.getValor());

        valor.setText(numero);
        byte[] photo = produto.getFoto();

        if(photo != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            imgFoto.setImageBitmap(ImageHelper.getRoundedCornerBitmap(bitmap));

        }

        return convertView;
    }
}