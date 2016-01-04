package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.utils.Config;

public class PreLogin extends AppCompatActivity implements View.OnClickListener {

    private TextView registrar;
    private Button logarEmail, logarFacebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
        this.inicializarElementos();
    }

    private void inicializarElementos(){
        registrar = (TextView) findViewById(R.id.registrar);
        registrar.setOnClickListener(this);

        logarEmail = (Button) findViewById(R.id.logarEmail);
        logarEmail.setOnClickListener(this);

        logarFacebook = (Button) findViewById(R.id.logarFacebook);
        logarFacebook.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registrar:
                /* implementar */

                break;
            case R.id.logarEmail:
                startActivity(new Intent(PreLogin.this, Login.class));
                break;
            case R.id.logarFacebook:
                /* implementar */

                break;
        }
    }
}
