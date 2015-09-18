package imd.ufrn.br.perfil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText loginText, senhaText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginText = (EditText) findViewById(R.id.loginText);
        senhaText = (EditText) findViewById(R.id.senhaText);
    }


    public void entrar(View view){

        boolean erro = false;

        if (loginText.getText().toString().trim().equals("")){
            loginText.setError("Digite um login válido");
            erro = true;
        }

        if (senhaText.getText().toString().trim().equals("")) {
            senhaText.setError("Digite uma senha válida");
            erro = true;
        }

        CadastroActivity cadastroActivity = new CadastroActivity();
        Intent i = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(i);
    }
}
