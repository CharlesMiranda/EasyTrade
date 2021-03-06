package br.com.interaje.easytrade.activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import br.com.interaje.easytrade.R;
import br.com.interaje.easytrade.model.Cliente;
import br.com.interaje.easytrade.utils.Config;
import br.com.interaje.easytrade.utils.PrefsManager;
import br.com.interaje.easytrade.utils.SessionManager;
import cz.msebera.android.httpclient.Header;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private Button logar;
    private ProgressDialog pd = null;
    private EditText et_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.inicializarElementos();

    }

    private void inicializarElementos() {
        logar = (Button) findViewById(R.id.logar);
        logar.setOnClickListener(this);
        et_email = (EditText) findViewById(R.id.login);
    }

    private ProgressDialog dialogLogin() {
        ProgressDialog dialog =
                ProgressDialog.show(
                        Login.this, "Aguarde..", "Conectando ao servidor.", true);
        dialog.setCancelable(true);
        return dialog;
    }

    private void logar() {
        RequestParams params = new RequestParams();
        params.add("valor", "Felipe");

        final String email = et_email.getText().toString();

        AsyncHttpClient client = new AsyncHttpClient();
        final Context context = Login.this;
        client.setMaxRetriesAndTimeout(1, 3000);
        client.post("http://192.168.1.138:8080/GuiaShop/Vendedor/ValidarAcesso", params,
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String decoded = new String(responseBody);
                        Toast.makeText(context, "Resposta: " + decoded, Toast.LENGTH_LONG).show();

                        // Cliente Fictício (Aqui você seta no objeto o q vem do servidor)
                        Cliente serverUser = new Cliente();
                        serverUser.setEmail(email);
                        serverUser.setNome("Charles da Silva");
                        serverUser.setId(1l);
                        serverUser.setTelefone("8644445555");

                        createSession(serverUser);
                        pd.dismiss();

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(context, "Falhou, deu esse erro: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        pd.dismiss();

                    }
                });
    }

    /**
     * Cria uma sessão do cliente q veio lá do servidor
     *
     * @param serverUser
     */
    private void createSession(Cliente serverUser) {
        SessionManager session = new SessionManager(Login.this);

        session.createLoginSession(serverUser);
    }

    private void chamaTask() {
        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                try {
                    // Faça a chamada aqui!
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                PrefsManager session = new PrefsManager(Login.this);
                session.addSessionParam("username", "Felipe Costa");
                session.addSessionParam("email", "email@email.com");
                session.addSessionParam("personalMessage", "Tenha um ótimo dia!");
                pd.dismiss();
                startActivity(new Intent(Login.this, Main2Activity.class));
            }
        };
        task.execute();
    }

    private void chamaThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000l);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pd.dismiss();
                startActivity(new Intent(Login.this, Main2Activity.class));
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logar:

                pd = dialogLogin();
                pd.show();

                if (Config.AMBIENTE_DESENVOLVIMENTO == "producao") {
                    logar();
                } else {
                    chamaTask();
                }
                break;
        }
    }

}
