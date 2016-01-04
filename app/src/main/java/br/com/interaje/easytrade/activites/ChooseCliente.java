package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.adapter.ClienteAdapter;
import br.com.interaje.easytrade.database.CarrinhoClienteDatabase;
import br.com.interaje.easytrade.database.CarrinhoClienteDatabaseHelper;
import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.model.CarrinhoCliente;
import br.com.interaje.easytrade.repositories.CarrinhoClienteDAO;
import br.com.interaje.easytrade.repositories.ClienteDAO;
import br.com.interaje.easytrade.repositories.impl.CarrinhoClienteDAOImpl;
import br.com.interaje.easytrade.repositories.impl.ClienteDAOImpl;

public class ChooseCliente extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;
    private ClienteAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

                Cliente cliente = (Cliente) adapter.getItem(position);
                CarrinhoCliente carrinhoCliente = new CarrinhoCliente();

                //verificando se os valores foram informados
                carrinhoCliente.setId(0l);
                carrinhoCliente.setCliente_id(cliente.getId());

                //limpar cache
                novoClienteCarrinho();

                //salvando no banco
                if (salvarClienteCarrinho(carrinhoCliente)) {
                    startActivity(new Intent(ChooseCliente.this, AddPedido.class));
                    finish();
                } else {
                    Toast.makeText(ChooseCliente.this, "Ocorreu um problema ao salvar o cliente! Tente novamente.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void novoClienteCarrinho() {
        CarrinhoClienteDatabase carrinhoDatabase = new CarrinhoClienteDatabase(new CarrinhoClienteDatabaseHelper(this));
        CarrinhoClienteDAO dao = new CarrinhoClienteDAOImpl();

        dao.removerAll(this, carrinhoDatabase);
    }

    private boolean salvarClienteCarrinho(CarrinhoCliente carrinhoCliente) {
        CarrinhoClienteDatabase carrinhoDatabase = new CarrinhoClienteDatabase(new CarrinhoClienteDatabaseHelper(this));
        CarrinhoClienteDAO dao = new CarrinhoClienteDAOImpl();

        return dao.salvar(carrinhoCliente, carrinhoDatabase);
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
