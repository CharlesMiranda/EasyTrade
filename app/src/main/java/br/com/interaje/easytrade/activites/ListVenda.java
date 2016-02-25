package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import br.com.interaje.easytrade.R;

public class ListVenda extends AppCompatActivity implements View.OnClickListener{

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_venda);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListVenda.this, ChooseCliente.class));
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



    }


    @Override
    public void onClick(View v) {
        /*switch (v.getId()) {
            case R.id.add:
                startActivity(new Intent(this, AddVenda.class));
                break;
        }*/
    }

}
