package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.adapter.MainActivityAdapter;
import br.com.interaje.easytrade.utils.InsertProdutos;

public class MainActivity extends AppCompatActivity {

    private TextView produtos;
    private ListView lista;
    private MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.inicializarElementos();
    }

    private void inicializarElementos(){
        lista = (ListView) findViewById(R.id.lista);


        adapter = new MainActivityAdapter(getLista(), this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, AddProduto.class);
                switch (position) {
                    case 0:
                        startActivity(new Intent(MainActivity.this, ListProduto.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this, ListCliente.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this, AddPedido.class));
                        break;
                }

            }
        });


    }

    private ArrayList<String> getLista(){

        ArrayList<String> resultado = new ArrayList<String>();

        resultado.add("Meus Produtos");
        resultado.add("Meus Clientes");
        resultado.add("Minhas Vendas");
        resultado.add("Meus Relatórios");

        return resultado;
    }
}
