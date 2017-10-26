package co.edu.javeriana.webservice.rest.restclient;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout layout;
    private TextInputEditText input;
    private Button guardar;
    private Button ver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = (TextInputLayout) findViewById(R.id.layout);
        input = (TextInputEditText) findViewById(R.id.input);
        guardar = (Button) findViewById(R.id.btn_guardar);
        ver = (Button) findViewById(R.id.btn_ver);

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

    private void guardar() {
        String name = input.getText().toString();
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(MY_SERVER_URL);
        WebTarget helloWebTarget = webTarget.path("myresource/article");
        Invocation.Builder invocationBuilder = helloWebTarget.request(MediaType.APPLICATION_JSON);
        Snackbar.make(this.getCurrentFocus(), "Articulo guardado: " + name, Snackbar.LENGTH_SHORT).show();
    }

    private void ver() {
    }
}
