package br.com.cast.turmaformacao.agenda.controllers.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactListAdapter extends BaseAdapter{
    private List<Contact> contactList;
    private Activity context;

    public ContactListAdapter(Activity context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    public void setDataValues(List<Contact> values) {
        contactList.clear();
        contactList.addAll(values);
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = (Contact) getItem(position);

        View contactListItemView = context.getLayoutInflater().inflate(R.layout.list_item_contact, parent, false);

        TextView textViewName = (TextView) contactListItemView.findViewById(R.id.textViewName);
        textViewName.setText(contact.getNome());

        return contactListItemView;

    }
}
