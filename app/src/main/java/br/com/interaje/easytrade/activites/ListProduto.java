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
import br.com.interaje.easytrade.adapter.ProdutoAdapter;
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class ListProduto extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;
    private ProdutoAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListProduto.this, AddProduto.class));
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

        adapter = new ProdutoAdapter(getLista(), this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListProduto.this, AddProduto.class);
                Produto produto = (Produto) adapter.getItem(position);

                intent.putExtra("_id", produto.getId());
                intent.putExtra("nome", produto.getNome());
                intent.putExtra("valor", produto.getValor());
                intent.putExtra("quantidade", produto.getQuantidade());
                intent.putExtra("descricao", produto.getDescricao());
                intent.putExtra("foto",produto.getFoto());

                startActivity(intent);

            }
        });


    }

    public List<Produto> getLista() {
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(this));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.find(this, produtoDatabase, "select * from " + ProdutoDatabaseHelper.NOME_TABELA + " order by " + ProdutoDatabaseHelper.COLUMN_NOME);

    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, AddProduto.class));
                break;
        }*/
    }

}
