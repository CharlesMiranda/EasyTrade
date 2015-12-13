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
import br.com.interaje.easytrade.adapter.ClienteAdapter;
import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.repositories.ClienteDAO;
import br.com.interaje.easytrade.repositories.impl.ClienteDAOImpl;

public class ListCliente extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;
    private ClienteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListCliente.this, AddCliente.class));
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

        adapter = new ClienteAdapter(getLista(), this);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(ListCliente.this, AddCliente.class);
                Cliente cliente = (Cliente) adapter.getItem(position);

                intent.putExtra("_id", cliente.getId());
                intent.putExtra("nome", cliente.getNome());
                intent.putExtra("telefone", cliente.getTelefone());
                intent.putExtra("email", cliente.getEmail());
                intent.putExtra("observacao", cliente.getObservacao());
                intent.putExtra("foto",cliente.getFoto());

                startActivity(intent);

            }
        });


    }

    public List<Cliente> getLista() {
        ClienteDatabase clienteDatabase = new ClienteDatabase(new ClienteDatabaseHelper(this));
        ClienteDAO dao = new ClienteDAOImpl();

        return dao.find(this, clienteDatabase, "select * from " + ClienteDatabaseHelper.NOME_TABELA + " order by " + ClienteDatabaseHelper.COLUMN_NOME);


    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, AddCliente.class));
                break;
        }*/
    }

}
