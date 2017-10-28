package co.edu.javeriana.webservice.rest.restclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import co.edu.javeriana.webservice.rest.restclient.services.RESTPeercheckService;

public class ListActivity extends AppCompatActivity {

    private RESTPeercheckService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        //service = new RESTPeercheckService(this, "", "lang/es");
        service = new RESTPeercheckService(this, "", "articles");
    }

    @Override
    protected void onStart() {
        super.onStart();
        service.execute();
    }
}
