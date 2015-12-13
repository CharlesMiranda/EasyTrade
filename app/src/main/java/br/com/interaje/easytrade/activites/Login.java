package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.interaje.easytrade.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button logar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logar = (Button) findViewById(R.id.logar);
        logar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logar:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

}
