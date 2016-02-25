package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.database.UsuarioDatabase;
import br.com.interaje.easytrade.database.UsuarioDatabaseHelper;
import br.com.interaje.easytrade.model.Usuario;
import br.com.interaje.easytrade.repositories.UsuarioDAO;
import br.com.interaje.easytrade.repositories.impl.UsuarioDAOImpl;
import br.com.interaje.easytrade.utils.PrefsManager;

public class AddUsuario extends AppCompatActivity implements View.OnClickListener{

    private Button salvar;
    private EditText nome, email, senha, confirmaSenha;
    private Bundle extras;
    private Long id;
    private UsuarioDatabase usuarioDatabase;
    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_usuario);

        this.inicializarElementos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(this.id != 0l) {

            // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_usuario, menu);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            default:
                startActivity(new Intent(this, PreLogin.class));
                finish();
                break;
        }

        return true;
    }


    private void inicializarElementos(){
        this.id = 0l;


        salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(this);

        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        confirmaSenha = (EditText) findViewById(R.id.confirma_senha);

        extras = getIntent().getExtras();

        if(extras != null){
            inicializarElementosExtras();
        }

    }

    private void inicializarElementosExtras(){

        id = extras.getLong("_id");
        nome.setText(extras.getString("nome"));
        email.setText(extras.getString("email"));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                Usuario usuario = new Usuario();

                //verificando se os valores foram informados
                if (!nome.getText().toString().equals("")
                        && !email.getText().toString().equals("")
                        && !senha.getText().toString().equals("")
                        && !confirmaSenha.getText().toString().equals("")
                        && senha.getText().toString().equals(confirmaSenha.getText().toString())
                        /*&& foto != null*/) {

                    usuario.setId(id);
                    usuario.setNome(nome.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha.getText().toString());

                    //salvando no banco
                    if(salvarUsuario(usuario)){
                        Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();

                        PrefsManager session = new PrefsManager(AddUsuario.this);
                        session.addSessionParam("username", nome.getText().toString());
                        session.addSessionParam("email", email.getText().toString());
                        session.addSessionParam("personalMessage", "Tenha um ótimo dia!");

                        startActivity(new Intent(this, Main2Activity.class));
                        finish();
                    }else{
                        Toast.makeText(this, "Ocorreu um problema ao salvar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    if(!senha.getText().toString().equals(confirmaSenha.getText().toString())){
                        Toast.makeText(this, "Senha e confirmação de senha precisam ser iguais!", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(this, "Nome, email, senha e confirmação de senha são obrigatórios! Informe-os.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
    }

    private boolean salvarUsuario(Usuario usuario) {
        UsuarioDatabase usuarioDatabase = new UsuarioDatabase(new UsuarioDatabaseHelper(this));
        UsuarioDAO dao = new UsuarioDAOImpl();

        return dao.salvar(usuario, usuarioDatabase);
    }

}
