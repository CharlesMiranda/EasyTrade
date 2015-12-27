package br.com.interaje.easytrade.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class AddProduto extends AppCompatActivity implements View.OnClickListener{

    private Button salvar;
    private EditText nome, valor, quantidade, descricao;
    private byte[] foto;
    private Bundle extras;
    private Long id;
    private ImageButton btnCamera;
    private ProdutoDatabase produtoDatabase;
    private ProdutoDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_produto);

        this.inicializarElementos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        if(this.id != 0l) {

            // Inflate the menu; this adds items to the action bar if it is present.
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_produto, menu);

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

                produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(this));
                dao = new ProdutoDAOImpl();

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Confirmação")
                        .setMessage("Deseja mesmo remover o registro?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //salvando no banco
                                if(dao.remover(AddProduto.this.id, AddProduto.this, produtoDatabase)){
                                    Toast.makeText(AddProduto.this, "Removido com sucesso.", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(AddProduto.this, ListProduto2.class));
                                    finish();
                                }else{
                                    Toast.makeText(AddProduto.this, "Ocorreu um problema ao remover! Tente novamente.", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(this, ListProduto2.class));
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
        valor = (EditText) findViewById(R.id.valor);
        quantidade = (EditText) findViewById(R.id.quantidade);
        descricao = (EditText) findViewById(R.id.descricao);

        extras = getIntent().getExtras();

        if(extras != null){
            inicializarElementosExtras();
        }

    }

    private void inicializarElementosExtras(){

        id = extras.getLong("_id");
        nome.setText(extras.getString("nome"));
        valor.setText(String.valueOf(extras.getDouble("valor")));
        quantidade.setText(String.valueOf(extras.getInt("quantidade")));
        descricao.setText(extras.getString("descricao"));
        foto = extras.getByteArray("foto");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                Produto produto = new Produto();

                //verificando se os valores foram informados
                if (!nome.getText().toString().equals("")
                        && !valor.getText().toString().equals("")
                        && !quantidade.getText().toString().equals("")
                        && foto != null) {

                    produto.setId(id);
                    produto.setNome(nome.getText().toString());
                    produto.setValor(Double.parseDouble(valor.getText().toString()));
                    produto.setQuantidade(Integer.parseInt(quantidade.getText().toString()));
                    produto.setDescricao(descricao.getText().toString());
                    produto.setFoto(foto);

                    //salvando no banco
                    if(salvarProduto(produto)){
                        Toast.makeText(this, "Salvo com sucesso.", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(this, ListProduto2.class));
                        finish();
                    }else{
                        Toast.makeText(this, "Ocorreu um problema ao salvar! Tente novamente.", Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(this, "Nome, valor, quantidade e foto são obrigatórios! Informe-os.", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.btnCamera:
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
                break;
        }
    }

    private boolean salvarProduto(Produto produto) {
        ProdutoDatabase produtoDatabase = new ProdutoDatabase(new ProdutoDatabaseHelper(this));
        ProdutoDAO dao = new ProdutoDAOImpl();

        return dao.salvar(produto, produtoDatabase);
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
