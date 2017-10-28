package co.edu.javeriana.webservice.rest.restclient;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.webservice.rest.restclient.adapters.ArticleAdapter;
import co.edu.javeriana.webservice.rest.restclient.negocio.Article;
import co.edu.javeriana.webservice.rest.restclient.services.RESTPeercheckService;

public class ListActivity extends AppCompatActivity {

    private RESTPeercheckService service;
    private ListView list;
    private ArticleAdapter articleAdapter;
    private List<Article> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        list = (ListView) findViewById(R.id.list_articles);
        articles = new ArrayList<>();
        articleAdapter = new ArticleAdapter(this, articles);
        list.setAdapter(articleAdapter);
        service = new RESTPeercheckService(this, "", "articles") {
            @Override
            protected void lastAction(List<Article> articles) {
                ListActivity.this.articles = articles;
                for(Article article : articles) {
                    articleAdapter.add(article);
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        service.execute();
    }
}
