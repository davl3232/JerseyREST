package co.edu.javeriana.webservice.rest.restclient.services;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

public class RESTPeercheckService extends AsyncTask<String, Void, String> {

    //protected static final String SERVICE_URL = "http://restcountries.eu/rest/v2";
    protected static final String SERVICE_URL = "http://localhost:8080/class";

    private ProgressDialog dialog;
    private Activity activity;
    private String queryParams;
    private String pathParams;

    public RESTPeercheckService(Activity activity, String queryParams, String pathParams) {
        this.activity = activity;
        this.queryParams = queryParams;
        this.pathParams = pathParams;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, "Petición de todos los articulos", "Esperando respuesta del servidor...", false, false);
    }

    @Override
    protected String doInBackground(String... params) {
        String respondMessage = "";

        if(params != null && params.length > 0) {
            this.queryParams = params[0];
        }

        String uri = SERVICE_URL + "/" + pathParams;

        HttpGet request = new HttpGet(uri);
        request.addHeader("content-type", "application/json");
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = null;

        Log.i(RESTPeercheckService.class.getName(), request.getMethod() + " : " + request.getURI().toString());
        try {
            response = client.execute(request);
            int responseCode = response.getStatusLine().getStatusCode();
            String message = response.getStatusLine().getReasonPhrase();
            HttpEntity entity = response.getEntity();
            Log.i(RESTPeercheckService.class.getName(), "Response code: " + responseCode);
            Log.i(RESTPeercheckService.class.getName(), "Response message: " + message);

            if(entity != null) {
                respondMessage = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            Log.e(RESTPeercheckService.class.getName(), e.getMessage());
        }
        return respondMessage;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.e(RESTPeercheckService.class.getName(), "Finalizado " + response);
        JSONArray result;
        try {
            result = new JSONArray(response);
            for(int i = 0; i < result.length(); i++) {
                JSONObject object = result.getJSONObject(i);
                Log.i("REST", object.toString());
            }
        } catch (JSONException e) {
            Log.e(RESTPeercheckService.class.getName(), e.getMessage());
        }
        dialog.dismiss();
    }
}
