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
import br.com.interaje.easytrade.adapter.UsuarioAdapter;
import br.com.interaje.easytrade.database.UsuarioDatabase;
import br.com.interaje.easytrade.database.UsuarioDatabaseHelper;
import br.com.interaje.easytrade.model.Usuario;
import br.com.interaje.easytrade.repositories.UsuarioDAO;
import br.com.interaje.easytrade.repositories.impl.UsuarioDAOImpl;

public class ListUsuario extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;
    private UsuarioAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_usuario);
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

        adapter = new UsuarioAdapter(getLista(), this);
        lista.setAdapter(adapter);
    }

    public List<Usuario> getLista() {
        UsuarioDatabase usuarioDatabase = new UsuarioDatabase(new UsuarioDatabaseHelper(this));
        UsuarioDAO dao = new UsuarioDAOImpl();

        return dao.find(this, usuarioDatabase, "select * from " + UsuarioDatabaseHelper.NOME_TABELA + " order by " + UsuarioDatabaseHelper.COLUMN_NOME);


    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, AddUsuario.class));
                break;
        }*/
    }

}
