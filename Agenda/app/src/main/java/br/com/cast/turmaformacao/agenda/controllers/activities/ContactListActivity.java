package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.controllers.adapters.ContactListAdapter;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.RedeSocial;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.RedeSocialBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.TelefoneBusinessService;


public class ContactListActivity extends AppCompatActivity {

    private ListView listViewContactList;
    private Contact selectedContact;
    ContactListAdapter adapter;
    private TextView filterEditText;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);

        bindContactList();

        bindEditTextFilter();

    }

    private void bindEditTextFilter() {
        filterEditText = (TextView) findViewById(R.id.editTextFilter);
        filterEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    protected void onResume() {
        List<Contact> values = ContactBusinessService.findAll();
        listViewContactList.setAdapter(new ContactListAdapter(this, values));
        ContactListAdapter adapter = (ContactListAdapter) listViewContactList.getAdapter();
        adapter.notifyDataSetChanged();
        super.onResume();
    }


    private void bindContactList() {
        listViewContactList = (ListView) findViewById(R.id.listViewContactList);
        registerForContextMenu(listViewContactList);
        listViewContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                adapter = (ContactListAdapter) listViewContactList.getAdapter();
                selectedContact = (Contact) adapter.getItem(position);
                return false;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menu_add_contact:
                onMenuAddContactClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddContactClick() {
        Intent goToTaskFormActivity = new Intent(ContactListActivity.this, ContactFormActivity.class);
        startActivity(goToTaskFormActivity);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo
            menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context_contact_list, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                onMenuDeleteClick();
                break;
            case R.id.menu_edit:
                onMenuEditClick();
        }
        return super.onContextItemSelected(item);

    }


    private void onMenuEditClick() {
        Intent goToTaskForm = new Intent(ContactListActivity.this, ContactFormActivity.class);
        goToTaskForm.putExtra("Contact", selectedContact);
        startActivity(goToTaskForm);
    }


    private void onMenuDeleteClick() {
        new AlertDialog.Builder(ContactListActivity.this)
                .setTitle(R.string.lbl_confirm)
                .setMessage(R.string.msg_confirm_delete)
                .setPositiveButton(R.string.lbl_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ContactBusinessService.delete(selectedContact);
                        TelefoneBusinessService.delete(selectedContact);
                        EmailBusinessService.delete(selectedContact);
                        RedeSocialBusinessService.delete(selectedContact);
                        selectedContact = null;
                        String message = getString(R.string.mgs_sucess_delete);
                        updateTaskList();
                        Toast.makeText(ContactListActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNeutralButton(R.string.lbl_no, null)
                .create()
                .show();
    }

    private void updateTaskList() {
        List<Contact> values = ContactBusinessService.findAll();
        listViewContactList.setAdapter(new ContactListAdapter(this, values));
        ContactListAdapter adapter = (ContactListAdapter) listViewContactList.getAdapter();
        adapter.notifyDataSetChanged();
    }
}


