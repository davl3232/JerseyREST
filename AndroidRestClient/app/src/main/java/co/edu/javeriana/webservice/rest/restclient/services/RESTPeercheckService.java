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
import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.webservice.rest.restclient.negocio.Article;
import co.edu.javeriana.webservice.rest.restclient.negocio.Author;

public class RESTPeercheckService extends AsyncTask<String, Void, String> {

    protected static final String SERVICE_URL = "http://10.155.100.223:8080/class";

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
        request.addHeader("Accept", "application/json");
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
                respondMessage = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            Log.e(RESTPeercheckService.class.getName(), e.getMessage());
        }
        return respondMessage;
    }

    @Override
    protected void onPostExecute(String response) {
        Log.e(RESTPeercheckService.class.getName(), "Finalizado");
        JSONObject result;
        List<Article> articles = new ArrayList<>();
        try {
            result = new JSONObject(response);
            JSONArray jsonarticles = result.getJSONArray("articles");
            for(int i = 0; i < jsonarticles.length(); i++) {
                Article article = new Article();
                JSONObject articleObject = jsonarticles.getJSONObject(i);
                article.setId(Long.parseLong(articleObject.getString("id")));
                article.setTitle(articleObject.getString("title"));

                JSONArray jsonautores = articleObject.getJSONArray("authors");
                for(int j = 0; j < jsonautores.length(); j++) {
                    JSONObject autorObject = jsonautores.getJSONObject(j);
                    Author autor = new Author();
                    autor.setId(autorObject.getInt("id"));
                    autor.setName(autorObject.getString("name"));
                    autor.setSurname(autorObject.getString("surname"));
                    article.addAuthor(autor);
                }

                articles.add(article);
            }
        } catch (JSONException e) {
            Log.e(RESTPeercheckService.class.getName(), e.getMessage());
        }
        dialog.dismiss();
        lastAction(articles);
    }

    protected void lastAction(List<Article> articles) {}
}
