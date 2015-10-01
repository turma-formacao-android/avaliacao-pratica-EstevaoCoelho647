package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactFormActivity extends AppCompatActivity{
    EditText editTextNome;
    EditText editTextPhone;

    public static final String PARAM_TASK = "Contact";
    private Contact contact;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        initContact();
        bindEditTextNome();
        bindEditTextPhone();

    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = extras.getParcelable(ContactFormActivity.PARAM_TASK);
        }
        this.contact = contact == null ? new Contact() : this.contact;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.menu_add_contact:
                onMenuAddClick();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClick() {
        String requiredMessage = ContactFormActivity.this.getString(R.string.msg_required);
        if (!FormHelper.validateRequired(requiredMessage, editTextNome, editTextPhone)) {
            binContact();
            ContactBusinessService.save(contact);
            Toast.makeText(ContactFormActivity.this, R.string.msg_success_save, Toast.LENGTH_LONG).show();
            ContactFormActivity.this.finish();
        }
    }

    private void bindEditTextPhone() {
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextPhone.setText(contact.getTelefone() == null ? "" : contact.getTelefone());
    }

    private void bindEditTextNome() {
        editTextNome = (EditText) findViewById(R.id.editTextName);
        editTextNome.setText(contact.getNome() == null ? "" : contact.getNome());
    }

    private void binContact() {
        contact.setNome(editTextNome.getText().toString());
        contact.setTelefone(editTextPhone.getText().toString());
    }
}
