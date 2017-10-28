package co.edu.javeriana.webservice.rest.restclient;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout layout;
    private TextInputEditText input;
    private LinearLayout autors;
    private Button agregar;
    private Button quitar;
    private Button guardar;
    private Button ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (TextInputLayout) findViewById(R.id.layout);
        input = (TextInputEditText) findViewById(R.id.input);
        autors = (LinearLayout) findViewById(R.id.authors);
        agregar = (Button) findViewById(R.id.btn_agregar);
        quitar = (Button) findViewById(R.id.btn_quitar);
        guardar = (Button) findViewById(R.id.btn_guardar);
        ver = (Button) findViewById(R.id.btn_ver);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregar();
            }
        });

        quitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quitar();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar();
            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ver();
            }
        });
    }

    private void quitar() {
        int index = autors.getChildCount() - 1;
        if(index > 0) {
            autors.removeViewAt(index);
        } else {
            Snackbar.make(this.getCurrentFocus(), "Debes agregar almenos un autor", Snackbar.LENGTH_SHORT).show();
        }
    }

    private void agregar() {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout textLayout = new TextInputLayout(this);
        textLayout.setLayoutParams(lp);

        TextInputEditText textAutor = new TextInputEditText(this);
        textAutor.setHint("Nombre del autor");
        textAutor.setLayoutParams(lp);

        textLayout.addView(textAutor);
        autors.removeView(textLayout);
        autors.addView(textLayout);
    }

    private void guardar() {
        String name = input.getText().toString();
        List<View> childs = autors.getFocusables(View.FOCUS_UP);
        for (View v : childs) {
            String text = ((TextInputEditText)v).getText().toString();
            Log.i("Nombre", text);
        }
        Snackbar.make(this.getCurrentFocus(), "Articulo guardado: " + name, Snackbar.LENGTH_SHORT).show();
    }

    private void ver() {
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }
}
