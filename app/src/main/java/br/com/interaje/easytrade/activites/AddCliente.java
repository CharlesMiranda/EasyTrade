package br.com.interaje.easytrade.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.database.ClienteDatabase;
import br.com.interaje.easytrade.database.ClienteDatabaseHelper;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.repositories.ClienteDAO;
import br.com.interaje.easytrade.repositories.impl.ClienteDAOImpl;

public class AddCliente extends AppCompatActivity implements View.OnClickListener{

    private Button salvar;
    private EditText nome, telefone, email, observacao;
    private byte[] foto;
    private Bundle extras;
    private Long id;
    private ImageButton btnCamera;
    private ClienteDatabase clienteDatabase;
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cliente);

        this.inicializarElementos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(this.id != 0l) {

            // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_cliente, menu);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.btn_deletar:

                clienteDatabase = new ClienteDatabase(new ClienteDatabaseHelper(this));
                dao = new ClienteDAOImpl();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmação")
                        .setMessage("Deseja mesmo remover o registro?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //salvando no banco
                                if(dao.remover(AddCliente.this.id, AddCliente.this, clienteDatabase)){
                                    Toast.makeText(AddCliente.this, "Removido com sucesso.", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(AddCliente.this, ListCliente.class));
                                    finish();
                                }else{
                                    Toast.makeText(AddCliente.this, "Ocorreu um problema ao remover! Tente novamente.", Toast.LENGTH_SHORT).show();
                                }

                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();

                break;
            default:
                startActivity(new Intent(this, ListCliente.class));
                finish();
                break;
        }

        return true;
    }


    private void inicializarElementos(){
        this.id = 0l;

        btnCamera = (ImageButton) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(this);

        salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(this);

        nome = (EditText) findViewById(R.id.nome);
        telefone = (EditText) findViewById(R.id.telefone);
        email = (EditText) findViewById(R.id.email);
        observacao = (EditText) findViewById(R.id.observacao);

        extras = getIntent().getExtras();

        if(extras != null){
            inicializarElementosExtras();
        }

    }

    private void inicializarElementosExtras(){

        id = extras.getLong("_id");
        nome.setText(extras.getString("nome"));
        telefone.setText(extras.getString("telefone"));
        email.setText(extras.getString("email"));
        observacao.setText(extras.getString("observacao"));
        foto = extras.getByteArray("foto");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                Cliente cliente = new Cliente();

                //verificando se os valores foram informados
                if (!nome.getText().toString().equals("")
                        && !telefone.getText().toString().equals("")
                        && !email.getText().toString().equals("")
                        /*&& foto != null*/) {

                    cliente.setId(id);
                    cliente.setNome(nome.getText().toString());
                    cliente.setTelefone(telefone.getText().toString());
                    cliente.setEmail(email.getText().toString());
                    cliente.setObservacao(observacao.getText().toString());
                    cliente.setFoto(foto);

                    //salvando no banco
                    if(salvarCliente(cliente)){
                        Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, ListCliente.class));
                        finish();
                    }else{
                        Toast.makeText(this, "Ocorreu um problema ao salvar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(this, "Nome, telefone, email são obrigatórios! Informe-os.", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;
        }
    }

    private boolean salvarCliente(Cliente cliente) {
        ClienteDatabase clienteDatabase = new ClienteDatabase(new ClienteDatabaseHelper(this));
        ClienteDAO dao = new ClienteDAOImpl();

        return dao.salvar(cliente, clienteDatabase);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap photo = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
        foto = stream.toByteArray();
    }
}
