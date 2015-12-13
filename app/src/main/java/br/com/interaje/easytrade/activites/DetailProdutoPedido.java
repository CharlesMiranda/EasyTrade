package br.com.interaje.easytrade.activites;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.database.CarrinhoDatabase;
import br.com.interaje.easytrade.database.CarrinhoDatabaseHelper;
import br.com.interaje.easytrade.database.ProdutoDatabase;
import br.com.interaje.easytrade.database.ProdutoDatabaseHelper;
import br.com.interaje.easytrade.model.Carrinho;
import br.com.interaje.easytrade.model.Produto;
import br.com.interaje.easytrade.repositories.CarrinhoDAO;
import br.com.interaje.easytrade.repositories.ProdutoDAO;
import br.com.interaje.easytrade.repositories.impl.CarrinhoDAOImpl;
import br.com.interaje.easytrade.repositories.impl.ProdutoDAOImpl;

public class DetailProdutoPedido extends AppCompatActivity implements View.OnClickListener{

    private Button salvar;
    private TextView nome, valor, descricao;
    private byte[] foto;
    private Bundle extras;
    private Long id;
    private ImageView imgFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produto_pedido);

        this.inicializarElementos();
    }

    private void inicializarElementos(){
        this.id = 0l;
        salvar = (Button) findViewById(R.id.salvar);
        salvar.setOnClickListener(this);

        nome = (TextView) findViewById(R.id.nome);
        valor = (TextView) findViewById(R.id.valor);
        descricao = (TextView) findViewById(R.id.descricao);
        imgFoto = (ImageView) findViewById(R.id.imgFoto);

        extras = getIntent().getExtras();

        if(extras != null){
            inicializarElementosExtras();
        }

    }

    private void inicializarElementosExtras(){

        id = extras.getLong("_id");
        nome.setText(extras.getString("nome"));

        DecimalFormat numeroFormatado;
        String numero;
        numeroFormatado = new DecimalFormat("'R$ '0.00");
        numero = numeroFormatado.format(extras.getDouble("valor"));
        valor.setText(numero);

        descricao.setText(extras.getString("descricao"));
        byte[] photo = foto = extras.getByteArray("foto");

        if(photo != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);
            imgFoto.setImageBitmap(bitmap);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.salvar:

                Carrinho carrinho = new Carrinho();

                //verificando se os valores foram informados
                carrinho.setId(0l);
                carrinho.setProduto_id(id);

                //salvando no banco
                if(salvarCarrinho(carrinho)){
                    Toast.makeText(this, "Adicionado com sucesso.", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(this, ListCarrinho.class));
                    finish();
                }else{
                    Toast.makeText(this, "Ocorreu um problema ao adicionar! Tente novamente.", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }

    private boolean salvarCarrinho(Carrinho carrinho) {
        CarrinhoDatabase carrinhoDatabase = new CarrinhoDatabase(new CarrinhoDatabaseHelper(this));
        CarrinhoDAO dao = new CarrinhoDAOImpl();

        return dao.salvar(carrinho, carrinhoDatabase);
    }

}
