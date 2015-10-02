package br.com.cast.turmaformacao.agenda.model.persistence;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;

/**
 * Created by Administrador on 01/10/2015.
 */
public class ContactContract {

    public static String TABLE = "contact";
    public static String ID = "id";
    public static String NAME = "name";
    public static String TELEFONE = "telefone";
    public static String EMAIL = "email";
    public static String REDESOCIAL = "redesocial";
    public static String CEP = "cep";
    public static String TIPOLOGRADOURO = "tipoDeLogradouro";
    public static String BAIRRO = "bairro";
    public static String LOGRADOURO = "logradouro";
    public static String CIDADE = "cidade";
    public static String ESTADO = "estado";


    public static final String[] COLUNS = {ID, NAME, TELEFONE, EMAIL, REDESOCIAL, CEP, TIPOLOGRADOURO, BAIRRO, LOGRADOURO, CIDADE, ESTADO};

    private ContactContract() {
    }

    public static String getCreateTableScript() {


        final StringBuilder create = new StringBuilder();

        create.append(" CREATE TABLE " + TABLE);
        create.append(" ( ");
        create.append(ID + " INTEGER PRIMARY KEY, ");
        create.append(NAME + " TEXT NOT NULL,");
        create.append(TELEFONE + " TEXT,");
        create.append(EMAIL + " TEXT,");
        create.append(REDESOCIAL + " TEXT, ");
        create.append(CEP + " TEXT, ");
        create.append(TIPOLOGRADOURO + " TEXT, ");
        create.append(BAIRRO + " TEXT, ");
        create.append(LOGRADOURO + " TEXT, ");
        create.append(CIDADE + " TEXT, ");
        create.append(ESTADO + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getNome());
        values.put(ContactContract.CEP, contact.getAddress().getZipCode());
        values.put(ContactContract.TIPOLOGRADOURO, contact.getAddress().getType());
        values.put(ContactContract.BAIRRO, contact.getAddress().getNeighborhood());
        values.put(ContactContract.LOGRADOURO, contact.getAddress().getStreet());
        values.put(ContactContract.CIDADE, contact.getAddress().getCity());
        values.put(ContactContract.ESTADO, contact.getAddress().getState());

        return values;
    }

    private static Contact getContact(Cursor cursor) {
        Contact contact = new Contact();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setNome(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.getAddress().setZipCode(cursor.getString(cursor.getColumnIndex(ContactContract.CEP)));
            contact.getAddress().setType(cursor.getString(cursor.getColumnIndex(ContactContract.TIPOLOGRADOURO)));
            contact.getAddress().setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactContract.BAIRRO)));
            contact.getAddress().setStreet(cursor.getString(cursor.getColumnIndex(ContactContract.LOGRADOURO)));
            contact.getAddress().setCity(cursor.getString(cursor.getColumnIndex(ContactContract.CIDADE)));
            contact.getAddress().setState(cursor.getString(cursor.getColumnIndex(ContactContract.ESTADO)));
            return contact;
        }
        return null;
    }

    public static List getContacts(Cursor cursor) {
        ArrayList<Contact> contacts = new ArrayList();
        while (cursor.moveToNext()) {
            contacts.add(getContact(cursor));
        }
        return contacts;
    }

}
