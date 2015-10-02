package br.com.cast.turmaformacao.agenda.controllers.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.cast.turmaformacao.agenda.R;
import br.com.cast.turmaformacao.agenda.model.entities.Address;
import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.entities.RedeSocial;
import br.com.cast.turmaformacao.agenda.model.entities.Telefone;
import br.com.cast.turmaformacao.agenda.model.http.CepService;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.EmailBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.EmailRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.RedeSocialBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.RedeSocialRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.TelefoneBusinessService;
import br.com.cast.turmaformacao.agenda.model.persistence.TelefoneRepository;
import br.com.cast.turmaformacao.agenda.util.FormHelper;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactFormActivity extends AppCompatActivity {
    EditText editTextNome;
    EditText editTextPhone;
    EditText editTextEmail;
    EditText editTextRedeSocial;
    EditText editTextCep;
    EditText editTextLogradouro;
    EditText editTextTipoLogradouro;
    EditText editTextBairro;
    EditText editTextCidade;
    EditText editTextEstado;
    private ListView listViewEmailList;
    private ListView listViewPhoneList;
    private ListView listViewRedeSocialList;


    ArrayAdapter<String> adapterListViewPhones;
    ArrayAdapter<String> adapterListViewEmails;
    ArrayAdapter<String> adapterListViewRedesSociais;


    ArrayList<String> emails = new ArrayList<>();
    ArrayList<String> phones = new ArrayList<>();
    ArrayList<String> redesSociais = new ArrayList<>();
    ArrayList<Email> objEmails = new ArrayList<Email>();
    ArrayList<RedeSocial> objRedesSociais = new ArrayList<RedeSocial>();
    ArrayList<Telefone> objPhones = new ArrayList<Telefone>();

    public static final String PARAM_Contact = "Contact";
    private Contact contact;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_form);

        binEmailList();
        binPhoneList();
        binRedeSocialList();
        initContact();
        bindEditTextNome();
        bindEditTextPhone();
        bindEditTextCep();
        bindEditTextLogradouro();
        bindEditTextBairro();
        bindEditTextCidade();
        bindEditTextEstado();
        bindEditTextTipoLogradouro();
        bindEditTextEmail();
        bindEditTextRedeSocial();
        bindButtonSearch();
        bindButtonAddPhones();
        bindButtonAddEmail();
        bindButtonAddSocialContact();


    }

    private void bindButtonAddSocialContact() {
        Button buttonAddSocialContact = (Button) findViewById(R.id.buttonMoreRedeSocial);
        buttonAddSocialContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RedeSocial redeSocial = new RedeSocial();

                if (!editTextRedeSocial.getText().toString().equals("")) {
                    redesSociais.add(editTextRedeSocial.getText().toString());
                    adapterListViewRedesSociais = new ArrayAdapter<String>(ContactFormActivity.this, android.R.layout.simple_list_item_1, redesSociais);
                    listViewRedeSocialList.setAdapter(adapterListViewRedesSociais);
                    redeSocial.setRedeSocial(editTextRedeSocial.getText().toString());
                    objRedesSociais.add(redeSocial);
                }
            }
        });
    }

    private void binRedeSocialList() {
        listViewRedeSocialList = (ListView) findViewById(R.id.listViewRedeSociais);
        listViewRedeSocialList.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindButtonAddEmail() {

        Button buttonAddEmail = (Button) findViewById(R.id.buttonMoreEmail);
        buttonAddEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email email = new Email();
                if (!editTextEmail.getText().toString().equals("")) {
                    emails.add(editTextEmail.getText().toString());
                    adapterListViewEmails = new ArrayAdapter<String>(ContactFormActivity.this, android.R.layout.simple_list_item_1, emails);
                    listViewEmailList.setAdapter(adapterListViewEmails);
                    email.setEmail(editTextEmail.getText().toString());
                    objEmails.add(email);
                }
            }
        });
    }

    private void binEmailList() {
        listViewEmailList = (ListView) findViewById(R.id.listViewEmails);
        listViewEmailList.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void bindButtonAddPhones() {
        Button buttonAddPhones = (Button) findViewById(R.id.buttonMorePhone);
        buttonAddPhones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telefone phone = new Telefone();
                if (!editTextPhone.getText().toString().equals("")) {
                    phones.add(editTextPhone.getText().toString());
                    adapterListViewPhones = new ArrayAdapter<String>(ContactFormActivity.this, android.R.layout.simple_list_item_1, phones);
                    listViewPhoneList.setAdapter(adapterListViewPhones);
                    phone.setTelefone(editTextPhone.getText().toString());
                    objPhones.add(phone);
                }
            }
        });
    }

    private void binPhoneList() {
        listViewPhoneList = (ListView) findViewById(R.id.listViewTelefones);
        listViewPhoneList.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }

    private void initContact() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.contact = extras.getParcelable(ContactFormActivity.PARAM_Contact);
        }
        this.contact = contact == null ? new Contact() : this.contact;

        if (contact.getId() != null){
            contact.setEmail(EmailRepository.getById(contact.getId()));
            contact.setTelefone(TelefoneRepository.getById(contact.getId()));
            contact.setRedeSocial(RedeSocialRepository.getById(contact.getId()));


            for (Email e : objEmails) {
                emails.add(e.getEmail());
            }
            for (Telefone t : objPhones) {
                phones.add(t.getTelefone());
            }
            for (RedeSocial rs : objRedesSociais) {
                redesSociais.add(rs.getRedeSocial());
            }
            setListsAdapters();

        }
    }

    private void setListsAdapters() {
        adapterListViewRedesSociais = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, redesSociais);
        listViewRedeSocialList.setAdapter(adapterListViewRedesSociais);

        adapterListViewEmails = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, emails);
        listViewEmailList.setAdapter(adapterListViewEmails);

        adapterListViewPhones = new ArrayAdapter<>(ContactFormActivity.this, android.R.layout.simple_list_item_1, phones);
        listViewPhoneList.setAdapter(adapterListViewPhones);
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
        if (!FormHelper.validateRequired(requiredMessage, editTextNome)) {
            binContact();
            ContactBusinessService.save(contact);
            binLists();
            saveLists();
            Toast.makeText(ContactFormActivity.this, R.string.msg_success_save, Toast.LENGTH_LONG).show();
            ContactFormActivity.this.finish();
        }
    }

    private void saveLists() {
        for (Email e : objEmails) {
            EmailBusinessService.save(e);
        }
        for (Telefone t : objPhones) {
            TelefoneBusinessService.save(t);
        }
        for (RedeSocial rs : objRedesSociais) {
           RedeSocialBusinessService.save(rs);
        }

    }


    private class GetAddressContact extends AsyncTask<String, Void, Address> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Address doInBackground(String... params) {
            return CepService.getAddressByCep(params[0]);
        }

        @Override
        protected void onPostExecute(Address address) {
            super.onPostExecute(address);
        }
    }

    private void bindButtonSearch() {
        Button buttonSearch = (Button) findViewById(R.id.buttonBusca);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AsyncTask<String, Void, Address> execute = new GetAddressContact().execute((editTextCep).getText().toString());
                boolean find = false;
                while (!find) {
                    try {
                        editTextCidade.setText(execute.get().getCity());
                        editTextBairro.setText(execute.get().getNeighborhood());
                        editTextEstado.setText(execute.get().getState());
                        editTextLogradouro.setText(execute.get().getStreet());
                        editTextTipoLogradouro.setText(execute.get().getType());
                        find = true;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        editTextCep.setError(getString(R.string.txt_invalid_zip));
                    } catch (ExecutionException e) {
                        editTextCep.setError(getString(R.string.txt_invalid_zip));
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    private void bindEditTextPhone() {
        editTextPhone = (EditText) findViewById(R.id.editTextTelefone);
       // editTextPhone.setText(contact.getTelefone() == null ? "" : contact.getTelefone());
    }

    private void bindEditTextEmail() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        //editTextEmail.setText(contact.getEmail().getEmail() == null ? "" : contact.getEmail().getEmail());
    }

    private void bindEditTextRedeSocial() {
        editTextRedeSocial = (EditText) findViewById(R.id.editTextRedeSocial);
       // editTextRedeSocial.setText(contact.getRedeSocial().getRedeSocial() == null ? "" : contact.getRedeSocial().getRedeSocial());
    }

    private void bindEditTextNome() {
        editTextNome = (EditText) findViewById(R.id.editTextName);
        editTextNome.setText(contact.getNome() == null ? "" : contact.getNome());
    }

    private void bindEditTextCep() {
        editTextCep = (EditText) findViewById(R.id.editTextCep);
        editTextCep.setText(contact.getAddress().getZipCode() == null ? "" : contact.getAddress().getZipCode());
    }

    private void bindEditTextLogradouro() {
        editTextLogradouro = (EditText) findViewById(R.id.editTextLogradouro);
        editTextLogradouro.setText(contact.getAddress().getStreet() == null ? "" : contact.getAddress().getStreet());
    }

    private void bindEditTextBairro() {
        editTextBairro = (EditText) findViewById(R.id.editTextBairro);
        editTextBairro.setText(contact.getAddress().getNeighborhood() == null ? "" : contact.getAddress().getNeighborhood());
    }

    private void bindEditTextCidade() {
        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        editTextCidade.setText(contact.getAddress().getCity() == null ? "" : contact.getAddress().getCity());
    }

    private void bindEditTextEstado() {
        editTextEstado = (EditText) findViewById(R.id.editTextEstado);
        editTextEstado.setText(contact.getAddress().getState() == null ? "" : contact.getAddress().getState());
    }

    private void bindEditTextTipoLogradouro() {
        editTextTipoLogradouro = (EditText) findViewById(R.id.editTextTipoDeLogradouro);
        editTextTipoLogradouro.setText(contact.getAddress().getType() == null ? "" : contact.getAddress().getType());
    }

    private void binContact() {
        contact.setNome(editTextNome.getText().toString());
//        contact.getTelefone().setTelefone(editTextPhone.getText().toString());
//        contact.getEmail().setEmail(editTextEmail.getText().toString());
//        contact.getRedeSocial().setRedeSocial(editTextRedeSocial.getText().toString());
        contact.getAddress().setZipCode(editTextCep.getText().toString());
        contact.getAddress().setStreet(editTextLogradouro.getText().toString());
        contact.getAddress().setNeighborhood(editTextBairro.getText().toString());
        contact.getAddress().setCity(editTextCidade.getText().toString());
        contact.getAddress().setState(editTextEstado.getText().toString());
        contact.getAddress().setType(editTextTipoLogradouro.getText().toString());
    }

    private void binLists() {
        List<Contact> contacts = ContactBusinessService.findAll();
        Long ultimoId = null;

        for (Contact c : contacts) {
            ultimoId = c.getId();
        }
        for (Email e : objEmails) {
            e.setIdContact(ultimoId);
        }
        for (Telefone t : objPhones) {
            t.setIdContact(ultimoId);
        }
        for (RedeSocial rs : objRedesSociais) {
            rs.setIdContact(ultimoId);
        }
    }
}
