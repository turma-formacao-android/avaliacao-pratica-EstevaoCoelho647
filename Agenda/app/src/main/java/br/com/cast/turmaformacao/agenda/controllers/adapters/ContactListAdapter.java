package br.com.cast.turmaformacao.agenda.controllers.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactListAdapter extends BaseAdapter implements Filterable {
    private List<Contact> contactList;
    private Activity context;
    List<String> mOriginalValues;
    List<String> arrayList;

    public ContactListAdapter(Activity context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
        arrayList = getmOriginalValues();

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
    public Contact getItem(int position) {
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

        TextView editTextNome = (TextView) contactListItemView.findViewById(R.id.textViewName);
        editTextNome.setText(contact.getNome());
//        TextView editTextPhone = (TextView) contactListItemView.findViewById(R.id.textViewPhone);
//        editTextPhone.setText(contact.getTelefone().getTelefone());
//        TextView editTextEmail = (TextView) contactListItemView.findViewById(R.id.textViewEmail);
//        editTextEmail.setText(contact.getEmail().getEmail());
//        TextView editTextRedeSocial = (TextView) contactListItemView.findViewById(R.id.textViewRedeSocial);
//        editTextRedeSocial.setText(contact.getRedeSocial().getRedeSocial());


//        TextView editTextCep = (TextView) contactListItemView.findViewById(R.id.textViewCep);
//        editTextCep.setText(contact.getNome());
//        TextView editTextLogradouro = (TextView) contactListItemView.findViewById(R.id.textViewLogradouro);
//        editTextLogradouro.setText(contact.getTelefone());
//        TextView editTextTipoLogradouro = (TextView) contactListItemView.findViewById(R.id.textViewTipoDeLogradouro);
//        editTextTipoLogradouro.setText(contact.getAdress().getType());
//        TextView editTextBairro = (TextView) contactListItemView.findViewById(R.id.textViewBairro);
//        editTextBairro.setText(contact.getAdress().getNeighborhood());
//        TextView editTextCidade = (TextView) contactListItemView.findViewById(R.id.textViewCidade);
//        editTextCidade.setText(contact.getAdress().getCity());
//        TextView editTextEstado = (TextView) contactListItemView.findViewById(R.id.textViewEstado);
//        editTextEstado.setText(contact.getAdress().getState());


        return contactListItemView;

    }

    public List<String> getmOriginalValues() {
        arrayList = new ArrayList<>();
        for(Contact c : contactList) {
            arrayList.add(c.getNome());
        }

        return arrayList;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                arrayList = (List<String>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<String> FilteredArrList = new ArrayList<>();
                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<>(arrayList);
                }
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i);
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(data);
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
}

