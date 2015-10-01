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

    public static final String[] COLUNS = {ID, NAME, TELEFONE, EMAIL, REDESOCIAL};

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
        create.append(REDESOCIAL + " TEXT ");
        create.append(" ); ");

        return create.toString();
    }

    public static ContentValues getContentValues(Contact contact) {
        ContentValues values = new ContentValues();
        values.put(ContactContract.ID, contact.getId());
        values.put(ContactContract.NAME, contact.getNome());
        values.put(ContactContract.TELEFONE, contact.getTelefone());
        values.put(ContactContract.EMAIL, contact.getTelefone());
        values.put(ContactContract.REDESOCIAL, contact.getTelefone());

        return values;
    }

    private static Contact getContact(Cursor cursor) {
        Contact contact = new Contact();
        if (!cursor.isBeforeFirst() || cursor.moveToNext()) {
            contact.setId(cursor.getLong(cursor.getColumnIndex(ContactContract.ID)));
            contact.setNome(cursor.getString(cursor.getColumnIndex(ContactContract.NAME)));
            contact.setTelefone(cursor.getString(cursor.getColumnIndex(ContactContract.TELEFONE)));
            contact.setEmail(cursor.getString(cursor.getColumnIndex(ContactContract.EMAIL)));
            contact.setRedeSocial(cursor.getString(cursor.getColumnIndex(ContactContract.REDESOCIAL)));

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
