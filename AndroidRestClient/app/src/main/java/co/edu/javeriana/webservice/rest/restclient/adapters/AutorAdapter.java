package co.edu.javeriana.webservice.rest.restclient.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import co.edu.javeriana.webservice.rest.restclient.R;
import co.edu.javeriana.webservice.rest.restclient.negocio.Author;

public class AutorAdapter extends ArrayAdapter<Author> {

    public AutorAdapter(Context context, List<Author> array) {
        super(context, 0, array);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Author autor = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.autor_adapter_layout, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.autor_name);

        name.setText(autor.getName() + " " + autor.getSurname());

        return convertView;
    }
}
