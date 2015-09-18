package imd.ufrn.br.perfil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CadastroActivity extends Activity {

    ImageButton fotoBtn;
    private static final int FOTO = 1;
    DatePickerDialog dataDialog;
    EditText dataText;
    SimpleDateFormat formatador;
    Button btnCadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        fotoBtn = (ImageButton) findViewById(R.id.FotoBtn);
        fotoBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, FOTO);
            }
        });

        formatador = new SimpleDateFormat("dd/MM/yyyy");
        dataText =(EditText) findViewById(R.id.dataText);

        Calendar novaData = Calendar.getInstance().getInstance();
        dataDialog = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        Calendar data = Calendar.getInstance();
                        data.set(year, monthOfYear, dayOfMonth);
                        dataText.setText(formatador.format(data.getTime()));
                    }
                },novaData.get(Calendar.YEAR), novaData.get(Calendar.MONTH),
                novaData.get(Calendar.DAY_OF_MONTH));

        btnCadastrar = (Button) findViewById(R.id.ConfirmarBtn);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmarDialog();
            }
        });

        dataText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    dataDialog.show();
            }
        });

        dataText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataDialog.show();
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==FOTO) {
            Bitmap foto = (Bitmap) data.getExtras().get("data");
            fotoBtn.setImageBitmap(foto);
        }
    }

    public void confirmarDialog() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Atenção");
        alerta.setMessage("Deseja realmente cadastrar?");
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //salvar no banco
                finish();
            }
        });
        alerta.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alerta.show();
    }
}
