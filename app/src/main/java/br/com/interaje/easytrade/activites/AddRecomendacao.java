package br.com.interaje.easytrade.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import br.com.interaje.easytrade.R;

public class AddRecomendacao extends AppCompatActivity implements View.OnClickListener{

    private Button salvar;
    private EditText codigo;
    private Bundle extras;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recomendacao);

        this.inicializarElementos();
    }


    private void inicializarElementos(){
        this.id = 0l;

        salvar = (Button) findViewById(R.id.salvar);
        codigo = (EditText) findViewById(R.id.nome);

        extras = getIntent().getExtras();

        if(extras != null){
            inicializarElementosExtras();
        }

    }

    private void inicializarElementosExtras(){

        id = extras.getLong("_id");
        codigo.setText(extras.getString("nome"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                //verificando se os valores foram informados
                if (!codigo.getText().toString().equals("")) {

                    //salvando no banco
                    if(true){
                        Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();

                        finish();
                    }else{
                        Toast.makeText(this, "Ocorreu um problema ao salvar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(this, "Código é obrigatório! Informe-o.", Toast.LENGTH_SHORT).show();

                }

                break;

        }
    }
}
