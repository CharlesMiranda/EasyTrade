package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.adapter.CarrinhoAdapter;
import br.com.interaje.easytrade.database.CarrinhoDatabase;
import br.com.interaje.easytrade.database.CarrinhoDatabaseHelper;
import br.com.interaje.easytrade.model.Carrinho;
import br.com.interaje.easytrade.repositories.CarrinhoDAO;
import br.com.interaje.easytrade.repositories.impl.CarrinhoDAOImpl;

public class ListCarrinho extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;
    private CarrinhoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_carrinho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListCarrinho.this, AddPedido.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onResume(){
        super.onResume();
        this.inicializarElementos();
    }


    private void inicializarElementos(){
        lista = (ListView) findViewById(R.id.lista);

        adapter = new CarrinhoAdapter(getLista(), this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent intent = new Intent(ListCarrinho.this, AddCarrinho.class);
                Carrinho carrinho = (Carrinho) adapter.getItem(position);

                intent.putExtra("_id", carrinho.getId());
                intent.putExtra("nome", carrinho.getNome());
                intent.putExtra("valor", carrinho.getValor());
                intent.putExtra("quantidade", carrinho.getQuantidade());
                intent.putExtra("descricao", carrinho.getDescricao());
                intent.putExtra("foto",carrinho.getFoto());

                startActivity(intent);*/

            }
        });


    }

    public List<Carrinho> getLista() {
        CarrinhoDatabase carrinhoDatabase = new CarrinhoDatabase(new CarrinhoDatabaseHelper(this));
        CarrinhoDAO dao = new CarrinhoDAOImpl();

        return dao.find(this, carrinhoDatabase, "select * from " + CarrinhoDatabaseHelper.NOME_TABELA);
    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, AddCarrinho.class));
                break;
        }*/
    }

}
