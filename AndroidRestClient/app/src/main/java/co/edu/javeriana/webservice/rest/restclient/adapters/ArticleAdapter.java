package co.edu.javeriana.webservice.rest.restclient.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import co.edu.javeriana.webservice.rest.restclient.R;
import co.edu.javeriana.webservice.rest.restclient.negocio.Article;
import co.edu.javeriana.webservice.rest.restclient.negocio.Author;

public class ArticleAdapter extends ArrayAdapter<Article> {

    public ArticleAdapter(Context context, List<Article> array) {
        super(context, 0, array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Article article = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.article_adapter_layout, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.article_name);
        ListView message = (ListView) convertView.findViewById(R.id.article_list_authors);
        List<Author> autores = new ArrayList<>();
        AutorAdapter autorAdapter = new AutorAdapter(parent.getContext(), autores);
        message.setAdapter(autorAdapter);
        name.setText(article.getTitle());

        for(Author author : article.getAuthors()) {
            autorAdapter.add(author);
            Log.i("Autor", author.getName());
        }

        return convertView;
    }
}
